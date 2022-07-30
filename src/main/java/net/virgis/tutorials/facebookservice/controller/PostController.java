package net.virgis.tutorials.facebookservice.controller;


import net.virgis.tutorials.facebookservice.model.Post;
import net.virgis.tutorials.facebookservice.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/post")
@CrossOrigin(value = "http://localhost:3000")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService, PostService postService1) {
        this.postService = postService1;
    }

    @PostMapping
    public Post addPost(@RequestParam Map<String, String> requestParams) throws Exception {
        String strpost = requestParams.get("post");
        String name = requestParams.get("name");
        String email = requestParams.get("email");
        String file = requestParams.get("file");
        String profilePic = requestParams.get("profilePic");
        Post post = Post.builder()
                .file(file)
                .post(strpost)
                .name(name)
                .email(email)
                .profilePic(profilePic)
                .timeStamp(new Date().toString())
                .build();

        post = postService.addPost(post);
        return post;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }
}
