package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.domain.dto.project.request.ProjectCreateRequestDTO;
import com.nhnacademy.task.domain.dto.project.request.ProjectModifyRequestDTO;
import com.nhnacademy.task.domain.dto.project.request.ProjectReadRequestDTO;
import com.nhnacademy.task.domain.dto.project.response.ProjectResponseDTO;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.exception.ProjectNotFoundException;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    @Override
    @Transactional
    public ProjectResponseDTO createProject(ProjectCreateRequestDTO projectRequestDTO) {

        Project project = Project.builder()
            .projectName(projectRequestDTO.getProjectName())
            .build();

        projectRepository.save(project);
        return projectRepository.queryByProjectNum(project.getProjectNum());
    }

    @Override
    public List<ProjectResponseDTO> readAllProjects() {
        return projectRepository.findAllBy();
    }

    @Override
    public ProjectResponseDTO readProject(ProjectReadRequestDTO projectReadRequestDTO) {

        return projectRepository.queryByProjectNum(projectReadRequestDTO.getProjectNum());
    }

    @Override
    public ProjectResponseDTO updateProject(Long projectNum, ProjectModifyRequestDTO projectModifyRequestDTO) {
        Project project = projectRepository.findById(projectNum).orElseThrow(() -> new ProjectNotFoundException("해당 프로젝트가 존재하지 않습니다."));

        project.setProjectName(projectModifyRequestDTO.getProjectName());
        projectRepository.save(project);

        return projectRepository.queryByProjectNum(project.getProjectNum());
    }

    @Override
    public boolean deleteProject(Long projectNum) {
        projectRepository.delete(projectRepository.findById(projectNum).orElseThrow(() -> new ProjectNotFoundException("해당 프로젝트가 존재하지 않습니다.")));

        return true;
    }
}
