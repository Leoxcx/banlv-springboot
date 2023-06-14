package com.sgz.banlv.controller;

import com.sgz.banlv.service.IAgentService;
import com.sgz.banlv.dto.CoordinateRecordDto;
import com.sgz.banlv.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@RestController
@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
public class AgentController {

    @Resource
    private IAgentService agentService;

//    传入代理商id、城市id、景区名及景点信息存储在内存中
    @RequestMapping("/savecoordinaterecord")
    private Result<Boolean> SaveCoordinateRecord(@RequestBody CoordinateRecordDto coordinateRecordDto){
        System.out.println(coordinateRecordDto.toString());
        if(coordinateRecordDto != null) {
            return agentService.SaveCoordinateRecord(coordinateRecordDto);
        }
        return Result.failure();
    }

//    传入代理商id获取当前内存中所有的代理商已添加信息
    @RequestMapping("/agenttocoordinaterecord")
    private Result<List<CoordinateRecordDto>> AgentToCoordinateRecord(@RequestParam Long agent_id){
        if(agent_id != null && agent_id != 0) {
            return agentService.AgentToCoordinateRecord(agent_id);
        }
        return Result.failure();
    }

    //代理商删除定位信息
    @RequestMapping("/removecoordinaterecord")
    private Result<Boolean> RemoveCoordinateRecord(@RequestParam Long agent_id, @RequestParam String uuid){
        if(agent_id != null && agent_id != 0 && StringUtils.isNotEmpty(uuid)) {
            return agentService.RemoveCoordinateRecord(agent_id, uuid);
        }
        return Result.failure();
    }
}
