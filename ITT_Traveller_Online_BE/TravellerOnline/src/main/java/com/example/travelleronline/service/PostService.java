package com.example.travelleronline.service;

import com.example.travelleronline.Util;
import com.example.travelleronline.model.DTOs.post.CreatePostDTO;
import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.repositories.PostRepository;
import com.example.travelleronline.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class PostService extends AbstractService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;//data base

    //add post
    public PostInfoDTO addPost(CreatePostDTO newPostDTO, int loggedId, List<MultipartFile> images, MultipartFile video){//todo after service
        //1. validate post info with static validation service methods
        //todo - like User attributes validation

        /*
        - 1.1. title is shorter than 5 symbols -> Bad request : msg "title is mandatory"
        - 1.2. description is shorter than 5 symbols -> Bad request : msg "bad request message"
        - 1.3. title and description have to be not null (mandatory field) -> Bad request : msg "bad request message"
        - 1.4. validation for images (check if images exist) todo
         */

        //1.1
        //get object from newPostDTO object
        /*
        if (!validator.methodName1(object)){
            throw new BadRequestException("Title should be at least one symbol long.");//todo
        }

        //1.2
        //get object from newPostDTO object
        if (!validator.methodName2(object)){
            throw new BadRequestException("Description is shorter than 5 symbols.");//todo
        }

        //1.3
        //get object from newPostDTO object
        if (!validator.methodName3(object1, object2)){
            throw new BadRequestException("No image uploaded");//todo
        }

        //1.4
        //get object from newPostDTO object
        if (!validator.methodName4(object)){
            throw new BadRequestException("insert appropriate message..");//todo
        }
        */
        //2. get logged user

        //todo for Pesho: findBy(int id) need to return Optional<User> for
        // the orElseThrow() method to compile
        User user = userRepository.findById(loggedId).orElseThrow(() -> new RuntimeException("User not found."));

        //3. create post entity
        Post post = mapper.map(newPostDTO, Post.class);

        //4. set owner
        post.setOwner(user);

        //5. save to db
        postRepository.save(post);//save entity

        //6. return dto
        //map this entity to a dto and return it
        PostInfoDTO p = mapper.map(post, PostInfoDTO.class);
        return p;

        /*
        //stream for each image upload
        //save each each image (postId + url from upload method)
        //check if image upload is null

        //upload single video
        //save
        MediaService.upload();//insert parameters into upload method
         */
<<<<<<< HEAD

=======
        //MediaService.upload();//insert parameters into upload method
        Post p = mapper.map(newPost, Post.class);
        postRepository.save(p);
        return mapper.map(p, PostInfoDTO.class);
>>>>>>> master
    }//todo resolve
=======
    }
>>>>>>> f232377eec16f745e79bb65612b867f2ea4df1fd

    //get post by post_id
    public PostInfoDTO getPostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(()->new BadRequestException(Util.POST_NOT_FOUND));//todo resolve
        return PostInfoDTO
                .builder()
                .id(post.getId())
                .owner(post.getOwner())//todo
                .title(post.getTitle())
                .//todo
    }

    //get posts by user_id
<<<<<<< HEAD
    public List<PostInfoDTO> getUserPosts(HttpSession session) {
        int userId = getUserId(session);
        //todo
=======
    public List<PostInfoDTO> getUserPosts(int userID) {
>>>>>>> f232377eec16f745e79bb65612b867f2ea4df1fd
        return null;
    }

    //get all posts (for newsfeed)
    public List<PostInfoDTO> getPosts() {
        return null;
    }

    //update post
    public PostInfoDTO updatePost(int postId, PostInfoDTO postInfoDTO) {
        return null;
    }

    //delete post
    public void deletePost(int id) {
    }
}
