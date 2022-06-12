package com.nhnacademy.task.domain.dto.response;

import com.nhnacademy.task.entity.Comment;
import com.nhnacademy.task.entity.Project;
import java.time.LocalDateTime;

public interface CommentResponseDTO {

    Comment.Pk getPk();

    Project getProject();

    Long getTaskNum();

    String getCommentContent();

    LocalDateTime getCommentCreatedDt();

    LocalDateTime getCommentModifiedDt();

    String getMemberName();
}
