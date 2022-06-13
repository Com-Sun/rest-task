package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.dto.comment.request.CommentCreateRequestDTO;
import com.nhnacademy.task.domain.dto.comment.request.CommentReadRequestDTO;
import com.nhnacademy.task.domain.dto.comment.request.CommentModifyRequestDTO;
import com.nhnacademy.task.domain.dto.comment.response.CommentResponseDTO;
import java.util.List;

public interface CommentService {

    CommentResponseDTO createComment(CommentCreateRequestDTO createRequestDTO);

    List<CommentResponseDTO> readAllComments();

    CommentResponseDTO readComment(CommentReadRequestDTO readRequestDTO);

    CommentResponseDTO updateComment(Long commentNum, CommentModifyRequestDTO updateRequestDTO);

    boolean deleteComment(Long commentNum);
}
