package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.dto.response.ProjectResponseDTO;
import com.nhnacademy.task.entity.Project;

public interface ProjectService {

    ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO);

    ProjectResponseDTO

}
