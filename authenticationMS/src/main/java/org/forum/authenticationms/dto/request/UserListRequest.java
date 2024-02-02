package org.forum.authenticationms.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListRequest {
    @NotNull
    private List<Long> users;
}
