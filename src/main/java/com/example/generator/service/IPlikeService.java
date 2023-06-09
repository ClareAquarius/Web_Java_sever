package com.example.generator.service;

import com.example.generator.entity.Plike;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.generator.entity.message.UpdateLikeMeg;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public interface IPlikeService extends IService<Plike> {


    void insertLike(Plike plike);
    void deletetLike(Integer userid, int postID);
    boolean searchIflike(Integer userid,int PostID);

}
