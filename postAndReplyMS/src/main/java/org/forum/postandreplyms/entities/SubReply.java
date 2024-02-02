package org.forum.postandreplyms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Getter
@Setter
@ToString
@Builder @NoArgsConstructor
@AllArgsConstructor
public class SubReply {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String commentText;
    private boolean isActive;
    private LocalDateTime dateCreated;
    @ManyToOne
    private PostReply postReply;
}
