package com.nhnacademy.task.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "task")
@Entity
@DynamicInsert
public class Task {

    @Id
    @Column(name = "task_num")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long taskNum;
    @ManyToOne
    @JoinColumn(name = "project_num")
    private Project project;
    @Column(name = "task_created_mem_num")
    private Long taskCreatedMemNum;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_content")
    private String taskContent;
    @Column(name = "task_created_dt")
    private LocalDateTime taskCreatedDt;
    @Column(name = "task_modified_dt")
    private LocalDateTime taskModifiedDt;

    @Builder(builderClassName = "TaskBuilder")
    private Task(Project project, String taskName, String taskContent, LocalDateTime taskCreatedDt, Long taskCreatedMemNum) {
        this.project = project;
        this.taskName = taskName;
        this.taskContent = taskContent;
        this.taskCreatedDt = taskCreatedDt;
        this.taskCreatedMemNum = taskCreatedMemNum;
    }
}
