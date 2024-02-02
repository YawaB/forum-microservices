package org.forum.postms.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String profileImageURL;
}
