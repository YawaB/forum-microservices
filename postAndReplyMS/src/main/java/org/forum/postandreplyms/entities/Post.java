package org.forum.postandreplyms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity @NoArgsConstructor  @AllArgsConstructor @Getter @Setter
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long userId;
    private String content;
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
