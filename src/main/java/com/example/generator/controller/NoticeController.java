package com.example.generator.controller;

import com.example.generator.service.INoticeService;
import com.example.generator.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class NoticeController {
    @Autowired
    private INoticeService noticeService;


}
