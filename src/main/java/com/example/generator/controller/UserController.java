package com.example.generator.controller;

import com.example.common.Result;
import com.example.generator.entity.User;
import com.example.generator.entity.message.*;
import com.example.generator.service.IAdminService;
import com.example.generator.service.INoticeService;
import com.example.generator.service.IPcommentService;
import com.example.generator.service.IPostService;
import com.example.generator.service.ISueService;
import com.example.generator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
    @Autowired
    private IPostService postService;
    @Autowired
    private IPcommentService pcommentService;
    @Autowired
    private ISueService sueService;
    @Autowired
    private INoticeService noticeService;
    @Autowired
    private IAdminService adminService;
    // login登录--登录验证用户的账号和密码,并为用户生成token,存入redis
    @PostMapping("/login")
    public ResponseEntity<Result<Object>> login(@RequestBody User user){
        Object data=userService.login(user);
        if(data==null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("账号或密码错误"));
        } else if(data=="你的账号已被封禁") {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("你的账号已被封禁"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Result.success(data));
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
    
    @PostMapping("/userDelete")
    public ResponseEntity<Result<String>> userDelete(@RequestBody PhoneMeg phoneMeg) {
        User user = userService.getUserByPhone(phoneMeg.getPhone());
        String postDeleteData = postService.deletePostbyUserID(user.getUserid());
        String commentDeleteData = pcommentService.deleteCommentByUserID(user.getUserid());
        String sueDeleteData = sueService.deleteSuebyUserID(user.getUserid());
        String noticeDeleteData1 = noticeService.deleteNoticebySender(user.getUserid());
        String noticeDeleteData2 = noticeService.deleteNoticebyReceiver(user.getUserid());
        if (postDeleteData == "删除成功" && commentDeleteData  == "删除成功" && sueDeleteData == "删除成功" && noticeDeleteData1 == "删除成功" && noticeDeleteData2 == "删除成功") {
            String data= userService.userDelete(phoneMeg.getPhone());
            if(data == "注销成功")
            {
                return ResponseEntity.status(HttpStatus.OK).body(Result.success(data));
            }
            else
            {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail(data));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("注销失败"));
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
        Integer userid= userService.getUserIdByToken(modifiedString);
//根据用户ID更新用户信息
        String result = userService.updateUser(userid, updateUserInfoMeg.getAvatarURL(), updateUserInfoMeg.getEmail(), updateUserInfoMeg.getName());
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

    @PostMapping("showUsers")
    public ResponseEntity<Result<Object>> showUsers(@RequestBody UserDetailsMeg userDetailsMeg)
    {
        List<UserDetails> list=userService.getUserDetailsList(userDetailsMeg.getName(), userDetailsMeg.getPhone());
        return ResponseEntity.status(HttpStatus.OK).body(Result.success(list));
    }

}
