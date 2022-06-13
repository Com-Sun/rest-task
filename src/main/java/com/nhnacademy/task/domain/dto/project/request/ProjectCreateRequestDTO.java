package com.nhnacademy.task.domain.dto.project.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectCreateRequestDTO {

    private Long projectNum;
    private String projectName;
}
