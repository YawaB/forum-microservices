package org.forum.postandreplyms.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteSubReplyRequest {
    private Long postId;
    private int replyIndex;
    private int subReplyIndex;
}
