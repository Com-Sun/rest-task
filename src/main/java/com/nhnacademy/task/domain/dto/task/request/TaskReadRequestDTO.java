package com.nhnacademy.task.domain.dto.task.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskReadRequestDTO {
    private Long taskNum;
}
