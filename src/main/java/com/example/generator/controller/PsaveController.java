package com.example.generator.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Psave;
import com.example.generator.entity.User;
import com.example.generator.entity.message.UpdateLikeMeg;
import com.example.generator.entity.message.UpdateSaveMeg;
import com.example.generator.service.IPsaveService;
import com.example.generator.service.IUserService;
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
    @Autowired
    IUserService userService;

    // 收藏/取消收藏功能
    @RequestMapping("/updateSave")
    public ResponseEntity<Object> updateSave(@RequestBody UpdateSaveMeg meg) {
        User user = userService.getUserByPhone(meg.getUserTelephone());
        boolean if_save_before = meg.isSaved();
        if(user==null)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("该用户不存在");
        }
        // 收藏--添加psave表单
        if (if_save_before==false) {
            Psave psave=new Psave();
            psave.setPtargetid(meg.getPostID());
            psave.setUserid(user.getUserid());
            psaveService.insertpsave(psave);
        }
        // 取消收藏--删除对应的psave表单
        else
        {
            psaveService.deletepsave(user.getUserid(),meg.getPostID());
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);

    }

}
