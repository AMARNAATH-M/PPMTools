package io.agileintellligence.fullstack.services;

import io.agileintellligence.fullstack.domain.Backlog;
import io.agileintellligence.fullstack.domain.Project;
import io.agileintellligence.fullstack.domain.ProjectTask;
import io.agileintellligence.fullstack.exceptions.ProjectNotFoundException;
import io.agileintellligence.fullstack.repositories.BacklogRepository;
import io.agileintellligence.fullstack.repositories.ProjectRepository;
import io.agileintellligence.fullstack.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {


    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask,String username) {

        //Exceptions: Project not found


            //PTs to be added to a specific project, project != null, BL exists
            Backlog backlog =projectService.findProjectByIdentifier(projectIdentifier,username).getBacklog();
                    //backlogRepository.findByProjectIdentifier(projectIdentifier);
            //set the bl to pt
            projectTask.setBacklog(backlog);
            //we want our project sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
            Integer BacklogSequence = backlog.getPTSequence();
            // Update the BL SEQUENCE
            BacklogSequence++;
            backlog.setPTSequence(BacklogSequence);
            //Add Sequence to Project Task
            projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);


            //INITIAL status when status is null
            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }

            //INITIAL priority when priority null
            if (projectTask.getPriority()==null||projectTask.getPriority() == 0) {
                projectTask.setPriority(3);
            }

            return projectTaskRepository.save(projectTask);

    }

    public Iterable<ProjectTask> findByBacklogId(String backlog_id,String username) {
        projectService.findProjectByIdentifier(backlog_id,username);

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }

    public ProjectTask findByProjectSequence(String backlog_id, String sequence,String username) {
        projectService.findProjectByIdentifier(backlog_id,username);
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(sequence);
        if (projectTask == null) {
            throw new ProjectNotFoundException("Project Task id:" + sequence + " does not exist");
        }
        if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
            throw new ProjectNotFoundException("Project Task id:" + sequence + " does not exist in the project" + backlog_id);
        }
        return projectTask;
    }


    public ProjectTask updateProject(ProjectTask updatedProject, String backlog_id, String sequence,String username) {
        ProjectTask projectTask1 = findByProjectSequence(backlog_id, sequence,username);

        projectTask1 = updatedProject;
        return projectTaskRepository.save(projectTask1);
    }
    public  void deleteProject(String backlog_id, String sequence,String username)
    {
        ProjectTask projectTask1 = findByProjectSequence(backlog_id, sequence,username);
        projectTaskRepository.delete(projectTask1);
    }

}
