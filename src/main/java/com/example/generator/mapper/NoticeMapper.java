package com.example.generator.mapper;

import com.example.generator.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    @Insert("INSERT INTO notice( receiver, sender, type, ntext, is_read, postid, target) VALUES(#{receiver}, #{sender}, #{type}, #{ntext}, #{isRead}, #{postid}, #{target})")
    @Options(useGeneratedKeys = true, keyProperty = "noticeid")
    int insert(Notice notice);

}
