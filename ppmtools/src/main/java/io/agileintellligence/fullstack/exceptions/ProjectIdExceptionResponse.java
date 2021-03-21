package io.agileintellligence.fullstack.exceptions;

import lombok.Data;

@Data
public class ProjectIdExceptionResponse {

    private String projectIdentifier;

    public ProjectIdExceptionResponse(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

}
