package com.nhnacademy.task.domain.dto.comment.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentModifyRequestDTO {

    private Long commentNum;
    private String commentContent;
    private LocalDateTime commentModifiedDt;
}
