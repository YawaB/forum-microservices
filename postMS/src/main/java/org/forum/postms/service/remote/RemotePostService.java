package org.forum.postms.service.remote;


import org.forum.postms.dto.request.InnerPostHistoryRequest;
import org.forum.postms.dto.request.PostPreviewRequest;
import org.forum.postms.dto.response.PostPreviewResponse;
import org.forum.postms.dto.response.PostResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(value = "post")
public interface RemotePostService {
    @PostMapping("post/previews/published")
    ResponseEntity<PostPreviewResponse> getAllPublishedPostPreviews(@Valid @RequestBody PostPreviewRequest request);

    @PostMapping("post/previews/user")
    ResponseEntity<PostPreviewResponse> getAllUnpublishedOrHiddenOrDeletedOrBannedPostPreviewsOfOwner(@Valid @RequestBody PostPreviewRequest request);

    @PostMapping("post/previews/admin")
    ResponseEntity<PostPreviewResponse> getAllDeletedOrBannedPostPreviews(@Valid @RequestBody PostPreviewRequest request);

    @PostMapping("post/history")
    ResponseEntity<PostPreviewResponse> getAllViewHistoryPosts(@RequestBody InnerPostHistoryRequest request);

    @GetMapping("post/{postId}")
    ResponseEntity<PostResponse> getPostById(@PathVariable String postId);
}
