package com.example.generator.controller;

import com.example.common.Result;
import com.example.generator.entity.User;
import com.example.generator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
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

    //
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> data=userService.login(user);
        if(data!=null) {
            return Result.success(data);
        }
        return Result.fail(2002,"用户名或者密码错误");
    }

    @GetMapping("/all")
//    注意这里的返回值,是result类型的,且指明泛型<T>是<List<User>>
    public Result<List<User>> getAlluser(){
        List<User> list=userService.list();
        return Result.success(list);
    }
}
