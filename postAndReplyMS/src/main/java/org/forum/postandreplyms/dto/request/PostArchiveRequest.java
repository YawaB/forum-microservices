package org.forum.postandreplyms.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostArchiveRequest {
    private Long postId;
    private boolean archived;
}
