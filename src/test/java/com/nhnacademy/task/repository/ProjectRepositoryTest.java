package com.nhnacademy.task.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.task.domain.dto.project.response.ProjectResponseDTO;
import com.nhnacademy.task.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;
    private Project project;

    @BeforeEach
    void setUp() {
        project = Project.builder()
            .projectNum(1L)
            .projectName("Hyunjin Project")
            .build();
        projectRepository.save(project);
    }

    @Test
    public void findByProjectNameTest() {
        ProjectResponseDTO responseDTO = projectRepository.findByProjectName("Hyunjin Project");
        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getProjectStatus()).isEqualTo("active");
    }
}