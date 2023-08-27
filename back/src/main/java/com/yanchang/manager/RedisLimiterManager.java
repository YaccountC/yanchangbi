package com.yanchang.manager;

import com.yanchang.exception.BusinessException;
import com.yanchang.common.ErrorCode;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisLimiterManager {
    @Resource
    private RedissonClient redissonClient;
    /**
     * 限流操作
     * @param key 区分不同限流器，比如不同用户的id应该分别统计
     * */
    public void doRateLimit(String key){
        //创建名为user_limiter的限流器。每秒最多访问两次
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        //限流器规则（每秒2个请求；连续的请求，最多只能有一个请求允许通过）
        //RateType.OVERALL 表示速率限制作用域整个令牌桶，即限制所有请求的速率
        rateLimiter.trySetRate(RateType.OVERALL,2,1, RateIntervalUnit.SECONDS);
        //每当一个操作来了，请求一个令牌
        boolean can0p = rateLimiter.tryAcquire(1);
        //如果没有令牌，还想执行操作就抛出异常
        if(!can0p){
            throw new BusinessException(ErrorCode.TOO_MANY_REQUEST);
        }
    }
}
