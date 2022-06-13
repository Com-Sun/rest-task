package com.nhnacademy.task.repository;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.task.domain.dto.response.CommentResponseDTO;
import com.nhnacademy.task.entity.Comment;
import com.nhnacademy.task.entity.Project;
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
class CommentRepositoryTest {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ProjectRepository projectRepository;
    private Task task;
    private Project project;

    @BeforeEach
    void setUp() {
        project = Project.builder()
            .projectName("Hyunjin Project")
            .build();
        projectRepository.save(project);

        task = Task.builder()
            .taskCreatedMemNum(1L)
            .taskCreatedDt(now())
            .taskName("현진아 일하자")
            .taskContent("가즈아")
            .project(project)
            .build();
    }

    @Test
    void findCommentByProjectNumTest() {
        projectRepository.saveAndFlush(project);

        Comment comment = Comment.builder()
            .task(task)
            .commentCreatedDt(now())
            .memberName("현진")
            .commentContent("안녕")
            .build();

        taskRepository.saveAndFlush(task);

        commentRepository.save(comment);

        List<CommentResponseDTO> respondDtoList = commentRepository.findByTask_TaskNum(task.getTaskNum());

        assertThat(respondDtoList).hasSize(1);

    }
}