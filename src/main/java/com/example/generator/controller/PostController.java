package com.example.generator.controller;

import com.example.common.Result;
import com.example.generator.entity.User;
import com.example.generator.entity.message.BrowseMeg;
import com.example.generator.entity.message.BrowseReturnMeg;
import com.example.generator.service.IPostService;
import com.example.generator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RestController
@RequestMapping("/api/auth")
public class PostController {
    @Autowired
    private IPostService postService;
    @Autowired
    private IUserService userService;
    // 这个brose就不用带 状态码 和 message了
    // browse--浏览文章--根据user
    @RequestMapping("/browse")
    public ResponseEntity<List<BrowseReturnMeg>> browse(@RequestBody BrowseMeg meg) {
        String partition = meg.getpartition();
        String searchinfo = meg.getSearchinfo();
        User user = userService.getUserbyPhone(meg.getUserTelephone());


        if (partition.equals("主页") || partition.isEmpty()) {
            // searchinfo是用于搜索帖子的关键词或信息的变量
            if (searchinfo == null)
            {
                List<BrowseReturnMeg> data = postService.broseAll(partition, searchinfo, user);
                return ResponseEntity.status(HttpStatus.OK).body(data);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }
}
