package com.example.generator.controller;

import com.example.generator.entity.message.UpdateLikeMeg;
import com.example.generator.entity.message.UpdateSaveMeg;
import com.example.generator.service.IPsaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class PsaveController {
    @Autowired
    IPsaveService psaveService;

    // 收藏/取消收藏功能
    @RequestMapping("/updateSave")
    public ResponseEntity<Object> browse(@RequestBody UpdateSaveMeg meg) {
        if(psaveService.updatepsave(meg))
        {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

}
