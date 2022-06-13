package com.nhnacademy.task.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.task.domain.dto.response.ProjectMemberResponseDTO;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectMember;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectMemberRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectMemberRepository projectMemberRepository;
    private Project project;

    @BeforeEach
    void setUp() {
        project = Project.builder()
            .projectName("Hyunjin Project")
            .build();
        projectRepository.saveAndFlush(project);
    }

    @Test
    void findProjectMemberByProjectNumTest() {
        ProjectMember projectMember = ProjectMember.builder()
            .pk(new ProjectMember.Pk(1L, project.getProjectNum()))
            .project(project)
            .memberName("현진")
            .projectAuth("관리자")
            .build();

        projectMemberRepository.save(projectMember);

        List<ProjectMemberResponseDTO> responseDTOS = projectMemberRepository.findByPk_ProjectNum(project.getProjectNum());

        assertThat(responseDTOS).hasSize(1);
    }

}