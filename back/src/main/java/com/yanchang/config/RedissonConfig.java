package com.yanchang.config;


import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//从application.yml文件中读取前缀为“spring.redix"的配置项
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {

    private Integer database;
    private String host;
    private Integer port;
    //private String password;

    @Bean
    public RedissonClient getRedissonClient(){
        Config config = new Config();
        //添加单机Redisson配置
        config.useSingleServer()
        //设置数据库
                .setDatabase(database).
        //设置地址
        setAddress("redis://"+host+":"+port);
             //   .setPassword(password);

        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
