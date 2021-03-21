package io.agileintellligence.fullstack.web;

import io.agileintellligence.fullstack.domain.Project;
import io.agileintellligence.fullstack.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result)
    {
        if(result.hasErrors())
        {
            Map<String, String> mp = new HashMap<String, String>();
            for(FieldError error:result.getFieldErrors())
            {
               mp.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(mp,HttpStatus.BAD_REQUEST);
        }
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(project1, HttpStatus.CREATED);
    }



}
