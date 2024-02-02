package org.forum.postandreplyms.dto.response;

import org.forum.postandreplyms.dto.ServiceStatus;
import org.forum.postandreplyms.entities.Post;
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