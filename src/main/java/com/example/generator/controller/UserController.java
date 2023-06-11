package com.example.generator.controller;

import com.example.common.Result;
import com.example.generator.entity.User;
import com.example.generator.entity.message.*;
import com.example.generator.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // login登录--登录验证用户的账号和密码,并为用户生成token,存入redis
    @PostMapping("/login")
    public ResponseEntity<Result<Map<String,Object>>> login(@RequestBody User user){
        Map<String,Object> data=userService.login(user);
        if(data!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(Result.success(data));
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("用户名或密码错误"));
    }

    // register--注册,验证数据之后 在user数据库添加表项
    @PostMapping("/register")
    public ResponseEntity<Result<String>> register(@RequestBody RegisterMeg registerMeg) {
        String data= userService.register(registerMeg);
        if(data == "注册成功")
        {
            return ResponseEntity.status(HttpStatus.OK).body(Result.success(data));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail(data));
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Result<String>>changePassword(@RequestBody ChangePasswordMeg changePasswordMeg) {
        String data= userService.changePassword(changePasswordMeg);
        if(data == "密码修改成功")
        {
            return ResponseEntity.status(HttpStatus.OK).body(Result.success(data));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail(data));
        }
    }

    // info--验证token,并返回用户数据
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
