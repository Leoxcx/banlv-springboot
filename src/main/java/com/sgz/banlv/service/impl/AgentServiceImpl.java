package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.sgz.banlv.common.model.CoordinateRecordInfo;
import com.sgz.banlv.entity.Agent;
import com.sgz.banlv.mapper.AgentMapper;
import com.sgz.banlv.service.IAgentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgz.banlv.dto.CoordinateRecordDto;
import com.sgz.banlv.vo.Result;
import com.sgz.banlv.dto.ScenicspotDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, Agent> implements IAgentService {


    //存代理商暂存景点信息
    @Override
    public Result<Boolean> SaveCoordinateRecord(CoordinateRecordDto coordinateRecordDto) {
        List<ScenicspotDto> scenicspotDtos = coordinateRecordDto.getScenicspotDto();
        if(scenicspotDtos.size() != 0 ) {
            for (ScenicspotDto scenicspotDto : scenicspotDtos) {
                scenicspotDto.setUuid(UUID.randomUUID().toString());
            }
            CoordinateRecordInfo.getCoordinateRecordModel().CoordinateRecord(coordinateRecordDto);
            return Result.success();
        }else {
            return Result.failure();
        }

    }

    //    传入代理商id获取当前内存中所有的代理商已添加信息
    @Override
    public Result<List<CoordinateRecordDto>> AgentToCoordinateRecord(Long agent_id) {
        List<CoordinateRecordDto> coordinateRecord = CoordinateRecordInfo.getCoordinateRecordModel().getCoordinateRecord(agent_id);
        if(CollectionUtils.isNotEmpty(coordinateRecord)) {
            return Result.success(coordinateRecord);
        }
        return Result.failure();
    }

    //代理商删除定位信息
    @Override
    public Result<Boolean> RemoveCoordinateRecord(Long agent_id, String uuid) {
        if (CoordinateRecordInfo.getCoordinateRecordModel().removeRecord(agent_id, uuid)) {
            return Result.success();
        }
        return Result.failure();
    }
}
