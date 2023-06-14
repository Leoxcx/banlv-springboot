package com.sgz.banlv.service;

import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.Userarrive;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface IUserarriveService extends IService<Userarrive> {

    Result<List<Userarrive>>  GetUserArrive(String user_openid);
}
