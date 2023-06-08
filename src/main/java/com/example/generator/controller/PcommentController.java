package com.example.generator.controller;

import com.example.generator.entity.message.PostPcommentMsg;
import com.example.generator.entity.message.PostPcommentReturnMsg;
import com.example.generator.service.IPcommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class PcommentController {
    @Autowired
    private IPcommentService pcommentService;

    @RequestMapping("postPcomment")
    public ResponseEntity<Object> pcomment(@RequestBody PostPcommentMsg msg){
        PostPcommentReturnMsg result=pcommentService.addpcomment(msg);
        if(result)
        {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
