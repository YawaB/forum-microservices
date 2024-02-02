package org.forum.authenticationms.dto.response;

import org.forum.authenticationms.entities.User;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String message;
    private String status;
    private Optional<User> user;
}