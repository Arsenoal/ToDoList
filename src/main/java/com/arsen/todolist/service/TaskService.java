package com.arsen.todolist.service;

import com.arsen.todolist.model.Task;

import java.util.List;

public interface TaskService {
    int addTask(Task task);
    void removeTask(int id);
    void updateTask(Task task);
    Task getTaskById(int id);
    List<Task> listTasks();

}
