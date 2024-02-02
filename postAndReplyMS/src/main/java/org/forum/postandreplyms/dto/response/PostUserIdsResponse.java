package org.forum.postandreplyms.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.forum.postandreplyms.dto.ServiceStatus;

import java.util.List;

@Getter
@Setter
@Builder
public class PostUserIdsResponse {
    private ServiceStatus status;
    private List<Long> userIds;
}
