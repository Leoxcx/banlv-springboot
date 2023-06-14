package com.sgz.banlv.controller;

import com.sgz.banlv.dto.UserPlayDto;
import com.sgz.banlv.utils.CommonUtils;
import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.Userplay;
import com.sgz.banlv.service.IUserplayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Time;
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
@RequestMapping( method = {RequestMethod.GET,RequestMethod.POST})
public class UserplayController {

    @Resource
    private IUserplayService userplayService;

//    通过用户id  user_id 获取播放记录
    @RequestMapping("/playrecord")
    public Result<List<Userplay>> PlayRecord(@RequestParam Long user_id) {

        if(CommonUtils.isLongNotEmpty(user_id)) {

            return userplayService.PlayRecord(user_id);
        }else {
            return Result.failure();
        }
    }

    //存播放记录
    @RequestMapping("/userPlaysubmitservlet")
    public Result<Boolean> SaveUserPlaysubmitservlet(@ModelAttribute UserPlayDto userPlayDto) {
        if(userPlayDto != null) {
            return userplayService.SaveUserPlaysubmitservlet(userPlayDto);
        }else {
            return Result.failure();
        }
    }
}
