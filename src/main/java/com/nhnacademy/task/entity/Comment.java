package com.nhnacademy.task.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
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

    @Embeddable
    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @AllArgsConstructor
    private static class Pk implements Serializable {

        @Column(name = "comment_num")
        private Long commentNum;

        @Column(name = "project_num")
        private Long projectNum;
    }
}
