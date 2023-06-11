package com.example.generator.controller;

import com.example.common.Result;
import com.example.generator.entity.User;
import com.example.generator.entity.message.PhoneMeg;
import com.example.generator.entity.message.UpdateUserInfoMeg;
import com.example.generator.entity.message.UserReturnMeg;
import com.example.generator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.lang.System.out;

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
    public Result<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> data=userService.login(user);
        if(data!=null) {
            return Result.success(data);
        }
        return Result.fail(2002,"用户名或者密码错误");
    }

    // register--注册,验证数据之后 在user数据库添加表项
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


    @PostMapping("/getInfo")
    public ResponseEntity<UserReturnMeg> getUserInfoByPhone(@RequestBody PhoneMeg phoneMeg) {
//    public Result<UserReturnMeg> getUserInfoByPhone(@RequestBody String phone) {
        out.println(phoneMeg.getPhone());
        User user = userService.getUserByPhone(phoneMeg.getPhone());
        UserReturnMeg userReturnMeg = new UserReturnMeg(user.getProfile(), user.getName(), user.getEmail());

//        return Result.success(userReturnMeg);
//        out.println(user.getName());
        return ResponseEntity.status(HttpStatus.OK).body(userReturnMeg);

    }

    @PostMapping("/updateUserInfo")
    public ResponseEntity<UserReturnMeg> updateUserInfo(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UpdateUserInfoMeg updateUserInfoMeg) {

        out.println(updateUserInfoMeg.getName());
        String modifiedString = authorizationHeader.replaceAll("Bearer ", "");
        out.println("token= "+modifiedString);
        Integer userid= userService.getUser(modifiedString);
//根据用户ID更新用户信息
        String result = userService.updateUser(userid, updateUserInfoMeg.getAvatarUrl(), updateUserInfoMeg.getEmail(), updateUserInfoMeg.getName());
//        out.println("更新了没？？？userid= "+userid);
        if(result.equals("更新成功")){
            //查询用户最新信息返回给前端
            User updatedUser = userService.getUserByID(userid);
            UserReturnMeg userReturnMeg = new UserReturnMeg(updatedUser.getProfile(), updatedUser.getName(), updatedUser.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(userReturnMeg);
//            return ResponseEntity.status(HttpStatus.OK).body("111");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
