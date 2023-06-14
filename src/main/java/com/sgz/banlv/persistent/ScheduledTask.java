package com.sgz.banlv.persistent;

import com.sgz.banlv.common.model.PlayNum;
import com.sgz.banlv.mapper.ScenicResourceMapper;
import com.sgz.banlv.mapper.ScenicspotMapper;
import com.sgz.banlv.mapper.SceniczoneMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class ScheduledTask {

    @Resource
    private ScenicResourceMapper scenicResourceMapper;
    @Resource
    private ScenicspotMapper scenicspotMapper;
    @Resource
    private SceniczoneMapper sceniczoneMapper;

//    @Scheduled(cron = "0 0 0 * * ?") // 每天晚上12点触发
    @Scheduled(cron = "0 */3 * * * ?") // 每天隔3分钟触发一次
    public void myScheduledMethod() {
        log.info("myScheduledMethod");
//        保存资源播放数定时任务
        boolean a = PlayNum.getPlayNumModel().setResourceMapper(scenicResourceMapper).saveResourceRecordToSql();

//            保存景点播放数定时任务
        boolean b = PlayNum.getPlayNumModel().setScenicspotMapper(scenicspotMapper).saveScenicSpotRecordToSql();

//            保存景区播放数定时任务
        boolean c = PlayNum.getPlayNumModel().setSceniczoneMapper(sceniczoneMapper).saveScenicZoneRecordToSql();
        if(a && b && c){
//                重置单例
            PlayNum.reinitialize();
            log.info("已完成播放记录持久化");
        }else{
            log.info("持久化播放记录失败");
        }
        // 在这里编写需要执行的任务逻辑
//        System.out.println("执行定时任务");
    }
}
