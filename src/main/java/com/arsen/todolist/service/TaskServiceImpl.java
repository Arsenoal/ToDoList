package com.arsen.todolist.service;

import com.arsen.todolist.dao.TaskDao;
import com.arsen.todolist.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskDao taskDao;

    public void setTaskDao(TaskDao taskDao){
        this.taskDao = taskDao;
    }

    @Transactional
    public int addTask(Task task) {
        return taskDao.addTask(task);
    }

    @Transactional
    public void removeTask(int id) {
        taskDao.removeTask(id);
    }

    @Transactional
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    @Transactional
    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    @Transactional
    public List<Task> listTasks() {
        return taskDao.listTasks();
    }
}
