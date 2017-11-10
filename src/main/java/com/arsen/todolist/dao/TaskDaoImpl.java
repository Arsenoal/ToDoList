package com.arsen.todolist.dao;

import com.arsen.todolist.model.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {
    private Logger logger = LoggerFactory.getLogger(TaskDaoImpl.class.getSimpleName());
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public int addTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(task);
        session.flush();

        logger.info("task successfully saved " + task);

        return task.getId();
    }

    public void updateTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.update(task);
        logger.info("task successfully updated " + task);
    }

    public void removeTask(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.load(Task.class, new Integer(id));

        if(task != null)
            session.remove(task);

        logger.info("task successfully removed " + task);
    }

    public Task getTaskById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.load(Task.class, new Integer(id));

        logger.info("get task by id: " + id);

        return task;
    }

    @SuppressWarnings("unchecked")
    public List<Task> listTasks() {
        Session session = sessionFactory.getCurrentSession();
        List<Task> tasks = session.createQuery("FROM Task").list();

        logger.info("get list of tasks: " + tasks);

        return tasks;
    }
}
