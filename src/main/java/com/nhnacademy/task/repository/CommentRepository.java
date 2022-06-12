package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.dto.response.CommentResponseDTO;
import com.nhnacademy.task.entity.Comment;
import com.nhnacademy.task.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Comment.Pk> {
    List<CommentResponseDTO> findByPk_ProjectNum(Long projectNum);
}
