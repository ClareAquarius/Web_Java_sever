package com.example.generator.controller;

import com.example.generator.entity.Pclike;
import com.example.generator.entity.Plike;
import com.example.generator.entity.User;
import com.example.generator.entity.message.UpdatePclikeMsg;
import com.example.generator.service.IPclikeService;
import com.example.generator.service.IPcommentService;
import com.example.generator.service.IUserService;
import com.example.generator.service.impl.PclikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
public class PclikeController {
    @Autowired
    private IPclikeService pclikeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPcommentService pcommentService;
    @RequestMapping("/updatePcommentLike")
    public ResponseEntity<Object> updatePcommentLike(@RequestBody UpdatePclikeMsg msg){
        User user = userService.getUserByPhone(msg.getUserTelephone());
        if(user==null)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("该用户不存在");
        }
        System.out.println(msg.isLiked());
        boolean if_like_before = msg.isLiked();

        // 点赞--增加对应Pcomment的like，添加pclike表单
        if (!if_like_before) {
            Pclike pclike = new Pclike();
            pclike.setPctargetid(msg.getPcommentID());
            pclike.setUserid(user.getUserid());
            pclikeService.insertLike(pclike);
            pcommentService.pcAddLikeCount(msg.getPcommentID());
        }
        // 取赞--减少对应Pcomment的like，删除pclike表单
        else
        {
            pclikeService.deletetpcLike(user.getUserid(),msg.getPcommentID());
            pcommentService.pcSubLikeCount(msg.getPcommentID());
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
