package com.yupi.springbootinit.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
public class ExcelUtils {
    public static String excelToCsv(MultipartFile multipartFile){
        //File file = null;
       // try{
          //  file = ResourceUtils.getFile("C:\\Users\\ERPHM\\antdesign\\develop\\bi\\back\\src\\main\\resources\\网站数据.xlsx");


        //}catch (FileNotFoundException e){
           // e.printStackTrace();
        //}
        List<Map<Integer,String>> list = null;

        try{
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        }catch (IOException e){
            log.error("表格处理错误",e);
        }

        if(CollUtil.isEmpty(list)){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        LinkedHashMap<Integer,String> headerMap = (LinkedHashMap) list.get(0);
        List<String> headerList = headerMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        stringBuilder.append(StringUtils.join(headerList,"，")).append("\n");
        for (int i=1;i< list.size();i++){
            LinkedHashMap<Integer,String> dataMap = (LinkedHashMap<Integer, String>) list.get(i);
            List<String> dataList = dataMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
            stringBuilder.append(StringUtils.join(dataList,"，")).append("\n");
        }

        return stringBuilder.toString();


    }
    //public static void main(String[] args){
        //System.out.println(excelToCsv(null));
    //}
}
