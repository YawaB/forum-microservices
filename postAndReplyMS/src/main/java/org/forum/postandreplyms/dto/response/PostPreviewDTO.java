package org.forum.postandreplyms.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPreviewDTO {
    private Long postId;
    private Long userId;
    private LocalDateTime date;
    private String title;
}
