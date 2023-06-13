package com.example.generator.controller;

import com.example.generator.service.INoticeService;
import com.example.generator.service.IPostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.generator.service.ISueService;
import com.example.generator.service.IUserService;
import com.example.generator.entity.*;
import com.example.generator.entity.message.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.common.Result;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    private IPostService postService;
    @Autowired
    private INoticeService noticeService;
    @PostMapping("/submitReport")
    public ResponseEntity<Object> sue(@RequestBody SueMeg sueMeg) {
        int userid = userService.getUserByPhone(sueMeg.getUserTelephone()).getUserid();
        String targettype = "post";
        LocalDateTime time = LocalDateTime.now();
        String status = "wait";
        Boolean finish = false;
        Sue sue = new Sue(targettype, sueMeg.getTargetID(), userid, sueMeg.getReason(), time, status, finish);
        String meg = sueService.submitSue(sue);
        return ResponseEntity.status(HttpStatus.OK).body(meg);
    }

    @GetMapping("/getSues")
    public ResponseEntity<Object> getSues(@RequestHeader("Authorization") String authorizationHeader)
    {
        List<Sue> sueList=sueService.getUnreadSuelist();
        List<SueReturnMsg> sueReturnMsgs=new ArrayList<>();
        for(Sue sue:sueList)
        {
            Post post=postService.getPostByPostID(sue.getPtargetid());
            SueReturnMsg sueReturnMsg=new SueReturnMsg(sue,post.getTitle(),post.getContent());
            sueReturnMsgs.add(sueReturnMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(sueReturnMsgs);
    }
    @PostMapping("/violation")
    public Result<Object> violation(@RequestBody SueActionMSg msg)
    {
        System.out.println(msg.getSueID());
        Sue sue=sueService.getSuebyID(msg.getSueID());
        Integer postuserid=postService.getUseridByPostid(sue.getPtargetid());
        // 对用户进行处罚
        Integer num=userService.addUserspunishment(postuserid);
        LocalDate banTime = userService.getUserByID(postuserid).getBanTime();
        // 标记sue已处理,并标记处理结果
        sueService.manageSue("OK",msg.getSueID());
        // 给用户通知
        Post post=postService.getPostByPostID(sue.getPtargetid());
        String punishcontent="这是第"+num+"次违规发言/发帖,"+"禁止登录的截止日期为"+banTime.toString();
        noticeService.addNotice(0,postuserid,post.getPostid(),"punish",punishcontent,post.getPostid());
        return Result.success("已处理");
    }
    @PostMapping("/noViolation")
    public Result<Object> noviolation(@RequestBody SueActionMSg msg)
    {
        Sue sue=sueService.getSuebyID(msg.getSueID());
        sueService.manageSue("nosin",msg.getSueID());
        return Result.success("已处理");
    }
}
