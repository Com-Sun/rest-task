package com.nhnacademy.task.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @EmbeddedId
    private Pk pk;

    @MapsId("projectNum")
    @ManyToOne
    @JoinColumn(name = "project_num")
    private Project project;

    @Column(name = "task_num")
    private Long taskNum;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_created_dt")
    private LocalDateTime commentCreatedDt;

    @Column(name = "comment_modified_dt")
    private LocalDateTime commentModifiedDt;

    @Column(name = "member_name")
    private String memberName;

    @Builder(builderClassName = "CommentBuilder")
    private Comment (Pk pk, Project project, Long taskNum, String commentContent, LocalDateTime commentCreatedDt, String memberName) {
        this.pk = pk;
        this.project = project;
        this.taskNum = taskNum;
        this.commentContent = commentContent;
        this.commentCreatedDt = commentCreatedDt;
        this.memberName = memberName;
    }


    @Embeddable
    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Pk implements Serializable {

        @Column(name = "comment_num")
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long commentNum;

        @Column(name = "project_num")
        private Long projectNum;
    }
}
