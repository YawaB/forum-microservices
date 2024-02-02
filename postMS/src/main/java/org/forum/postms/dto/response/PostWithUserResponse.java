package org.forum.postms.dto.response;

import org.forum.postms.dto.ServiceStatus;
import org.forum.postms.entities.Post;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostWithUserResponse {
    private ServiceStatus status;
    private Post post;
    private List<UserInfoDTO> users;
}