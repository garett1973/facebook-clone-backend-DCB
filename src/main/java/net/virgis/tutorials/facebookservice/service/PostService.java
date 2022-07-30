package net.virgis.tutorials.facebookservice.service;


import net.virgis.tutorials.facebookservice.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    Post addPost(Post post) throws Exception;

    List<Post> getPosts();
}
