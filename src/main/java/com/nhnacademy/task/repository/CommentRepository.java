package com.nhnacademy.task.repository;

import com.nhnacademy.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Task, Task.Pk> {
}
