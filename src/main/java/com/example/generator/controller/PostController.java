package com.example.generator.controller;

import com.example.common.Result;
import com.example.generator.entity.message.BrowseMeg;
import com.example.generator.entity.message.BrowseReturnMeg;
import com.example.generator.service.IPostService;
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

    // 这个brose就不用带 状态码 和 message了
    @RequestMapping("/browse")
    public ResponseEntity<List<BrowseReturnMeg>> browse(@RequestBody BrowseMeg meg) {
        String partition = meg.getpartition();
        String searchinfo = meg.getSearchinfo();
        String phone = meg.getUserTelephone();

        if (partition.equals("主页") || partition.isEmpty()) {
            if (searchinfo == null) {
                List<BrowseReturnMeg> data = postService.broseAll(partition, searchinfo, phone);
                return ResponseEntity.status(HttpStatus.OK).body(data);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }
}
