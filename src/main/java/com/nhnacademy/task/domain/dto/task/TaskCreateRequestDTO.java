package com.nhnacademy.task.domain.dto.task;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskCreateRequestDTO {
    private Long taskCreatedMemNum;
    private Long projectNum;
    private String taskName;
    private String taskContent;
    private LocalDateTime taskCreatedDt;
}
