package org.forum.postms.entities;

import lombok.*;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Boolean active;
    private Timestamp dateJoined;
    private String type;
    private String profileImageURL;
}
