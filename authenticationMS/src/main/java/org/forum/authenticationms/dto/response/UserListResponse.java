package org.forum.authenticationms.dto.response;

import org.forum.authenticationms.entities.User;
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