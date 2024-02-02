package org.forum.postandreplyms.controller;

import org.forum.postandreplyms.dto.ServiceStatus;
import org.forum.postandreplyms.dto.request.*;
import org.forum.postandreplyms.dto.response.NewPostResponse;
import org.forum.postandreplyms.dto.response.PostPreviewDTO;
import org.forum.postandreplyms.dto.response.PostPreviewResponse;
import org.forum.postandreplyms.dto.response.PostResponse;
import org.forum.postandreplyms.entities.Post;
import org.forum.postandreplyms.security.AuthUserDetail;
import org.forum.postandreplyms.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*")
public class PostController {
    private PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/test")
    public void test() {
        System.out.println("executed");
    }

    @PostMapping("/previews/published")
    public ResponseEntity<PostPreviewResponse> getAllPublishedPostPreviews(@Valid @RequestBody PostPreviewRequest request){
        List<Post> posts = postService.getAllPublishedPostPreviews(request.getUserId(),
                request.getOrderBy(),
                request.isAsc());

        List<PostPreviewDTO> postPreviewDTOs = posts.stream()
                .map(post -> PostPreviewDTO.builder()
                        .postId(post.getId())
                        .userId(post.getUserId())
                        .date(post.getDateCreated())
                        .title(post.getTitle())
                        .build())
                .collect(Collectors.toList());

        PostPreviewResponse response = PostPreviewResponse.builder()
                .status(ServiceStatus.builder()
                        .success(true)
                        .message("All post previews found.")
                        .build())
                .posts(postPreviewDTOs)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/previews/user")
    public ResponseEntity<PostPreviewResponse> getAllUnpublishedOrHiddenOrDeletedOrBannedPostPreviewsOfOwner(@Valid @RequestBody PostPreviewRequest request,
                                                                                                             @AuthenticationPrincipal AuthUserDetail authUserDetail){
        List<Post> posts = postService.getAllUnpublishedOrHiddenOrDeletedOrBannedPostPreviewsByUserId(authUserDetail.getUserId().longValue(), request.getStatus());

        List<PostPreviewDTO> postPreviewDTOs = posts.stream()
                .map(post -> PostPreviewDTO.builder()
                        .postId(post.getId())
                        .userId(post.getUserId())
                        .date(post.getDateCreated())
                        .title(post.getTitle())
                        .build())
                .collect(Collectors.toList());

        PostPreviewResponse response = PostPreviewResponse.builder()
                .status(ServiceStatus.builder()
                        .success(true)
                        .message("All post previews found.")
                        .build())
                .posts(postPreviewDTOs)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/previews/admin")
    public ResponseEntity<PostPreviewResponse> getAllDeletedOrBannedPostPreviews(@Valid @RequestBody PostPreviewRequest request){
        List<Post> posts = postService.getAllDeletedOrBannedPostPreviews(request.getStatus());

        List<PostPreviewDTO> postPreviewDTOs = posts.stream()
                .map(post -> PostPreviewDTO.builder()
                        .postId(post.getId())
                        .userId(post.getUserId())
                        .date(post.getDateCreated())
                        .title(post.getTitle())
                        .build())
                .collect(Collectors.toList());

        PostPreviewResponse response = PostPreviewResponse.builder()
                .status(ServiceStatus.builder()
                        .success(true)
                        .message("All post previews found.")
                        .build())
                .posts(postPreviewDTOs)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId){
        // Check if the user is admin

        Post post = postService.getPostById(postId);

        PostResponse response = PostResponse.builder()
                .status(ServiceStatus.builder()
                        .success(true)
                        .message("Post found.")
                        .build())
                .post(post)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<NewPostResponse> createNewPost(@Valid @RequestBody NewPostRequest request, @AuthenticationPrincipal AuthUserDetail authUserDetail){
        Post post = postService.createNewPost(authUserDetail.getUserId(),
                request.getTitle(),
                request.getContent(),
                request.getStatus());

        NewPostResponse response = NewPostResponse.builder()
                .status(ServiceStatus.builder()
                        .success(true)
                        .message("Post created.")
                        .build())
                .post(post)
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/edit")
    public ResponseEntity<ServiceStatus> editPost(@Valid @RequestBody EditPostRequest request,@AuthenticationPrincipal AuthUserDetail authUserDetail) {
        postService.editPostById(request.getPostId(),
                authUserDetail.getUserId(),
                request.getTitle(),
                request.getContent(),
                request.getImages(),
                request.getAttachments());

        // Return success response
        return ResponseEntity.ok(ServiceStatus.builder()
                .success(true)
                .message("Post edited.")
                .build());
    }

    @PatchMapping("/modify")
    public ResponseEntity<ServiceStatus> modifyPost(@Valid @RequestBody EditPostRequest request,@AuthenticationPrincipal AuthUserDetail authUserDetail) {
        postService.modifyPostById(request.getPostId(),
                authUserDetail.getUserId(),
                request.getTitle(),
                request.getContent());

        // Return success response
        return ResponseEntity.ok(ServiceStatus.builder()
                .success(true)
                .message("Post edited.")
                .build());
    }

    @PatchMapping("/reply")
    public ResponseEntity<ServiceStatus> replyPost(@Valid @RequestBody ReplyRequest request,
                                                   @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        postService.replyPostById(request.getPostId(),
                authUserDetail.getUserId(),
                request.getComment());

        // Return success response
        return ResponseEntity.ok(ServiceStatus.builder()
                .success(true)
                .message("Post replied.")
                .build());
    }

    @PatchMapping("/subReply")
    public ResponseEntity<ServiceStatus> replySubPost(@Valid @RequestBody SubReplyRequest request,
                                                      @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        postService.replySubPostById(request.getPostId(),
                request.getReplyIndex(),
                authUserDetail.getUserId(),
                request.getComment());

        // Return success response
        return ResponseEntity.ok(ServiceStatus.builder()
                .success(true)
                .message("Sub Post replied.")
                .build());
    }

    @PatchMapping("/delete/reply")
    public ResponseEntity<ServiceStatus> deleteReply(@RequestBody DeleteReplyRequest request,
                                                     @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        Collection<? extends GrantedAuthority> authorities = authUserDetail.getAuthorities();

        // Check if the user is admin
        boolean isAdmin = authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals("admin") ||
                        authority.getAuthority().equals("super"));
        postService.deleteReplyByIdAndIndex(request.getPostId(),
                request.getReplyIndex(),
                authUserDetail.getUserId(),
                isAdmin);

        // Return success response
        return ResponseEntity.ok(ServiceStatus.builder()
                .success(true)
                .message("Reply deleted.")
                .build());
    }

    @PatchMapping("/delete/subreply")
    public ResponseEntity<ServiceStatus> deleteSubReply(@RequestBody DeleteSubReplyRequest request,
                                                        @AuthenticationPrincipal AuthUserDetail authUserDetail) {

        Collection<? extends GrantedAuthority> authorities = authUserDetail.getAuthorities();

        // Check if the user is admin
        boolean isAdmin = authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals("admin") ||
                        authority.getAuthority().equals("super"));
        postService.deleteSubReplyByIdAndIndex(request.getPostId(),
                request.getReplyIndex(),
                request.getSubReplyIndex(),
                authUserDetail.getUserId(),
                isAdmin);

        // Return success response
        return ResponseEntity.ok(ServiceStatus.builder()
                .success(true)
                .message("Sub reply deleted.")
                .build());
    }

    @PatchMapping("/update/archived")
    public ResponseEntity<ServiceStatus> updateArchive(@RequestBody PostArchiveRequest request,
                                                       @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        postService.updateArchiveById(request.getPostId(),
                authUserDetail.getUserId(),
                request.isArchived());

        // Return success response
        return ResponseEntity.ok(ServiceStatus.builder()
                .success(true)
                 .build());
    }

    @PatchMapping("/update/status")
    public ResponseEntity<ServiceStatus> updateStatus(@RequestBody PostStatusRequest request,
                                                      @AuthenticationPrincipal AuthUserDetail authUserDetail) {

        postService.updateStatusById(request.getPostId(),
                authUserDetail.getUserId().longValue(),
                request.getStatus(),
                true);

        // Return success response
        return ResponseEntity.ok(ServiceStatus.builder()
                .success(true)
                .message("Post status updated.")
                .build());
    }
}
