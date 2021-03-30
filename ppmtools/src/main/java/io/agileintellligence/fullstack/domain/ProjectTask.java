package io.agileintellligence.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(updatable = false,unique = true)
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
    @Column(updatable = false)
    private String projectIdentifier;
    @Column(insertable = false,updatable=false)
    private int backlog_id;


    //Many to One With BackLog
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
    @JoinColumn(name="backlog_id",updatable=false,nullable=false)
    @JsonIgnore
    private Backlog backlog;




}
