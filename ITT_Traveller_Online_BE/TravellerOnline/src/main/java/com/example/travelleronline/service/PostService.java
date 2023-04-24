package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.model.entities.Image;
import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.model.exceptions.UnauthorizedException;
import com.example.travelleronline.model.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PostService extends AbstractService{

    @Autowired
    private PostRepository postRepository;//data base
    @Autowired
    private CategoryService categoryService;

    @Autowired ImageRepository imageRepository;
    @Autowired ReactionRepository reactionRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired CategoryRepository categoryRepository;

        /*
        note:
         1. validate post info with static validation service methods
         2. get logged user
         3. create post entity
         4. set owner
         5. save (entity) to db
         6. return dto
         */

    //todo Pageable
    public Page<PostInfoDTO> getAllPostsWithPagination(int pageNumber) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "dateCreated");
        Page<Post> postsPage = postRepository.findAll(pageable);

        return postsPage.map(post -> mapper.map(post, PostInfoDTO.class));
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
    //todo Pageable
    public List<PostInfoDTO> getUserPosts(int userId) {
        List<Post> posts = postRepository.findByOwnerId(userId);
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
        /*
        todo validate : SPRING
         - 1.1. title is shorter than 5 symbols -> Bad request : msg "title is mandatory"
         - 1.2. description is shorter than 5 symbols -> Bad request : msg "bad request message"
         - 1.3. title and description have to be not null (mandatory field) -> Bad request : msg "bad request message"
         - 1.4. validation for images (check if images exist) todo
         */
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
    public PostInfoDTO editPost(int userId, int postId,
                                PostInfoDTO postInfoDTO){
//        System.out.println(title);
        if (checkOwner(userId, postRepository.findById(postId).get().getOwner().getId())){
            Post post = postRepository.findById(postId).orElseThrow(()->new NotFoundException("Post not found"));
            post.setTitle(postInfoDTO.getTitle());
            post.setDescription(postInfoDTO.getDescription());
            post.setLocation(postInfoDTO.getLocation());
            post.setCategory(categoryRepository.getById(postInfoDTO.getCategoryId()));
            post.setDateCreated(LocalDateTime.now());
            postRepository.save(post);
            return mapper.map(post, PostInfoDTO.class);
        } else {
            throw new UnauthorizedException("You need to be the owner.");
        }
    }
    //todo search post
    public boolean checkOwner(int userId, int ownerId) {
        return (userId == ownerId);
    }
}



