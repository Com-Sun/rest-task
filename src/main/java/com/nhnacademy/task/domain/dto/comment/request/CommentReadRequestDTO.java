package com.nhnacademy.task.domain.dto.comment.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentReadRequestDTO {

    private Long commentNum;
}
