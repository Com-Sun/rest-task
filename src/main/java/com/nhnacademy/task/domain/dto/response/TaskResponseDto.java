package com.nhnacademy.task.domain.dto.response;

import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import java.time.LocalDateTime;
import javax.persistence.Column;

public interface TaskResponseDto {

    Task.Pk getPk();
    Project getProject();
    String getTaskName();
    String getTaskContent();
    LocalDateTime getTaskCreatedDt();
    LocalDateTime getTaskModifiedDt();
    Long getTaskCreatedMemNum();
}
