package com.example.generator.controller;

import com.example.common.Result;
import com.example.generator.entity.Admin;
import com.example.generator.entity.User;
import com.example.generator.entity.message.ChangeAdminMsg;
import com.example.generator.entity.message.ChangePasswordMeg;
import com.example.generator.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
@RestController
//这个返回会默认做json处理
@RequestMapping("/api/auth")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    //登录--验证管理员的账号和密码,并为用户生成token,存入redis
    @RequestMapping("/adminLogin")
    public Result<Map<String,Object>> login(@RequestBody Admin admin){
        Map<String,Object> data=adminService.login(admin);
        if(data!=null) {
            return Result.success(data);
        }
        return Result.fail(2002,"账户或者密码错误");
    }
    @GetMapping("/admininfo")
    public Result<Map<String,Object>> getAdminInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String modifiedString = authorizationHeader.replaceAll("Bearer ", "");
        //根据token查看管理员信息
        Map<String,Object> data= adminService.getAdminInfo(modifiedString);
        //结果不为空,则生成token
        if(data!=null)
        {
            return Result.success(data);
        }
        return  Result.fail("无效token");
    }
    @PostMapping("/changeAdminPassword")
    public ResponseEntity<Object> changePassword(@RequestBody ChangeAdminMsg msg)
    {
        boolean if_have_admin =adminService.searchAdminByAccount(msg.getAccount());
        if(!if_have_admin){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("输入的账号不存在"));
        }
        if(msg.getPassword1().length()<6)
        {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("密码长度需至少为6"));
        }
        if(!Objects.equals(msg.getPassword1(), msg.getPassword2()))
        {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("两次输入的密码不一致！"));
        }
        adminService.changePasswordByAccount(msg.getAccount(),msg.getPassword1());
        return ResponseEntity.status(HttpStatus.OK).body("修改成功！");
    }
    @PostMapping("/deleteAdmin")
    public ResponseEntity<Object> deleteAdmin(@RequestBody ChangeAdminMsg msg){
        boolean if_have_admin =adminService.searchAdminByAccount(msg.getAccount());
        if(!if_have_admin){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("输入的账号不存在"));
        }
        adminService.deleteAdminByAccount(msg.getAccount());
        return ResponseEntity.status(HttpStatus.OK).body("删除成功！");
    }
    @PostMapping("/addAdmin")
    public ResponseEntity<Object> addAdmin(@RequestBody ChangeAdminMsg msg){
        boolean if_have_admin =adminService.searchAdminByAccount(msg.getAccount());
        if(if_have_admin){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("该账户已被注册"));
        }
        if(msg.getPassword1().length()<6)
        {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("密码长度需至少为6"));
        }
        if(!Objects.equals(msg.getPassword1(), msg.getPassword2()))
        {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("两次输入的密码不一致！"));
        }
        adminService.addAdmin(msg.getAccount(),msg.getPassword1());
        return ResponseEntity.status(HttpStatus.OK).body("添加成功！");
    }
}
