package com.example.generator.controller;

import com.example.common.Result;
import com.example.generator.entity.Post;
import com.example.generator.entity.User;
import com.example.generator.entity.message.*;
import com.example.generator.service.IPlikeService;
import com.example.generator.service.IPostService;
import com.example.generator.service.IPsaveService;
import com.example.generator.service.IUserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.utils.TextModeration.getSuggestion;
import static java.lang.System.out;


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
    @Autowired
    private IPlikeService plikeService;
    @Autowired
    private IPsaveService psaveService;

    // browse--浏览文章--根据phone电话号码，searchinfo搜索关键词，partition主题来传送文章列表
    // 注意这个 brose方法返回值，没有带 状态码 和 message了，直接返回data数据
    // 注意这个 BrowseReturnMeg相比post增加了 isliked是否点赞 和 issaved是否收藏的数据 需要在别的数据表中取得
    @RequestMapping("/browse")
    public ResponseEntity<List<BrowseReturnMeg>> browse(@RequestBody BrowseMeg meg) {
        String partition = meg.getpartition();
        String searchinfo = meg.getSearchinfo();
        List<BrowseReturnMeg> data = postService.broseAll(partition, searchinfo, meg.getUserTelephone());
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<Result<String>> addPost(@RequestBody PostMeg postMeg) {
        String content = postMeg.getContent();
        String partition = postMeg.getPartition();
        String title = postMeg.getTitle();
        String userTelephone = postMeg.getUserTelephone();

        String flag1 = getSuggestion(title);
        String flag2 = getSuggestion(content);
        if(!(flag1.equals("Pass") && flag2.equals("Pass"))) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("含不良信息，请修改！"));
        }
//
        User user = userService.getUserByPhone(userTelephone);
        Post post = new Post(user.getUserid(), title, content, partition);
//
        int result = postService.addPost(post);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(Result.success("发布成功"));
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Result.fail("发布失败"));
        }
//        return ResponseEntity.status(HttpStatus.OK).body(userid.toString());
    }

    @PostMapping("/RichEditorUploadImage")
    public ResponseEntity<Map<String, Object>> richEditorUploadImage(MultipartHttpServletRequest request) {
        try {
        String projectRootPath = System.getProperty("user.dir");
        out.println("projectRootPath= "+projectRootPath);
            List<MultipartFile> files = request.getFiles("wangeditor-uploaded-image");
            List<String> urls = new ArrayList<>();

            String basePath = projectRootPath+"/src/main/resources/static/images/PostImages/";


            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String ext = FilenameUtils.getExtension(file.getOriginalFilename());
                    String name = "image_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                    String newFilename = name + "." + ext;
//                    String dst = "./static/images/PostImages/" + newFilename;
//                    String path = request.getServletContext().getRealPath("/images/PostImages/");
                    // 这个path大概= C:\Users\liuy\AppData\Local\Temp\tomcat-docbase.8080.2478560337122190439\images\PostImages\（8080.后面的数字随机不定）
//                    out.println("path= "+path);
//                    String dst = path + newFilename;

                    String dst = basePath + newFilename;
                    out.println("dst= "+dst);
//                    String fileUrl = "/static/images/PostImages/" + newFilename;
                    String fileUrl = "/images/PostImages/" + newFilename;
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
        }
        catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, Object>() {{
                put("errno", 1);
                put("message", "上传失败");
            }});
        }
    }

    @PostMapping("/showDetails")
    public ResponseEntity<Object> showDetails(@RequestBody showPostDetailsMsg msg)
    {
        User user=userService.getUserByPhone(msg.getUserTelephone());
        Post post=postService.getPostByPostID(msg.getPostID());
        User author = userService.getUserByID(post.getUserid());
        if(user==null)
        {
            return  ResponseEntity.status(HttpStatus.OK).body("该用户不存在");
        }
        boolean like=plikeService.searchIflike(user.getUserid(),msg.getPostID());
        boolean save=psaveService.searchIfsave(user.getUserid(),msg.getPostID());
        showPostDetailsReturnMsg returnMsg=new showPostDetailsReturnMsg(post,author,like,save);
        return ResponseEntity.status(HttpStatus.OK).body(returnMsg);
    }

    @PostMapping("/deletePost")
    public ResponseEntity<Result<String>> deletePost(@RequestBody DeleteMeg deletemeg) {
        String msg = postService.deletePost(deletemeg.getPostID());
        return ResponseEntity.status(HttpStatus.OK).body(Result.success(msg));
    }
}
