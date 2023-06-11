package com.example.generator.service;

import com.example.generator.entity.Pclike;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public interface IPclikeService extends IService<Pclike> {

    boolean searchIflike(Integer userid, Integer pcommentid);

    void insertLike(Pclike pclike);

    void deletetpcLike(Integer userid, int pcommentID);
}
