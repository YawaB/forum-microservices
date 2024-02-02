package org.forum.postandreplyms.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewPostRequest {
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Content is required")
    private String content;
    private String status;
}
