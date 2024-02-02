package org.forum.postms.dto.response;

import org.forum.postms.dto.ServiceStatus;
import org.forum.postms.entities.Post;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private ServiceStatus status;
    private Post post;
}