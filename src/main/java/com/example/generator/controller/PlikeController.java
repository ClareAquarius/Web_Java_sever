package com.example.generator.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Plike;
import com.example.generator.entity.User;
import com.example.generator.entity.message.BrowseMeg;
import com.example.generator.entity.message.BrowseReturnMeg;
import com.example.generator.entity.message.UpdateLikeMeg;
import com.example.generator.service.IPlikeService;
import com.example.generator.service.IPostService;
import com.example.generator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
public class PlikeController {
    @Autowired
    private IPlikeService plikeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostService PostService;

    // 点赞/取赞功能
    // 点赞增加post的like数 并在plike添加表项
    // 取赞取消post的like数 并在plike中删除对应表项
    @RequestMapping("/updateLike")
    public ResponseEntity<Object> updateLike(@RequestBody UpdateLikeMeg meg) {
        User user = userService.getUserByPhone(meg.getUserTelephone());
        if(user==null)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("该用户不存在");
        }
        boolean if_like_before = meg.isLiked();

        // 点赞--增加对应post的like，添加clike表单
        if (!if_like_before) {
            Plike plike = new Plike();
            plike.setPtargetid(meg.getPostID());
            plike.setUserid(user.getUserid());
            plikeService.insertLike(plike);
            PostService.postAddLikeCount(meg.getPostID());
        }

        // 取赞--减少对应post的like，删除clike表单
        else
        {
            plikeService.deletetLike(user.getUserid(),meg.getPostID());
            PostService.postsubLikeCount(meg.getPostID());
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
