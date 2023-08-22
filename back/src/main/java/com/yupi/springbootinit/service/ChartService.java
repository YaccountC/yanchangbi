package com.yupi.springbootinit.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.dto.chart.ChartQueryRequest;
import com.yupi.springbootinit.model.dto.user.UserQueryRequest;
import com.yupi.springbootinit.model.entity.Chart;


/**
* @author ERPHM
* @description 针对表【chart(图表信息表)】的数据库操作Service
* @createDate 2023-08-22 17:32:17
*/
public interface ChartService extends IService<Chart> {

}
