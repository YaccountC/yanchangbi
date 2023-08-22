package com.yupi.springbootinit.model.dto.chart;

import java.io.Serializable;
import lombok.Data;

/**
 * 文件上传请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class GenChartByAiRequest implements Serializable {

    /**
     * 分析目标
     */
    private String goal;
    /**
     * 图标名称
     */
    private String name;
    /**
     * 图标类型
     */
    private String chartType;

    private static final long serialVersionUID = 1L;
}