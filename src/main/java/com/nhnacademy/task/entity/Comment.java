package com.nhnacademy.task.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "comment_num")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long commentNum;
    @ManyToOne
    @JoinColumn(name = "task_num")
    private Task task;

    @Column(name = "member_name")
    private String memberName;
    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_created_dt")
    private LocalDateTime commentCreatedDt;

    @Column(name = "comment_modified_dt")
    private LocalDateTime commentModifiedDt;

    @Builder(builderClassName = "CommentBuilder")
    private Comment (Task task, String commentContent, LocalDateTime commentCreatedDt, String memberName) {
        this.task = task;
        this.commentContent = commentContent;
        this.commentCreatedDt = commentCreatedDt;
        this.memberName = memberName;
    }

}
