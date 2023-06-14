package com.sgz.banlv.controller;



import com.sgz.banlv.vo.Result;
import com.sgz.banlv.service.IUserService;
import com.sgz.banlv.utils.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
public class UserController {
    @Resource
    private IUserService userService;

//    @GetMapping("/all")
//    public Result<List<User>> getAllUser() {
//        List<User> list = userService.list();
//        return Result.success(list);
//    }

    @RequestMapping("/wechatgetuserid")
    public Result<Long> WechatGetUserId(@RequestParam String code) {
        //调用微信接口获取openid用户唯一标识
        String openid = WeChatUtil.getSessionKeyOrOpenId(code);
        if(StringUtils.isEmpty(openid)) {

            return userService.WechatGetUserId(openid);
        }else {
            return Result.failure();
        }
    }
}
