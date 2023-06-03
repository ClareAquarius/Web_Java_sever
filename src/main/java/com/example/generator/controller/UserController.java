package com.example.generator.controller;

import com.example.common.Result;
import com.example.generator.entity.User;
import com.example.generator.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
//@Controller
//这个注解默认返回的是视图
@RestController
//这个返回会默认做json处理
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> data=userService.login(user);
        if(data!=null) {
            return Result.success(data);
        }
        return Result.fail(2002,"用户名或者密码错误");
    }
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        String data= userService.register(user);
        if(data == "注册成功")
        {
            return Result.success(data);
        }
        else
        {
            return Result.fail(data);
        }
    }
    @GetMapping("/info")
    public Result<Map<String,Object>> getUserInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String modifiedString = authorizationHeader.replaceAll("Bearer ", "");
        //根据token查看用户信息
        Map<String,Object> data= userService.getUserInfo(modifiedString);
        //结果不为空,则生成token
        if(data!=null)
        {
            return Result.success(data);
        }
        return  Result.fail("无效token");
    }
}
