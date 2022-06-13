package com.nhnacademy.task.service.impl;

import static java.time.LocalDateTime.now;

import com.nhnacademy.task.domain.dto.comment.request.CommentCreateRequestDTO;
import com.nhnacademy.task.domain.dto.comment.request.CommentReadRequestDTO;
import com.nhnacademy.task.domain.dto.comment.request.CommentModifyRequestDTO;
import com.nhnacademy.task.domain.dto.comment.response.CommentResponseDTO;
import com.nhnacademy.task.entity.Comment;
import com.nhnacademy.task.exception.CommentNotFoundException;
import com.nhnacademy.task.exception.TaskNotFoundException;
import com.nhnacademy.task.repository.CommentRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public CommentResponseDTO createComment(CommentCreateRequestDTO createRequestDTO) {
        Comment comment = Comment.builder()
            .task(taskRepository.findById(createRequestDTO.getTaskNum()).orElseThrow(() -> new TaskNotFoundException("해당 업무가 존재하지 않습니다.")))
            .commentContent(createRequestDTO.getCommentContent())
            .commentCreatedDt(now())
            .memberName(createRequestDTO.getMemberName())
            .build();

        commentRepository.save(comment);
        return commentRepository.queryByCommentNum(comment.getCommentNum());
    }

    @Override
    public List<CommentResponseDTO> readAllComments() {
        return commentRepository.findAllBy();
    }

    @Override
    public CommentResponseDTO readComment(CommentReadRequestDTO readRequestDTO) {
        Comment comment = commentRepository.findById(readRequestDTO.getCommentNum()).orElseThrow(() -> new CommentNotFoundException("해당 댓글이 존재하지 않습니다."));

        return commentRepository.queryByCommentNum(comment.getCommentNum());
    }

    @Override
    public CommentResponseDTO updateComment(Long commentNum, CommentModifyRequestDTO updateRequestDTO) {
        Comment comment = commentRepository.findById(commentNum).orElseThrow(() -> new CommentNotFoundException("해당 댓글이 존재하지 않습니다."));
        comment.setCommentContent(updateRequestDTO.getCommentContent());
        comment.setCommentModifiedDt(now());
        commentRepository.save(comment);
        return commentRepository.queryByCommentNum(comment.getCommentNum());
    }

    @Override
    public boolean deleteComment(Long commentNum) {
        return false;
    }
}
