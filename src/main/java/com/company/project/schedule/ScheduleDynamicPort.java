package com.company.project.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

//废弃

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduleDynamicPort {
    /*@Autowired
    private DynamicPortService dynamicPortService;
    *//**
     * id1Initial 一次更新的第一个数；id2Initial 一次更新的最后一个数
     * num 一次更新的数量； amount 模拟数据总个数； i 第几次更新
     *//*
    int id1Initial=1;
    int id2Initial=17;

    int num=17;
    int amount=68;
    int id1=0;
    int id2=0;
    int i=0;

    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")//或直接指定时间间隔，例如：5秒//@Scheduled(fixedRate=5000)
    private void configureTasks() {


        if (id2<amount){
            id1=id1Initial+num*i;
            id2=id2Initial+num*i;
            i++;
        }
        else {
            id1=id1Initial;
            id2=id2Initial;
            i=1;
        }
        System.out.println("ScheduleDynamicPort: " + LocalDateTime.now()+"  id1: " + id1+", id2: " + id2 );
        dynamicPortService.updateDynamicPortBetweenId(id1,id2);

    }*/
}
