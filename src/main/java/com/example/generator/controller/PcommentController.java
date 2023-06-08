package com.example.generator.controller;

import com.example.generator.entity.Ccomment;
import com.example.generator.entity.Pcomment;
import com.example.generator.entity.User;
import com.example.generator.entity.message.PostPcommentMsg;
import com.example.generator.entity.message.PostPcommentReturnMsg;
import com.example.generator.entity.message.showPostDetailsMsg;
import com.example.generator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02x`
 */
@RestController
@RequestMapping("/api/auth")
public class PcommentController {
    @Autowired
    private IPcommentService pcommentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPclikeService pclikeService;
    @Autowired
    private IPostService postService;
    @RequestMapping("/showPcomments")
    public ResponseEntity<Object> showPcomments(@RequestBody showPostDetailsMsg msg){
        User user=userService.getUserByPhone(msg.getUserTelephone());
        if(user==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("电话号码错误");
        }
        List<Pcomment> pcommentList = pcommentService.searchPcommentsListByPostID(msg.getPostID());
        List<PostPcommentReturnMsg> returnMsgList=new ArrayList<>();

        for(Pcomment pcomment:pcommentList){
            // *****这里先省略 评论的评论 获得方式****
            List<Ccomment> list=new ArrayList<>();
            // 暂时还没写
            // 根据用户id和一级评论的id来查询用户是否点赞
            boolean like=pclikeService.searchIflike(user.getUserid(),pcomment.getPcommentid());
            PostPcommentReturnMsg m=new PostPcommentReturnMsg(pcomment,user,like,list);
            returnMsgList.add(m);
        }

        return ResponseEntity.status(HttpStatus.OK).body(returnMsgList);
    }

    // 发表评论,增加pcomment表项,并增加对应post的comment数量
    @RequestMapping("/postPcomment")
    public ResponseEntity<Object> pcomment(@RequestBody PostPcommentMsg msg){
        User user=userService.getUserByPhone(msg.getUserTelephone());
        if(user==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("电话号码错误");
        }
        if(msg.getContent().isEmpty())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("评论不能为空");
        }
        Pcomment pcomment=new Pcomment(user.getUserid(),msg.getPostID(),msg.getContent());
        pcommentService.addPcomment(pcomment);
        postService.addPostCommit(msg.getPostID());
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }
}
