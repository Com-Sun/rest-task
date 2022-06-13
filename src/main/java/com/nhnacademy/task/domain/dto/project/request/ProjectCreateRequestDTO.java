package com.nhnacademy.task.domain.dto.project.request;


import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectCreateRequestDTO {

    @NotBlank(message = "이름이 공백으로 인한 에러")
    private String projectName;
}
