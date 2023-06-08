package com.example.generator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.generator.service.ISueService;
import com.example.generator.service.IUserService;
import com.example.generator.entity.*;
import com.example.generator.entity.message.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.common.Result;
import java.util.Map;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
public class SueController {
    @Autowired
    private ISueService sueService;
    @Autowired
    private IUserService userService;
    @PostMapping("/submitReport")
    public ResponseEntity<Object> sue(@RequestBody SueMeg sueMeg) {
        int userid = userService.getUserByPhone(sueMeg.getUserTelephone()).getUserid();
        String targettype = "帖子";
        LocalDateTime time = LocalDateTime.now();
        String status = "wait";
        Boolean finish = false;
        Sue sue = new Sue(targettype, sueMeg.getTargetID(), userid, sueMeg.getReason(), time, status, finish);
        String meg = sueService.submitSue(sue);
        return ResponseEntity.status(HttpStatus.OK).body(meg);
    }
}
