package com.nhnacademy.task.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.task.domain.dto.response.CommentResponseDTO;
import com.nhnacademy.task.entity.Comment;
import com.nhnacademy.task.entity.Project;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CommentRepository commentRepository;
    private Project project;

    @BeforeEach
    void setUp() {
        project = Project.builder()
            .projectName("Hyunjin Project")
            .projectStatus("활성")
            .build();
    }

    @Test
    void findCommentByProjectNumTest() {
        projectRepository.saveAndFlush(project);

        Comment comment = Comment.builder()
            .pk(new Comment.Pk(1L, project.getProjectNum()))
            .project(project)
            .commentCreatedDt(LocalDateTime.now())
            .taskNum(1L)
            .memberName("현진")
            .commentContent("안녕")
            .build();

        commentRepository.save(comment);

        List<CommentResponseDTO> respondDtoList = commentRepository.findByPk_ProjectNum(project.getProjectNum());

        assertThat(respondDtoList).hasSize(1);

    }
}