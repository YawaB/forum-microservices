package org.forum.postandreplyms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity @Getter @Setter
public class PostReply {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String commentText;
    private boolean isActive;
    private LocalDateTime dateCreated;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @OneToMany(mappedBy = "postReply", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubReply> subReplies;

}
