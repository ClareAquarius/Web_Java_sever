package com.example.generator.mapper;

import com.example.generator.entity.Pcomment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public interface PcommentMapper extends BaseMapper<Pcomment> {
    @Insert("INSERT INTO pcomment(userid, ptargetid, pctext) VALUES (#{userid}, #{ptargetid}, #{pctext})")
    @Options(useGeneratedKeys = true, keyProperty = "pcommentid")
    int insertPcomment(Pcomment pcomment);
}
