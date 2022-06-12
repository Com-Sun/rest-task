package com.nhnacademy.task.domain.dto.response;

import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import java.time.LocalDateTime;

public interface TaskResponseDTO {

    Task.Pk getPk();
    Project getProject();
    String getTaskName();
    String getTaskContent();
    LocalDateTime getTaskCreatedDt();
    LocalDateTime getTaskModifiedDt();
    Long getTaskCreatedMemNum();
}
