package com.example.generator.controller;

import com.example.utils.TextModeration;
import com.example.generator.entity.Post;
import com.example.generator.entity.message.BrowseMeg;
import com.example.generator.entity.message.BrowseReturnMeg;
import com.example.generator.entity.message.PostMeg;
import com.example.generator.service.IPostService;
import com.example.generator.service.IUserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
public class PostController {
    @Autowired
    private IPostService postService;
    @Autowired
    private IUserService userService;

    // browse--浏览文章--根据phone电话号码，searchinfo搜索关键词，partition主题来传送文章列表
    // 注意这个 brose方法返回值，没有带 状态码 和 message了，直接返回data数据
    // 注意这个 BrowseReturnMeg相比post增加了 isliked是否点赞 和 issaved是否收藏的数据 需要在别的数据表中取得
    @RequestMapping("/browse")
    public ResponseEntity<List<BrowseReturnMeg>> browse(@RequestBody BrowseMeg meg) {
        String partition = meg.getpartition();
        String searchinfo = meg.getSearchinfo();

        if (partition.equals("主页") || partition.isEmpty()) {
            // searchinfo是用于搜索帖子的关键词或信息的变量
            if (searchinfo == null)
            {
                List<BrowseReturnMeg> data = postService.broseAll(partition, searchinfo, meg.getUserTelephone());
                return ResponseEntity.status(HttpStatus.OK).body(data);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<String> addPost(@RequestBody PostMeg postMeg) {
        String content = postMeg.getContent();
        String partition = postMeg.getPartition();
        String title = postMeg.getTitle();
        String userTelephone = postMeg.getUserTelephone();
//
        Integer userid = userService.getUserIdByPhone(userTelephone);

        Post post = new Post(userid, title, content, partition);
//
        int result = postService.addPost(post);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("发布成功");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("发布失败");
        }
//        return ResponseEntity.status(HttpStatus.OK).body(userid.toString());
    }

    @PostMapping("/RichEditorUploadImage")
    public ResponseEntity<Map<String, Object>> richEditorUploadImage(MultipartHttpServletRequest request) {
        try {
            List<MultipartFile> files = request.getFiles("wangeditor-uploaded-image");
            List<String> urls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String ext = FilenameUtils.getExtension(file.getOriginalFilename());
                    String name = "image_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                    String newFilename = name + "." + ext;
//                    String newFilename = name + ".jpg";
//                    String dst = "./static/images/PostImages/" + newFilename;
                    String path = request.getServletContext().getRealPath("/images/PostImages/");
//                    out.println("path= "+path);
                    String dst = path + newFilename;
//                    out.println(dst);
                    String fileUrl = "/static/images/PostImages/" + newFilename;
                    urls.add(fileUrl);
                    File destFile = new File(dst);
                    if (!destFile.getParentFile().exists()) {
                        destFile.getParentFile().mkdirs();
                    }
                    file.transferTo(destFile);
                }
            }
            return ResponseEntity.ok(new HashMap<String, Object>() {{
                put("errno", 0);
                put("data", new HashMap<String, Object>() {{
                    put("url", urls);
                }});
            }});
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, Object>() {{
                put("errno", 1);
                put("message", "上传失败");
            }});
        }
    }
}
