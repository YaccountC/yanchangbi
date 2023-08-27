package com.yanchang.manager;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AiManagerTest {
    @Resource
    private AiManager aimanager;
    @Test
    void doChat(){
        String ans = aimanager.doChat(1691360639622098946L,
                "分析诉求：\n"+
                        "原始数据:\n"+
                        "日期，用户数\n"+
                        "1号，10\n"+
                        "2号，20\n"+
                        "3号，30\n"+
                        "4号，40\n"+
                        "5号，50");
        System.out.println(ans);
    }
}