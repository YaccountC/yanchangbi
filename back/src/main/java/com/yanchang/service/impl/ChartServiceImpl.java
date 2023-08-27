package com.yanchang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yanchang.service.ChartService;
import com.yanchang.model.entity.Chart;
import com.yanchang.mapper.ChartMapper;
import org.springframework.stereotype.Service;

/**
* @author ERPHM
* @description 针对表【chart(图表信息表)】的数据库操作Service实现
* @createDate 2023-08-22 17:32:17
*/
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService {


}




