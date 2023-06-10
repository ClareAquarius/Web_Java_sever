package com.example.generator.controller;

import com.alibaba.fastjson2.JSON;
import com.example.common.Result;
import com.example.generator.entity.Notice;
import com.example.generator.entity.Pcomment;
import com.example.generator.entity.Post;
import com.example.generator.entity.User;
import com.example.generator.entity.message.NoticeReturnMsg;
import com.example.generator.service.INoticeService;
import com.example.generator.service.IPcommentService;
import com.example.generator.service.IPostService;
import com.example.generator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/api/auth")
public class NoticeController {
    @Autowired
    private INoticeService noticeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPcommentService pcommentService;

    // 得到用户相关的notice列表信息.根据token得到用户信息
    @GetMapping("/getNotice")
    public ResponseEntity<Object> getNotice(@RequestHeader("Authorization") String authorizationHeader){
        // Token解封装获得用户
        String token = authorizationHeader.replaceAll("Bearer ", "");
        User user=userService.getUserByToken(token);
        if(user==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("无效token");
        }

        //得到用户的notice数据信息,准备开始封装成消息
        List<Notice> noticeList=noticeService.getNoticeListByUserID(user.getUserid());
        List<NoticeReturnMsg> noticeReturnMsgs=new ArrayList<>();
        for (Notice notice : noticeList)
        {
            User sender=userService.getUserByID(notice.getSender());
            {
                Pcomment pcomment = pcommentService.getPcommentById(notice.getTarget());
                NoticeReturnMsg returnMsg=new NoticeReturnMsg(notice,user,sender,pcomment.getTime().toString());
                noticeReturnMsgs.add(returnMsg);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(noticeReturnMsgs);
    }

    @RequestMapping("/readNotice/{id}")
    public ResponseEntity<Object> readNotice(@PathVariable("id") int id) {
        boolean result=noticeService.read_notice(id);
        if(result)
        {
            return ResponseEntity.status(HttpStatus.OK).body("成功");
        }
        return ResponseEntity.status(HttpStatus.OK).body("更新notice失败");
    }
}
