package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.post.CreatePostDTO;
import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.model.entities.Image;
import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.repositories.ImageRepository;
import com.example.travelleronline.model.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService extends AbstractService{

    @Autowired
    private PostRepository postRepository;//data base
    @Autowired
    private CategoryService categoryService;

    @Autowired ImageRepository imageRepository;

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

//        //MediaService.upload();//insert parameters into upload method
//        Post p = mapper.map(newPost, Post.class);
//        postRepository.save(p);
//        return mapper.map(p, PostInfoDTO.class);

    }//todo resolve
    //todo Pageable
    public List<PostInfoDTO> getPosts() {//todo criteria
        return null;
//        List<Post> posts = postRepository.getAll();
//        return posts
//                .stream()
//                .map(p -> mapper.map(p, PostInfoDTO.class))
//                .collect(Collectors.toList());
    }
    public PostInfoDTO getPostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(()->new BadRequestException("Post does not exist."));
        PostInfoDTO postInfoDTO = mapper.map(post, PostInfoDTO.class);
        return postInfoDTO;
    }
    //todo Pageable + connect to comments: OneToMany List<Comments>
    public List<PostInfoDTO> getUserPosts(int loggedId) {
        List<Post> posts = postRepository.findByOwnerId(loggedId);
        return posts
                .stream()
                .map(post -> mapper.map(post, PostInfoDTO.class))
                .collect(Collectors.toList());
    }

    public String deletePost(int postId, int userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("User not found."));
        Post post = postRepository.findById(postId).orElseThrow(()->new BadRequestException("Post not found."));
        user.getPosts().remove(post);
        return ("You have removed post " + postId + "successfully!");
    }
    @Transactional
    public PostInfoDTO uploadPost(int userId, String title, String description,
                                  String location, int categoryId, MultipartFile video,
                                  MultipartFile image1, MultipartFile image2, MultipartFile image3) {
        //todo validate : SPRING
        String videoUrl = MediaService.uploadMedia(video);
        Post post = Post.builder()
                .owner(userRepository.findById(userId).orElseThrow(() -> new BadRequestException("User not found.")))
                .title(title)
                .description(description)
                .location(location)
                .category(categoryService.getByCategoryId(categoryId))
                .dateCreated(LocalDateTime.now())
                .videoUrl(videoUrl)
                .build();
        postRepository.save(post);
        List <MultipartFile> images = List.of(image1, image2, image3);
        setImages(post, images);
        return mapper.map(post, PostInfoDTO.class);
    }
    public void setImages(Post post, List<MultipartFile> images){
        for(MultipartFile imageRaw : images){
            Image image = Image.builder()
                    .url(MediaService.uploadMedia(imageRaw))
                    .post(post)
                    .build();
            imageRepository.save(image);//todo refactor: sql native @Query for simultaneous MultipartFile db insertion
        }
    }

    public Post testPost() {
        return postRepository.findById(3).get();
    }
}



