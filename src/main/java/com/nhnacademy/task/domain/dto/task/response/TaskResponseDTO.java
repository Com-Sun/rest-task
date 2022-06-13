package com.nhnacademy.task.domain.dto.task.response;

import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import java.time.LocalDateTime;

public interface TaskResponseDTO {

    Long getTaskNum();
    Project getProject();
    String getTaskName();
    String getTaskContent();
    LocalDateTime getTaskCreatedDt();
    LocalDateTime getTaskModifiedDt();
    Long getTaskCreatedMemNum();
}
