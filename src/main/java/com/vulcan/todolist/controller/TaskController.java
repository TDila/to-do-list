package com.vulcan.todolist.controller;

import com.vulcan.todolist.entity.Task;
import com.vulcan.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long taskId){
        Task task = taskService.getTaskById(taskId).orElseThrow(() -> new RuntimeException("Task Not Found, id: "+taskId));
        return task;
    }
    @PostMapping
    public Task createTask(@RequestBody Task theTask){
        theTask.setId(0L);
        return taskService.createTask(theTask);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId,@RequestBody Task theTask){
        return taskService.updateTask(taskId, theTask);
    }
    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return "Task deleted, id: "+taskId;
    }

}
