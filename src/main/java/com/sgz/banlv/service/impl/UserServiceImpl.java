package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.User;
import com.sgz.banlv.mapper.UserMapper;
import com.sgz.banlv.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

//    @Resource
//    private IUserService users;


    @Override
    public Result<Long> WechatGetUserId(String openid) {

            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userOpenid", openid);
            //通过openid查询对应user数据

            User user = this.getOne(queryWrapper);
            // 判断用户是否注册
            if(user != null) {
                return Result.success(user.getUser_id());
            }else {
                User user1 = new User();
                user1.setUser_openid(openid);
                if(this.save(user1)) {
                    User user2 = this.getOne(queryWrapper);
                    return Result.success(user2.getUser_id());
                }
            }

        return Result.failure();

    }

}
