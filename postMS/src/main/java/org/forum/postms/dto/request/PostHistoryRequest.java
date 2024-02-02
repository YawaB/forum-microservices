package org.forum.postms.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostHistoryRequest {
    private String keyword;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
