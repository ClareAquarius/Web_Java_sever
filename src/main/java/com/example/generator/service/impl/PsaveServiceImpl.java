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
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean updatepsave(UpdateSaveMeg meg) {
        User user = getUserByPhone(meg.getUserTelephone());
        boolean if_save_before = meg.isSaved();
        Integer result;
        // 收藏--添加psave表单
        if (if_save_before==false) {
            Psave psave=new Psave();
            psave.setPtargetid(meg.getPostID());
            psave.setUserid(user.getUserid());
            result=this.baseMapper.insert(psave);
        }
        // 取消收藏--删除对应的psave表单
        else
        {
            LambdaQueryWrapper<Psave> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Psave::getUserid, user.getUserid());
            wrapper.eq(Psave::getPtargetid, meg.getPostID());
            result = this.baseMapper.delete(wrapper);
        }
        return result > 0;
    }

    private User getUserByPhone(String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, phone);
        return userMapper.selectOne(wrapper);
    }
}
