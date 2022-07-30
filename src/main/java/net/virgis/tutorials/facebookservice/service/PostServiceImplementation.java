package net.virgis.tutorials.facebookservice.service;

import net.virgis.tutorials.facebookservice.entity.PostEntity;
import net.virgis.tutorials.facebookservice.model.Post;
import net.virgis.tutorials.facebookservice.repository.PostEntityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {

    private final PostEntityRepository postEntityRepository;

    public PostServiceImplementation(PostEntityRepository postEntityRepository) {
        this.postEntityRepository = postEntityRepository;
    }

    @Override
    public Post addPost(Post post) throws Exception {
        try {
            PostEntity postEntity = new PostEntity();
            BeanUtils.copyProperties(post, postEntity);

            if (post.getFile() != null && !post.getFile().equalsIgnoreCase("null")) {
                postEntity.setImage(post.getFile());
            } else {
                postEntity.setImage(null);
            }

            postEntity = postEntityRepository.save(postEntity);
            post.setId(postEntity.getId());
            post.setFile(null);
            post.setImage(postEntity.getImage());
        } catch (Exception e) {
            throw new Exception("Error while adding post" + e.getMessage());
        }
        return post;
    }

    @Override
    public List<Post> getPosts() {
        List<PostEntity> postEntities = postEntityRepository.findAll();
        List<Post> posts = new ArrayList<>();
        posts = postEntities.stream()
                .map((postEntity) ->
                        Post.builder()
                                .id(postEntity.getId())
                                .timeStamp(postEntity.getTimeStamp())
                                .post(postEntity.getPost())
                                .name(postEntity.getName())
                                .email(postEntity.getEmail())
                                .image(postEntity.getImage())
                                .profilePic(postEntity.getProfilePic())
                                .build())
                .collect(Collectors.toList());
        return posts;
    }
}
