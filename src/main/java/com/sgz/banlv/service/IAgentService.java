package com.sgz.banlv.service;

import com.sgz.banlv.entity.Agent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sgz.banlv.dto.CoordinateRecordDto;
import com.sgz.banlv.vo.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface IAgentService extends IService<Agent> {

    Result<Boolean> SaveCoordinateRecord(CoordinateRecordDto coordinateRecordDto);

    Result<List<CoordinateRecordDto>> AgentToCoordinateRecord(Long agent_id);

    Result<Boolean> RemoveCoordinateRecord(Long agent_id, String uuid);
}
