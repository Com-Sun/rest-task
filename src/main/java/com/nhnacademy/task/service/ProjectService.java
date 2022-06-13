package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.dto.project.request.ProjectCreateRequestDTO;
import com.nhnacademy.task.domain.dto.project.request.ProjectModifyRequestDTO;
import com.nhnacademy.task.domain.dto.project.request.ProjectReadRequestDTO;
import com.nhnacademy.task.domain.dto.project.response.ProjectResponseDTO;
import java.util.List;

public interface ProjectService {

    ProjectResponseDTO createProject(ProjectCreateRequestDTO projectRequestDTO);

    List<ProjectResponseDTO> readAllProjects();

    ProjectResponseDTO readProject(ProjectReadRequestDTO projectReadRequestDTO);

    ProjectResponseDTO updateProject(ProjectModifyRequestDTO projectModifyRequestDTO);

    boolean deleteProject(Long projectNum);

}
