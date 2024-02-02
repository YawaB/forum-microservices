package org.forum.userms.dto.response;

import lombok.*;
import org.forum.userms.entities.User;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String message;
    private String status;
    private User user;
}