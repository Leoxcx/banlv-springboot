package com.sgz.banlv.controller;

import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.Userarrive;
import com.sgz.banlv.service.IUserarriveService;
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
public class UserarriveController {

    @Resource
    private IUserarriveService userarriveService;


    @RequestMapping("/getuserarrive")
    public Result<List<Userarrive>>  GetUserArrive(@RequestParam String user_openid) {
        if(StringUtils.isEmpty(user_openid )) {
            return userarriveService.GetUserArrive(user_openid);
        } else {
            return Result.failure();
        }
    }

}
