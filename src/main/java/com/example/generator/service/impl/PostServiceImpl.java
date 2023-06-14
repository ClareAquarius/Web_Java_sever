package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.generator.entity.Plike;
import com.example.generator.entity.Post;
import com.example.generator.entity.Psave;
import com.example.generator.entity.User;
import com.example.generator.entity.message.BrowseReturnMeg;
import com.example.generator.mapper.PlikeMapper;
import com.example.generator.mapper.PostMapper;
import com.example.generator.mapper.PsaveMapper;
import com.example.generator.mapper.UserMapper;
import com.example.generator.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {
    @Autowired
    private PlikeMapper plikeMapper;
    @Autowired
    private PsaveMapper psaveMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;

    @Override
    public List<BrowseReturnMeg> broseAll(String partition, String searchinfo, String userTelephone) {
        List<Post> data = new ArrayList<Post>();
        if (partition.equals("主页") || partition.isEmpty()){
            if(searchinfo == null){
                // 获得帖子全部Post数据
                data = this.list();
            } else {
                // 按照 searchinfo 进行模糊查询，获取符合条件的所有帖子
                List<Post> TemData = this.list();
                for (Post post : TemData) {
                    // 使用SQL的LIKE语句进行模糊查询
                    if (post.getTitle().contains(searchinfo) || post.getContent().contains(searchinfo)) {
                        // 如果标题或内容包含搜索信息，则将该帖子添加到筛选列表中
                        data.add(post);
                    }
                }
            }
        } else {
            // 按照 partition 进行精确查询，获取数据库中partition对应的所有帖子
            List<Post> TemData = this.list();
                for (Post post : TemData) {
                    // 使用SQL的LIKE语句进行模糊查询
                    if (post.getPostPartition().equals(partition)) {
                        // 如果标题或内容包含搜索信息，则将该帖子添加到筛选列表中
                        data.add(post);
                    }
                }
        }
        // 根据phone获得user信息
        LambdaQueryWrapper<User> wrapper_user=new LambdaQueryWrapper<>();
        wrapper_user.eq(User::getPhone,userTelephone);
        User user=userMapper.selectOne(wrapper_user);

        // 开始对返回信息进行格式设置
        List<BrowseReturnMeg> resultList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            Post post = data.get(i);
            BrowseReturnMeg modifiedPost = new BrowseReturnMeg();
            modifiedPost.setPostID(post.getPostid());
            modifiedPost.setComment(post.getCommentNum());
            modifiedPost.setContent(post.getContent());
            modifiedPost.setLike(post.getLikeNum());
            modifiedPost.setPostTime(post.getPostTime().toString());
            modifiedPost.setTitle(post.getTitle());

            User author = userService.getUserByID(post.getUserid());

            modifiedPost.setUserName(author.getName()); // 设置用户名
            modifiedPost.setUserTelephone(author.getPhone()); // 设置用户电话

            // 查询plikeMapper，查看该用户是否点赞
            LambdaQueryWrapper<Plike> wrapper_like = new LambdaQueryWrapper<>();
            wrapper_like.eq(Plike::getUserid, user.getUserid());
            wrapper_like.eq(Plike::getPtargetid, post.getPostid());
            Plike plike=plikeMapper.selectOne(wrapper_like);
            if(plike!=null)
            {
               modifiedPost.setIsLiked(true);
            }
            else
            {
                modifiedPost.setIsLiked(false);
            }

            // 查询psaveMapper，查看该用户是否收藏
            LambdaQueryWrapper<Psave> wrapper_save = new LambdaQueryWrapper<>();
            wrapper_save.eq(Psave::getUserid, user.getUserid());
            wrapper_save.eq(Psave::getPtargetid, post.getPostid());
            Psave psave=psaveMapper.selectOne(wrapper_save);
            if(psave!=null)
            {
                modifiedPost.setIsSaved(true);
            }
            else
            {
                modifiedPost.setIsSaved(false);
            }
            // 把帖子详情插入返回结果
            resultList.add(modifiedPost);
        }
        return resultList;
    }

    @Override
    public Integer addPost(Post post) {

        int result = this.baseMapper.insert(post);
        if (result > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String deletePostbyUserID(Integer userID) {
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getUserid, userID);
        int result = this.baseMapper.delete(wrapper);
        if (result >= 0 ) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @Override
    public String deletePost(Integer postId) {
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getPostid, postId);
        int result = this.baseMapper.delete(wrapper);
        if (result > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @Override
    public void postAddLikeCount(int postID) {
        LambdaUpdateWrapper<Post> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Post::getPostid, postID).setSql("like_num = like_num  + 1");
        this.update(null, updateWrapper);
    }

    @Override
    public void postsubLikeCount(int postID) {
        LambdaUpdateWrapper<Post> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Post::getPostid, postID).setSql("like_num = like_num  - 1");
        this.update(null, updateWrapper);
    }

    @Override
    public Post getPostByPostID(int postID) {
        LambdaQueryWrapper<Post> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Post::getPostid,postID);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public void addPostCommit(int postID) {
        LambdaUpdateWrapper<Post> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Post::getPostid, postID).setSql("comment_num = comment_num  + 1");
        this.update(null, updateWrapper);
    }

    @Override
    public int getUseridByPostid(int postID) {
        LambdaQueryWrapper<Post> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Post::getPostid,postID);
        return this.baseMapper.selectOne(wrapper).getUserid();
    }


}

