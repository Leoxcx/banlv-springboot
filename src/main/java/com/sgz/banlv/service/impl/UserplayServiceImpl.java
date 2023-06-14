package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.sgz.banlv.dto.UserPlayDto;
import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.Userplay;
import com.sgz.banlv.mapper.UserplayMapper;
import com.sgz.banlv.service.IUserplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
public class UserplayServiceImpl extends ServiceImpl<UserplayMapper, Userplay> implements IUserplayService {

    //通过userId查用户播放记录
    @Override
    public Result<List<Userplay>> PlayRecord(Long user_id) {
        List<Userplay> userplays = this.lambdaQuery().eq(Userplay::getUser_id, user_id).list();
        if(CollectionUtils.isNotEmpty(userplays)) {
            return Result.success(userplays);
        }
        return Result.failure();
    }

    @Override
    public Result<Boolean> SaveUserPlaysubmitservlet(UserPlayDto userPlayDto) {


        boolean save = this.save(this.toUserplay(userPlayDto));

        return save ? Result.success() : Result.failure();
    }


    // 添加一个方法将DTO转换为实体类
    public Userplay toUserplay(UserPlayDto userPlayDto) {
        Userplay userplay = new Userplay();
        userplay.setResource_id(userPlayDto.getResource_id());
        userplay.setUser_id(userPlayDto.getUser_id());




        if (userPlayDto.getUserPlay_time() != null) {
            // 将时间戳转为当前时间
            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(userPlayDto.getUserPlay_time(), 0, ZoneOffset.ofHours(8));
            log.info("userPlay_time",dateTime.toString());
            userplay.setUserPlay_time(dateTime);
        }
        return userplay;
    }
}
