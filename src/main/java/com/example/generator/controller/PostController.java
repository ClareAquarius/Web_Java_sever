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

    // browse--浏览文章--根据phone电话号码，searchinfo搜索关键词，partition主题来传送文章列表
    // 注意这个 brose方法返回值，没有带 状态码 和 message了，直接返回data数据
    // 注意这个 BrowseReturnMeg相比post增加了 isliked是否点赞 和 issaved是否收藏的数据 需要在别的数据表中取得
    @RequestMapping("/browse")
    public ResponseEntity<List<BrowseReturnMeg>> browse(@RequestBody BrowseMeg meg) {
        String partition = meg.getpartition();
        String searchinfo = meg.getSearchinfo();

        if (partition.equals("主页") || partition.isEmpty()) {
            // searchinfo是用于搜索帖子的关键词或信息的变量
            if (searchinfo == null)
            {
                List<BrowseReturnMeg> data = postService.broseAll(partition, searchinfo, meg.getUserTelephone());
                return ResponseEntity.status(HttpStatus.OK).body(data);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }
}
