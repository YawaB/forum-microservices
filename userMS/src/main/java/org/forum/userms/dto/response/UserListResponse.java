package org.forum.userms.dto.response;

import lombok.*;
import org.forum.userms.entities.User;

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