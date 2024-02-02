package org.forum.postms.service.remote;

import org.forum.postms.dto.request.UserListRequest;
import org.forum.postms.dto.response.UserListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(value = "user")
public interface RemoteUserService {
    @GetMapping("user/list")
    UserListResponse loadUsersByIdList(@Valid @RequestBody UserListRequest request);
}
