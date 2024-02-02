package org.forum.postandreplyms.dto.response;

import org.forum.postandreplyms.dto.ServiceStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPreviewResponse {
    private ServiceStatus status;
    private List<PostPreviewDTO> posts;
}