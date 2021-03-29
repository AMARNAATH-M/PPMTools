package io.agileintellligence.fullstack.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence = 0;
    private String projectIdentifier;

    //OnetoOne with Project

    //OnetoMany with ProjectTask

}
