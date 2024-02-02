package org.forum.postandreplyms.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteReplyRequest {
    private Long postId;
    private int replyIndex;
}
