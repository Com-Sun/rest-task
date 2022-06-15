package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.dto.comment.response.CommentResponseDTO;
import com.nhnacademy.task.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<CommentResponseDTO> findByTask_TaskNum(Long taskNum);

    CommentResponseDTO queryByCommentNum(Long commentNum);

    List<CommentResponseDTO> findAllBy();
}
