package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.dto.comment.request.CommentCreateRequestDTO;
import com.nhnacademy.task.domain.dto.comment.request.CommentModifyRequestDTO;
import com.nhnacademy.task.domain.dto.comment.request.CommentReadRequestDTO;
import com.nhnacademy.task.domain.dto.comment.response.CommentResponseDTO;
import com.nhnacademy.task.service.CommentService;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/projects/{project-num}/tasks/{task-num}/comments")
    public CommentResponseDTO createComment(@RequestBody CommentCreateRequestDTO requestDTO) {

        return commentService.createComment(requestDTO);
    }

    @GetMapping(value = "/projects/{project-num}/tasks/{task-num}/comments")
    public List<CommentResponseDTO> readAllComments() {

        return commentService.readAllComments();
    }

    @GetMapping(value = "/projects/{project-num}/tasks/{task-num}/comments/{commentNum}")
    public CommentResponseDTO readComment(@PathVariable(name = "commentNum") Long commentNum) {

        return commentService.readComment(CommentReadRequestDTO.builder()
            .commentNum(commentNum)
            .build());
    }

    @PutMapping(value = "/projects/{project-num}/tasks/{task-num}/comments/{commentNum}")
    public CommentResponseDTO modifyComment(@PathVariable(name = "commentNum") Long commentNum,
                                            @RequestBody CommentModifyRequestDTO requestDTO) {

        return commentService.updateComment(commentNum, requestDTO);
    }

    @DeleteMapping(value = "/projects/{project-num}/tasks/{task-num}/comments/{commentNum}")
    public boolean deleteComment(@PathVariable(name = "commentNum") Long commentNum) {

        return commentService.deleteComment(commentNum);
    }

}
