package com.example.travelleronline.service;

import com.example.travelleronline.model.DTOs.post.PostInfoDTO;
import com.example.travelleronline.model.DTOs.post.SearchPostDTO;
import com.example.travelleronline.model.DTOs.post.SearchPostResultDTO;
import com.example.travelleronline.model.entities.Image;
import com.example.travelleronline.model.entities.Post;
import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.exceptions.BadRequestException;
import com.example.travelleronline.model.exceptions.NotFoundException;
import com.example.travelleronline.model.exceptions.UnauthorizedException;
import com.example.travelleronline.model.repositories.*;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.springframework.data.domain.PageRequest.of;

@Service
public class PostService extends AbstractService{
    @Autowired
    private PostRepository postRepository;//data base
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired ImageRepository imageRepository;
    @Autowired ReactionRepository reactionRepository;
    @Autowired CommentRepository commentRepository;
    public Page<PostInfoDTO> getAllPostsWithPagination(int pageNumber) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "dateCreated");
        Page<Post> postsPage = postRepository.findAll(pageable);
        return postsPage.map(post -> mapper.map(post, PostInfoDTO.class));
    }
    public PostInfoDTO getPostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(()->new BadRequestException("Post does not exist."));
        PostInfoDTO postInfoDTO = mapper.map(post, PostInfoDTO.class);
        List<String> imagesUrls = imageRepository.findAllByPost_Id(id).stream()
                .map(image -> image.get().getUrl())
                .collect(Collectors.toList());
        postInfoDTO.setImageUrls(imagesUrls);
        long commentCount = getBranchedCommentCountForPost(post.getId());
        postInfoDTO.setCommentCount(commentCount);
        return postInfoDTO;
    }
    public Page<PostInfoDTO> getUserPosts(int pageNumber,int userId) {
        Pageable pageAsParam = of(pageNumber, pageSize);
        long totalElements = postRepository.countPostsByOwnerId(userId);

        List<PostInfoDTO> posts= postRepository.findByOwnerId(pageAsParam,userId).stream()
                .map(post -> mapper.map(post, PostInfoDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(posts, pageAsParam, totalElements);
    }
    public String deletePost(int postId, int userId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new BadRequestException("Post not found."));
        if (checkOwner(post.getOwner().getId(), userId)){
            postRepository.delete(post);
            return ("You have removed post " + postId + "successfully!");
        }else{
            throw new UnauthorizedException("You are not the owner of this post.");
        }
    }
    @Transactional
    public PostInfoDTO uploadPost(int userId, String title, String description,
                                  String location, int categoryId, MultipartFile video,
                                  MultipartFile image1, MultipartFile image2, MultipartFile image3
                                  ,String additionalInfo) {
        String videoUrl=null;
        if(video!=null)videoUrl = MediaService.uploadMedia(video);
        Post post = Post.builder()
                .owner(userRepository.findById(userId).orElseThrow(() -> new BadRequestException("User not found.")))
                .title(title)
                .description(description)
                .location(location)
                .additionalInfo(additionalInfo)
                .category(categoryRepository.getById(categoryId))
                .dateCreated(LocalDateTime.now())
                .videoUrl(videoUrl)
                .build();
        try {
            postRepository.save(post);
        } catch (ConstraintViolationException e){
            throw new BadRequestException("Post input data not acceptable");
        }
        List <MultipartFile> images = new ArrayList<>();
        if(image1!=null) images.add(image1);
        if(image2!=null) images.add(image2);
        if(image3!=null) images.add(image3);
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
            imageRepository.save(image);
            imgUrls.add(image.getUrl());
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
    public Page<PostInfoDTO> getNewsfeedByDate (int pageNumber, int loggedId) {
        Pageable pageAsParam = of(pageNumber, pageSize);
        long totalElements = postRepository.count();

        List<Integer> subscribers = userRepository.findSubscribedToIdsBySubscriberId(loggedId);
        List<PostInfoDTO> result =
                postRepository.getNewsFeedByDate(subscribers,pageAsParam)
                        .stream()
                        .map(post -> mapper.map(post, PostInfoDTO.class))
                        .collect(Collectors.toList());
        if(result.size()==0) throw new NotFoundException("You need to subscribe to view newsfeed.");
        return new PageImpl<>(result, pageAsParam, totalElements);
    }
    public Page<PostInfoDTO> getNewsfeedByRating (int pageNumber, int loggedId) {
        Pageable pageAsParam = of(pageNumber, pageSize);
        long totalElements = postRepository.count();

        List<Integer> subscribers = userRepository.findSubscribedToIdsBySubscriberId(loggedId);
        List<PostInfoDTO> result =
                postRepository.getNewsFeedByLikes(subscribers, pageAsParam)
                        .stream()
                        .map(post -> mapper.map(post, PostInfoDTO.class))
                        .collect(Collectors.toList());
        if(result.size()==0) throw new NotFoundException("You need to subscribe to view newsfeed.");
        return new PageImpl<>(result, pageAsParam, totalElements);
    }
    public PostInfoDTO editPost(int userId, int postId,
                                PostInfoDTO postInfoDTO){
        try {
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
        } catch (ConstraintViolationException e){
            throw new BadRequestException("Update data not acceptable.");
        }
    }
    public Page<SearchPostResultDTO> searchPostsByTitle(SearchPostDTO searchPostDTO, int pageNumber) {
            String searchPrompt = searchPostDTO.getSearchPrompt();
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "title");
            Page<Post> postsPage = postRepository.getByTitle(searchPrompt, pageable);
            if (postsPage.isEmpty()){
                throw new NotFoundException("No such post(s) found..");
            }
            return postsPage.map(post -> mapper.map(post, SearchPostResultDTO.class));
    }
    public Page<SearchPostResultDTO> searchPostsByCategories (SearchPostDTO searchPostDTO, int pageNumber) {
        try {
            String searchPrompt = searchPostDTO.getSearchPrompt();
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "title");
            int categoryId = categoryRepository.findCategoryIdByName(searchPrompt);
            Page<Post> postsPage = postRepository.getByCategoryId(categoryId, pageable);
            return postsPage.map(post -> mapper.map(post, SearchPostResultDTO.class));
        } catch (Exception e){
            throw new BadRequestException("No such post(s) found..");
        }
    }
    private boolean checkOwner(int userId, int ownerId) {
        return (userId == ownerId);
    }
}