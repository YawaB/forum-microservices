package org.forum.postms.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SubReply {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String commentText;
    private boolean isActive;
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name = "post_reply_id")
    private PostReply postReply;
}
