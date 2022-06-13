package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.dto.project.response.ProjectResponseDTO;
import com.nhnacademy.task.entity.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    ProjectResponseDTO findByProjectName(String projectName);
    ProjectResponseDTO queryByProjectNum(Long projectNum);

    List<ProjectResponseDTO> findAllBy();

}
