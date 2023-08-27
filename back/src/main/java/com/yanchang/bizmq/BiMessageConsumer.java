package com.yanchang.bizmq;

import static com.yanchang.bizmq.BiMqConstant.*;
import com.rabbitmq.client.Channel;
import com.yanchang.constant.CommonConstant;
import com.yanchang.exception.BusinessException;
import com.yanchang.common.ErrorCode;
import com.yanchang.manager.AiManager;
import com.yanchang.model.entity.Chart;
import com.yanchang.service.ChartService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class BiMessageConsumer {
    @Resource
    private ChartService chartService;
    @Resource
    private AiManager aiManager;


    @SneakyThrows
    @RabbitListener(queues = {BI_QUEUE_NAME},ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag){
        log.info("receiveMessage message = {}",message);
        if(StringUtils.isBlank(message)){
            channel.basicNack(deliveryTag,false,false);
            throw  new BusinessException(ErrorCode.SYSTEM_ERROR,"消息为空");
        }
        long chartId = Long.parseLong(message);
        Chart chart = chartService.getById(chartId);
        if(chart==null){
            channel.basicNack(deliveryTag,false,false);
            throw  new BusinessException(ErrorCode.NOT_FOUND_ERROR,"消息为空");
        }

        Chart updateChart = new Chart();
        updateChart.setId(chart.getId());
        updateChart.setStatus("running");
        boolean b = chartService.updateById(updateChart);
        if(!b){
            channel.basicNack(deliveryTag,false,false);
            handleChartUpdateError(chart.getId(),"更新图表执行中状态失败");
            return ;
        }
        String result = aiManager.doChat(CommonConstant.BI_MODEL_ID,buildUserInput(chart).toString());
        String[] splits = result.split("【【【【【");
        if(splits.length<3){
            channel.basicNack(deliveryTag,false,false);
            handleChartUpdateError(chart.getId(),"AI生成错误");
            return;
        }
        String genChart = splits[1].trim();
        String genResult = splits[2].trim();

        Chart updateChartResult = new Chart();
        updateChartResult.setId(chart.getId());
        updateChartResult.setGenChart(genChart);
        updateChartResult.setGenResult(genResult);
        updateChartResult.setStatus("succeed");
        boolean updateResult = chartService.updateById(updateChartResult);
        if(!updateResult){
            channel.basicNack(deliveryTag,false,false);
            handleChartUpdateError(chart.getId(), "更新图表完成状态失败");
        }

    }
    private void handleChartUpdateError(long chartId,String executorMessage){
        Chart updateChartResult = new Chart();
        updateChartResult.setId(chartId);
        updateChartResult.setStatus("executorMessage");
        boolean updateResult = chartService.updateById((updateChartResult));
        if(!updateResult){
            log.error("更新图表状态失败"+chartId+","+executorMessage);
        }
    }

    public String buildUserInput(Chart chart){
        String goal = chart.getGoal();
        String chartType = chart.getCharType();
        String csvData = chart.getChartData();


        StringBuilder userInput = new StringBuilder();
        userInput.append("分析需求：").append("\n");

        // 拼接分析目标
        String userGoal = goal;
        if (StringUtils.isNotBlank(chartType)) {
            userGoal += "，请使用" + chartType;
        }
        userInput.append(userGoal).append("\n");
        userInput.append("原始数据：").append("\n");
        userInput.append(csvData).append("\n");
        return userInput.toString();
    }

}
