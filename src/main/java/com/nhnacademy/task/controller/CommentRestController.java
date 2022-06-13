package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.dto.comment.request.CommentCreateRequestDTO;
import com.nhnacademy.task.domain.dto.comment.request.CommentModifyRequestDTO;
import com.nhnacademy.task.domain.dto.comment.request.CommentReadRequestDTO;
import com.nhnacademy.task.domain.dto.comment.response.CommentResponseDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskCreateRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskModifyRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskReadRequestDTO;
import com.nhnacademy.task.domain.dto.task.response.TaskResponseDTO;
import com.nhnacademy.task.repository.CommentRepository;
import com.nhnacademy.task.service.CommentService;
import com.nhnacademy.task.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentRestController {

    private final CommentService commentService;
    private final TaskService taskService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/comments")
    public CommentResponseDTO createComment(@RequestBody CommentCreateRequestDTO requestDTO) {
        return commentService.createComment(requestDTO);
    }

    @GetMapping(value = "/comments")
    public List<CommentResponseDTO> readAllComments() {
        return commentService.readAllComments();
    }

    @GetMapping(value = "/comments/{commentNum}")
    public CommentResponseDTO readComment(@PathVariable(name = "commentNum") Long commentNum) {

        return commentService.readComment(CommentReadRequestDTO.builder()
            .commentNum(commentNum)
            .build());
    }

    @PutMapping(value = "/comments/{commentNum}")
    public CommentResponseDTO modifyComment(@PathVariable(name = "commentNum") Long commentNum, @RequestBody
    CommentModifyRequestDTO requestDTO) {

        return commentService.updateComment(commentNum, requestDTO);
    }

    @DeleteMapping(value = "/comments/{commentNum}")
    public boolean deleteComment(@PathVariable(name = "commentNum") Long commentNum) {
        return commentService.deleteComment(commentNum);
    }

}
