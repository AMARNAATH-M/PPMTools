package io.agileintellligence.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private String projectSequence;
    @NotBlank(message = "Please include Project Summary")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date dueDate;
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date created_At;
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date updated_At;
    //Many to One With BackLog
    @Column(updatable = false)
    private String projectIdentifier;


}
