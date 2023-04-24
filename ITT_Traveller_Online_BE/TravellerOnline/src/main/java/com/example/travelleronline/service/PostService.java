package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.comment.CommentDTO;
import com.example.travelleronline.model.DTOs.post.CreatePostDTO;
import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.model.entities.Image;
import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.model.repositories.CommentRepository;
import com.example.travelleronline.model.repositories.ImageRepository;
import com.example.travelleronline.model.repositories.PostRepository;
import com.example.travelleronline.model.repositories.ReactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.springframework.data.domain.PageRequest.of;

@Service
public class PostService extends AbstractService{

    @Autowired
    private PostRepository postRepository;//data base
    @Autowired
    private CategoryService categoryService;

    @Autowired ImageRepository imageRepository;
    @Autowired ReactionRepository reactionRepository;
    @Autowired CommentRepository commentRepository;
    public Page<PostInfoDTO> getPosts(int pageNumber) {//todo criteria

        Pageable pageAsParam = of(pageNumber, pageSize);
        long totalElements = postRepository.count();
        List <PostInfoDTO> result=
                postRepository.findAll(pageAsParam)
                .stream()
                .map(post -> mapper.map(post, PostInfoDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(result, pageAsParam, totalElements);
    }
    public PostInfoDTO getPostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(()->new BadRequestException("Post does not exist."));
        PostInfoDTO postInfoDTO = mapper.map(post, PostInfoDTO.class);
        //note: images -> PostInfoDTO
        List<String> imagesUrls = imageRepository.findAllByPost_Id(id).stream()
                .map(image -> image.get().getUrl())
                .collect(Collectors.toList());
        postInfoDTO.setImageUrls(imagesUrls);
        //note: comment count -> PostInfoDTO
        //question: count all comments and sub-comments.
        long commentCount = getBranchedCommentCountForPost(post.getId());
        postInfoDTO.setCommentCount(commentCount);
        return postInfoDTO;
    }
    //todo Pageable + connect to comments: OneToMany List<Comments>
    public List<PostInfoDTO> getUserPosts(int userID) {
        List<Post> posts = postRepository.findByOwnerId(userID);
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
                                  MultipartFile image1, MultipartFile image2, MultipartFile image3
                                    ,String additionalInfo) {
        //
        /*
        todo validate : SPRING
         - 1.1. title is shorter than 5 symbols -> Bad request : msg "title is mandatory"
         - 1.2. description is shorter than 5 symbols -> Bad request : msg "bad request message"
         - 1.3. title and description have to be not null (mandatory field) -> Bad request : msg "bad request message"
         - 1.4. validation for images (check if images exist) todo
         */
        String videoUrl=null;
        if(!video.isEmpty())videoUrl = MediaService.uploadMedia(video);
        Post post = Post.builder()
                .owner(userRepository.findById(userId).orElseThrow(() -> new BadRequestException("User not found.")))
                .title(title)
                .description(description)
                .location(location)
                .additionalInfo(additionalInfo)
                .category(categoryService.getByCategoryId(categoryId))
                .dateCreated(LocalDateTime.now())
                .videoUrl(videoUrl)
                .build();

        postRepository.save(post);

        System.out.println("\n\n\n\n"+image2);
        List <MultipartFile> images = new ArrayList<>();
        if(!image1.isEmpty()) images.add(image1);
        if(!image2.isEmpty()) images.add(image2);
        if(!image3.isEmpty()) images.add(image3);
        List <String> imgUrls= new ArrayList<>();
        setImages(post, images,imgUrls);
        PostInfoDTO result = mapper.map(post, PostInfoDTO.class);
        result.setImageUrls(imgUrls);
        return result;
    }
    public void setImages(Post post, List<MultipartFile> images, List<String> imgUrls){
        for(MultipartFile imageRaw : images){
            Image image = Image.builder()
                    .url(MediaService.uploadMedia(imageRaw))
                    .post(post)
                    .build();
            imageRepository.save(image);//todo refactor: sql native @Query for simultaneous MultipartFile db insertion
            imgUrls.add(image.getUrl());
        }
    }

    public Post testPost() {
        return postRepository.findById(3).get();
    }

    //todo
    private int getBranchedCommentCountForPost(Integer postId) {
        AtomicInteger commentCount = new AtomicInteger(commentRepository.countAllByPostId(postId));
        commentRepository.findAllByPostId(postId).stream()
                .forEach(comment ->
                    {
                    commentCount.addAndGet(commentRepository.countAllByParentCommentId(comment.getId()));
                    }
                );
        return commentCount.get();
    }
    public String getLocationUrl(int postId){
        Post post = postRepository.findById(postId).orElseThrow(()->new NotFoundException("No such post"));
        String location = "https://www.google.com/maps/@" + post.getLocation();
        return location;
    }

    public Page<PostInfoDTO> getNewsfeedByDate(int pageNumber, int loggedId) {
        Pageable pageAsParam = of(pageNumber, pageSize);
        long totalElements = postRepository.count();

        User user=userRepository.findById(loggedId).get();
        List<Integer> subscribers= new ArrayList<>();
        user.getSubscribedTo().stream()
                .forEach(sub -> subscribers.add(sub.getId()));
        List <PostInfoDTO> result=
                postRepository.newsFeedByDate(subscribers,pageAsParam)
                        .stream()
                        .map(post -> mapper.map(post, PostInfoDTO.class))
                        .collect(Collectors.toList());
        return new PageImpl<>(result, pageAsParam, totalElements);
    }

    public Page<PostInfoDTO> getNewsfeedByRating(int pageNumber, int loggedId) {
        Pageable pageAsParam = of(pageNumber, pageSize);
        long totalElements = postRepository.count();

        User user=userRepository.findById(loggedId).get();
        List<Integer> subscribers= new ArrayList<>();
        user.getSubscribedTo().stream()
                .forEach(sub -> subscribers.add(sub.getId()));
        List <PostInfoDTO> result=
                postRepository.newsFeedByLikes(subscribers,pageAsParam)
                        .stream()
                        .map(post -> mapper.map(post, PostInfoDTO.class))
                        .collect(Collectors.toList());
        return new PageImpl<>(result, pageAsParam, totalElements);
    }
}



