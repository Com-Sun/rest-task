package com.nhnacademy.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.nhnacademy.task.domain.dto.project.request.ProjectCreateRequestDTO;
import com.nhnacademy.task.domain.dto.project.request.ProjectModifyRequestDTO;
import com.nhnacademy.task.domain.dto.project.request.ProjectReadRequestDTO;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.repository.ProjectRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepository projectRepository;

    private Project project;
    private ProjectCreateRequestDTO projectCreateRequestDTO;


    @BeforeEach
    void setUp() {
        project = Project.builder()
            .projectName("현진아 가즈아")
            .build();

        projectCreateRequestDTO = ProjectCreateRequestDTO.builder()
            .projectName("현진아 가즈아")
            .build();

    }

    @Test
    void createProjectTest() {
        projectService.createProject(projectCreateRequestDTO);
        verify(projectRepository, atLeastOnce()).save(any());
    }

    @Test
    void readAllProjectsTest() {
        projectService.readAllProjects();
        verify(projectRepository, atLeastOnce()).findAllBy();
    }

    @Test
    void readProjectTest() {
        ProjectReadRequestDTO readRequestDTO = ProjectReadRequestDTO.builder()
            .ProjectNum(1L)
            .build();

        projectService.readProject(readRequestDTO);
        verify(projectRepository, atLeastOnce()).queryByProjectNum(any());
    }

    @Test
    void updateProjectTest() {
        given(projectRepository.findById(any()))
            .willReturn(Optional.of(project));

        ProjectModifyRequestDTO modifyRequestDTO = ProjectModifyRequestDTO
            .builder()
            .projectName("이걸로바꾸자")
            .build();

        projectService.updateProject(1L, modifyRequestDTO);
        verify(projectRepository, atLeastOnce()).findById(1L);
        verify(projectRepository, atLeastOnce()).save(any());
        verify(projectRepository, atLeastOnce()).queryByProjectNum(any());
    }

    @Test
    void deleteProjectTest() {
        given(projectRepository.findById(any()))
            .willReturn(Optional.of(project));

        boolean isTrue = projectService.deleteProject(1L);

        verify(projectRepository, atLeastOnce()).findById(any());
        assertThat(isTrue).isTrue();
    }
}