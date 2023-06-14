package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.sgz.banlv.mapper.UserMapper;
import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.User;
import com.sgz.banlv.entity.Userarrive;
import com.sgz.banlv.mapper.UserarriveMapper;
import com.sgz.banlv.service.IUserService;
import com.sgz.banlv.service.IUserarriveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class UserarriveServiceImpl extends ServiceImpl<UserarriveMapper, Userarrive> implements IUserarriveService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private IUserService userService;

//    通过用户openid获取用户所有的打开信息（useArrive）表 获取用户打卡记录
    @Override
    public Result<List<Userarrive>> GetUserArrive(String user_openid) {


            //通过openid查询对应user数据
            User user = userService.query().eq("user_openid", user_openid).one();

            if(user != null) {
                List<Userarrive> userarrive = lambdaQuery().eq(Userarrive::getUser_id, user.getUser_id()).list();

                if(CollectionUtils.isNotEmpty(userarrive)) {
                    return Result.success(userarrive);
                }
            }

        return Result.failure();
    }
}
