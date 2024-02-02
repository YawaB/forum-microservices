package org.forum.postms.service;

import org.forum.postms.dto.ServiceStatus;
import org.forum.postms.dto.request.*;
import org.forum.postms.dto.response.*;
import org.forum.postms.entities.User;
import org.forum.postms.service.remote.RemoteUserService;
import org.forum.postms.service.remote.RemotePostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostManagementService {
    private RemoteUserService userService;

    private RemotePostService postService;

    @Autowired
    public void setUserService(RemoteUserService userService) {
        this.userService = userService;
    }


    @Autowired
    public void setPostService(RemotePostService postService) {
        this.postService = postService;
    }

    public PostPreviewWithUserResponse getAllPublishedPostPreviews(PostPreviewRequest request) {

        ResponseEntity<PostPreviewResponse> postPreviewResponse = postService.getAllPublishedPostPreviews(request);

        List<Long> userIds = postPreviewResponse.getBody().getPosts().stream()
                .map(PostPreviewDTO::getUserId)
                .distinct()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        UserListRequest userListRequest = UserListRequest.builder()
                .users(userIds)
                .build();

        UserListResponse userListResponse = userService.loadUsersByIdList(userListRequest);

        List<User> users = userListResponse.getUsers();

        List<UserInfoDTO> userInfoDTOs = users.stream()
                .map(user -> new UserInfoDTO(
                        user.getId().longValue(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getProfileImageURL()
                ))
                .collect(Collectors.toList());

        PostPreviewWithUserResponse response = PostPreviewWithUserResponse.builder()
                .status(ServiceStatus.builder()
                        .success(true)
                        .message("All post previews found.")
                        .build())
                .posts(postPreviewResponse.getBody().getPosts())
                .users(userInfoDTOs)
                .build();

        return response;
    }

    public Object getAllUnpublishedOrHiddenOrDeletedOrBannedPostPreviewsOfOwner(PostPreviewRequest request) {
        ResponseEntity<PostPreviewResponse> postPreviewResponse = postService.getAllUnpublishedOrHiddenOrDeletedOrBannedPostPreviewsOfOwner(request);

        List<Long> userIds = postPreviewResponse.getBody().getPosts().stream()
                .map(PostPreviewDTO::getUserId)
                .distinct()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        UserListRequest userListRequest = UserListRequest.builder()
                .users(userIds)
                .build();

        UserListResponse userListResponse = userService.loadUsersByIdList(userListRequest);

        List<User> users = userListResponse.getUsers();

        List<UserInfoDTO> userInfoDTOs = users.stream()
                .map(user -> new UserInfoDTO(
                        user.getId().longValue(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getProfileImageURL()
                ))
                .collect(Collectors.toList());

        PostPreviewWithUserResponse response = PostPreviewWithUserResponse.builder()
                .status(ServiceStatus.builder()
                        .success(true)
                        .message("All post previews found.")
                        .build())
                .posts(postPreviewResponse.getBody().getPosts())
                .users(userInfoDTOs)
                .build();

        return response;
    }

    public Object getAllDeletedOrBannedPostPreviews(PostPreviewRequest request) {
        ResponseEntity<PostPreviewResponse> postPreviewResponse = postService.getAllDeletedOrBannedPostPreviews(request);

        List<Long> userIds = postPreviewResponse.getBody().getPosts().stream()
                .map(PostPreviewDTO::getUserId)
                .distinct()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        UserListRequest userListRequest = UserListRequest.builder()
                .users(userIds)
                .build();

        UserListResponse userListResponse = userService.loadUsersByIdList(userListRequest);

        List<User> users = userListResponse.getUsers();

        List<UserInfoDTO> userInfoDTOs = users.stream()
                .map(user -> new UserInfoDTO(
                        user.getId().longValue(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getProfileImageURL()
                ))
                .collect(Collectors.toList());

        PostPreviewWithUserResponse response = PostPreviewWithUserResponse.builder()
                .status(ServiceStatus.builder()
                        .success(true)
                        .message("All post previews found.")
                        .build())
                .posts(postPreviewResponse.getBody().getPosts())
                .users(userInfoDTOs)
                .build();

        return response;
    }

    public Object getPostById(String postId) {

        ResponseEntity<PostResponse> postResponse = postService.getPostById(postId);

        Long userId = postResponse.getBody().getPost().getUserId();

        UserListRequest userListRequest = UserListRequest.builder()
                .users(List.of(userId))
                .build();

        UserListResponse userListResponse = userService.loadUsersByIdList(userListRequest);

        List<User> users = userListResponse.getUsers();

        List<UserInfoDTO> userInfoDTOs = users.stream()
                .map(user -> new UserInfoDTO(
                        user.getId().longValue(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getProfileImageURL()
                ))
                .collect(Collectors.toList());

        PostPreviewWithUserResponse response = PostPreviewWithUserResponse.builder()
                .status(ServiceStatus.builder()
                        .success(true)
                        .message("All post previews found.")
                        .build())
                .posts(List.of())
                .users(userInfoDTOs)
                .build();

        return response;
    }
}
