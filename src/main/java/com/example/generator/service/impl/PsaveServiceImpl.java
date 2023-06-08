package com.example.generator.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Psave;
import com.example.generator.entity.User;
import com.example.generator.entity.message.UpdateSaveMeg;
import com.example.generator.mapper.PsaveMapper;
import com.example.generator.mapper.UserMapper;
import com.example.generator.service.IPsaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
@Service
public class PsaveServiceImpl extends ServiceImpl<PsaveMapper, Psave> implements IPsaveService {

    @Override
    public void insertpsave(Psave psave) {
        this.baseMapper.insert(psave);
    }

    @Override
    public void deletepsave(Integer userid, int postID) {
        LambdaQueryWrapper<Psave> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Psave::getUserid, userid);
        wrapper.eq(Psave::getPtargetid, postID);
        this.baseMapper.delete(wrapper);
    }

}
