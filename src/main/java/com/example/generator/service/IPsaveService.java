package com.example.generator.service;

import com.example.generator.entity.Psave;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.generator.entity.message.UpdateLikeMeg;
import com.example.generator.entity.message.UpdateSaveMeg;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public interface IPsaveService extends IService<Psave> {

    void insertpsave(Psave psave);

    void deletepsave(Integer userid, int postID);
}
