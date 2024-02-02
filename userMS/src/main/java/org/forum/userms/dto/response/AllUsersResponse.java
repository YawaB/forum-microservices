package org.forum.userms.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.forum.userms.entities.User;

import java.util.List;

@Getter
@Setter
@Builder
public class AllUsersResponse {
    private List<User> users;
}
