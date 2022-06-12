package com.nhnacademy.task.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.task.domain.dto.response.TagResponseDTO;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Tag;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TagRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TagRepository tagRepository;
    private Project project;

    @BeforeEach
    void setUp() {
        project = Project.builder()
            .projectName("Hyunjin Project")
            .projectStatus("활성")
            .build();
        projectRepository.save(project);
    }

    @Test
    void findTagByProjectNumTest() {
        Tag tag = Tag.builder()
            .pk(new Tag.Pk("가즈아~", project.getProjectNum()))
            .project(project)
            .taskNum(1L)
            .build();
        tagRepository.save(tag);

        List<TagResponseDTO> responseDTOList = tagRepository.findByPk_ProjectNum(project.getProjectNum());

        assertThat(responseDTOList).hasSize(1);
    }

}