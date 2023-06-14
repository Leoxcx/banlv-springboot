package com.sgz.banlv.service;

import com.sgz.banlv.dto.UserPlayDto;
import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.Userplay;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.Time;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface IUserplayService extends IService<Userplay> {

    Result<List<Userplay>> PlayRecord(Long user_id);

    Result<Boolean> SaveUserPlaysubmitservlet(UserPlayDto userPlayDto);
}
