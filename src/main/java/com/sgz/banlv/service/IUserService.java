package com.sgz.banlv.service;

import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface IUserService extends IService<User> {

    Result<Long> WechatGetUserId(String openid);

}
