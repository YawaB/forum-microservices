package org.forum.postms.dto.response;

import org.forum.postms.entities.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponse {
    private String message;
    private String status;
    private List<User> users;
}