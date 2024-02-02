package org.forum.postms.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class PostReply {

    @Id
    @GeneratedValue
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String commentText;
    private boolean isActive;
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "postReply", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubReply> subReplies;
}
