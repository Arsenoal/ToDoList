package com.arsen.todolist.dao;

import com.arsen.todolist.model.Task;

import java.util.List;

public interface TaskDao {
    int addTask(Task task);
    void updateTask(Task task);
    void removeTask(int id);
    Task getTaskById(int id);
    List<Task> listTasks();
}
