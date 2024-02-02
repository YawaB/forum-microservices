package org.forum.postms.controller;


import org.forum.postms.dto.request.PostHistoryRequest;
import org.forum.postms.dto.request.PostPreviewRequest;
import org.forum.postms.service.PostManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/postManagement")
@CrossOrigin(origins = "*")
public class PostManagementController {
    private PostManagementService postManagementService;

    @Autowired
    public void setPostManagementService(PostManagementService postManagementService) {
        this.postManagementService = postManagementService;
    }

    @PostMapping("previews/published")
    ResponseEntity getAllPublishedPostPreviews(@Valid @RequestBody PostPreviewRequest request) {
        return ResponseEntity.ok(postManagementService.getAllPublishedPostPreviews(request));
    }

    @PostMapping("previews/user")
    ResponseEntity getAllUnpublishedOrHiddenOrDeletedOrBannedPostPreviewsOfOwner(@Valid @RequestBody PostPreviewRequest request) {
        return ResponseEntity.ok(postManagementService.getAllUnpublishedOrHiddenOrDeletedOrBannedPostPreviewsOfOwner(request));
    }

    @PostMapping("previews/admin")
    ResponseEntity getAllDeletedOrBannedPostPreviews(@Valid @RequestBody PostPreviewRequest request) {
        return ResponseEntity.ok(postManagementService.getAllDeletedOrBannedPostPreviews(request));
    }

    @GetMapping("{postId}")
    ResponseEntity getPostById(@PathVariable String postId) {
        return ResponseEntity.ok(postManagementService.getPostById(postId));
    }
}
