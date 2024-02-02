package org.forum.authenticationms.dto.response;

import org.forum.authenticationms.entities.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AllUsersResponse {
    private List<User> users;
}
