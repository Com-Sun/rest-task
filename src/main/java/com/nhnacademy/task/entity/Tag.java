package com.nhnacademy.task.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "tag")
public class Tag {

    @EmbeddedId
    private Pk pk;

    @MapsId("projectNum")
    @ManyToOne
    @JoinColumn(name = "project_num")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "task_num")
    private Task task;

    @Builder(builderClassName = "TagBuilder")
    private Tag(Pk pk, Task task) {
        this.pk = pk;
        this.task = task;
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    private static class Pk implements Serializable {

        @Column(name = "tag_name")
        private String tagName;
        @Column(name = "project_num")
        private Long projectNum;
    }
}
