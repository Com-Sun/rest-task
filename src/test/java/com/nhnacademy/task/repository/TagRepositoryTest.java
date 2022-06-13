package com.nhnacademy.task.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.task.domain.dto.response.TagResponseDTO;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Tag;
import com.nhnacademy.task.entity.Task;
import java.time.LocalDateTime;
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

    @Autowired
    TaskRepository taskRepository;

    private Project project;
    private Task task;

    @BeforeEach
    void setUp() {
        project = Project.builder()
            .projectName("Hyunjin Project")
            .build();
        projectRepository.saveAndFlush(project);

        task = Task.builder()
            .project(project)
            .taskContent("일할시간")
            .taskCreatedDt(LocalDateTime.now())
            .taskName("현진아")
            .taskCreatedMemNum(1L)
            .build();
        taskRepository.saveAndFlush(task);
    }

    @Test
    void findTagByProjectNumTest() {
        Tag tag = Tag.builder()
            .pk(new Tag.Pk("가즈아~", project.getProjectNum()))
            .project(project)
            .task(task)
            .build();
        tagRepository.save(tag);

        List<TagResponseDTO> responseDTOList = tagRepository.findByPk_ProjectNum(project.getProjectNum());

        assertThat(responseDTOList).hasSize(1);
    }

}