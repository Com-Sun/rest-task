package com.nhnacademy.task.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.task.domain.dto.response.ProjectResponseDTO;
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
            .projectName("Hyunjin Project")
            .projectStatus("활성")
            .build();
    }

    @Test
    public void findByProjectNameTest() {
        projectRepository.save(project);
        ProjectResponseDTO responseDTO = projectRepository.findByProjectName("Hyunjin Project");
        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getProjectStatus()).isEqualTo("활성");
    }
}