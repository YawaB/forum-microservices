package org.forum.postms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private Long topicId;
    private boolean isActive;
    private boolean isArchived;
    private String status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    @ElementCollection
    @CollectionTable(name = "post_images", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image")
    private List<String> images;
    @ElementCollection
    @CollectionTable(name = "post_attachments", joinColumns = @JoinColumn(name = "post_id"))
    private List<String> attachments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostReply> postReplies;

}
