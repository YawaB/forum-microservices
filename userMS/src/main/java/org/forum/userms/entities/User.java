package org.forum.userms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean active;
    private LocalDateTime dateJoined;
    private String type;
    private String profileImageURL;
}
