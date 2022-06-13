package com.nhnacademy.task.domain.dto.response;

import com.nhnacademy.task.entity.Comment;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import java.time.LocalDateTime;

public interface CommentResponseDTO {

    Long getCommentNum();

    Task getTask();

    String getCommentContent();

    LocalDateTime getCommentCreatedDt();

    LocalDateTime getCommentModifiedDt();

    String getMemberName();
}
