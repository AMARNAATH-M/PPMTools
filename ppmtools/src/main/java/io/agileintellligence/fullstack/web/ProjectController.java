package io.agileintellligence.fullstack.web;

import io.agileintellligence.fullstack.domain.Project;
import io.agileintellligence.fullstack.services.MapValidationErrorService;
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

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result)
    {
        ResponseEntity<?> error = mapValidationErrorService.MapValidationErrorService(result);
        if(error != null)
            return error;

        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(project1, HttpStatus.CREATED);
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId)
    {
        Project project = projectService.findByProjectIdentifier(projectId);
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }
    @GetMapping("/all")
    public Iterable<Project> getAllProjects()
    {
        return projectService.findAllProjects();
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable String projectId)
    {
       projectService.deleteProjectByProjectIdentifier(projectId);
        return new ResponseEntity<String>("Project with " + projectId + " Successfully deleted", HttpStatus.OK);
    }
    @PutMapping("/{projectId}")
    public ResponseEntity<?> UpdateProject(@PathVariable String projectId,@RequestBody Project project, BindingResult result)
    {


        Project project1 = projectService.findByProjectIdentifier(projectId.toUpperCase());
        if(project1 == null)
            return new ResponseEntity<String>("Project with ID " + projectId + "Does not exists",HttpStatus.NOT_FOUND);
        Project project2=projectService.updateProject(projectId,project);
        return new ResponseEntity<>(project2, HttpStatus.CREATED);
    }



}
