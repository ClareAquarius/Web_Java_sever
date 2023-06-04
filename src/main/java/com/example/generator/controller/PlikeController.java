package com.example.generator.controller;

import com.example.generator.entity.message.BrowseMeg;
import com.example.generator.entity.message.BrowseReturnMeg;
import com.example.generator.entity.message.UpdateLikeMeg;
import com.example.generator.service.IPlikeService;
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

    @RequestMapping("/updateLike")
    public ResponseEntity<Object> browse(@RequestBody UpdateLikeMeg meg) {
        if(plikeService.updateplike(meg))
        {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
}
