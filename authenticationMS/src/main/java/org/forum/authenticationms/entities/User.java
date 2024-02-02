package org.forum.authenticationms.entities;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name="user")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean active;
    private Timestamp dateJoined;
    private String type;
    private String profileImageURL;

    public Boolean isActive() {
        return active;
    }
}
