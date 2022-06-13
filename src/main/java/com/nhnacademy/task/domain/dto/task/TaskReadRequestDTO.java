package com.nhnacademy.task.domain.dto.task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskReadRequestDTO {
    private Long taskNum;
}
