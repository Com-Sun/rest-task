package com.nhnacademy.task.service;

import static java.time.LocalDateTime.now;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.nhnacademy.task.domain.dto.comment.request.CommentCreateRequestDTO;
import com.nhnacademy.task.domain.dto.comment.request.CommentReadRequestDTO;
import com.nhnacademy.task.domain.dto.comment.request.CommentModifyRequestDTO;
import com.nhnacademy.task.entity.Comment;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.repository.CommentRepository;
import com.nhnacademy.task.repository.TaskRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private TaskRepository taskRepository;

    private Project project;
    private Task task;

    private Comment comment;

    @BeforeEach
    void setUp() {
        project= Project.builder()
            .projectName("This is Project!")
            .build();

        task = Task.builder()
            .project(project)
            .taskContent("일할 시간이다")
            .taskName("현진아")
            .taskCreatedDt(now())
            .taskCreatedMemNum(1L)
            .build();

        comment = Comment.builder()
            .task(task)
            .commentCreatedDt(now())
            .memberName("현진")
            .commentContent("안녕")
            .build();

    }

    @Test
    void createComment() {
        given(taskRepository.findById(any()))
            .willReturn(Optional.of(task));

        CommentCreateRequestDTO createRequestDTO = CommentCreateRequestDTO.builder()
            .commentNum(1L)
            .commentContent("안녕난여")
            .commentCreatedDt(now())
            .taskNum(1L)
            .memberName("현진짱")
            .build();

        commentService.createComment(createRequestDTO);

        verify(commentRepository, atLeastOnce()).save(any());
        verify(commentRepository, atLeastOnce()).queryByCommentNum(any());
    }

    @Test
    void readAllComments() {
        commentService.readAllComments();
        verify(commentRepository, atLeastOnce()).findAllBy();
    }

    @Test
    void readComment() {
        CommentReadRequestDTO readRequestDTO = CommentReadRequestDTO.builder()
            .commentNum(1L)
            .build();
        commentService.readComment(readRequestDTO);

        verify(commentRepository, atLeastOnce()).queryByCommentNum(any());
    }

    @Test
    void updateComment() {
        given(commentRepository.findById(any()))
            .willReturn(Optional.of(comment));

        CommentModifyRequestDTO modifyRequestDTO = CommentModifyRequestDTO.builder()
            .commentModifiedDt(now())
            .commentContent("수정맨")
            .commentNum(1L)
            .build();

        commentService.updateComment(modifyRequestDTO);

        verify(commentRepository, atLeastOnce()).findById(any());
        verify(commentRepository, atLeastOnce()).queryByCommentNum(any());
        verify(commentRepository, atLeastOnce()).save(any());
    }

    @Test
    void deleteComment() {
    }
}