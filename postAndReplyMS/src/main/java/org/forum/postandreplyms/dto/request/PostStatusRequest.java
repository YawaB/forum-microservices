package org.forum.postandreplyms.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostStatusRequest {
    private Long postId;
    private String status;
}
