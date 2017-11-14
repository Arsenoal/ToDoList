package com.arsen.todolist.controller;

import com.arsen.todolist.model.Task;
import com.arsen.todolist.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);
    private TaskService taskService;

    @Autowired
    @Qualifier(value = "taskService")
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> listTasks(){
        logger.info("get all tasks");
        List<Task> tasks = taskService.listTasks();

        if(tasks.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTask(@PathVariable("id") int id){
        logger.info("get task by id");
        Task task = taskService.getTaskById(id);

        if(task == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        logger.info("add a task");

        if(task.getId() == 0){
            int id = taskService.addTask(task);

            Task storedTask = taskService.getTaskById(id);

            if(storedTask == null)
                return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<Task>(HttpStatus.OK);
        }else{
            taskService.updateTask(task);
            return new ResponseEntity<Task>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@PathVariable("id") int id, @RequestBody Task task){
        logger.info("update a task by id " + id);

        Task findTask = taskService.getTaskById(id);

        if(findTask == null){
            logger.info(String.format("Task with id %d not found", id));

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        findTask.setId(task.getId());
        findTask.setDescription(task.getDescription());
        findTask.setDone(task.isDone());
        findTask.setDate(task.getDate());
        taskService.updateTask(findTask);

        return new ResponseEntity<>(findTask, HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Task> deleteTask(@PathVariable("id") int id){
        logger.info("deleting task by id " + id);

        Task findTask = taskService.getTaskById(id);

        if(findTask == null){
            logger.info(String.format("task by id %d not found", id));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        taskService.removeTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
