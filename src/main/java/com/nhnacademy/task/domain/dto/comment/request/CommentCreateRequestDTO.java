package com.nhnacademy.task.domain.dto.comment.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentCreateRequestDTO {

    private Long taskNum;
    private String memberName;
    private String commentContent;

}
