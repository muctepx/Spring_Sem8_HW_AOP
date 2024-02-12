package com.example.spring.data.service;

import com.example.spring.data.domain.Task;
import com.example.spring.data.domain.TaskStatus;
import com.example.spring.data.repository.TasksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с задачами.
 */

@Service
@AllArgsConstructor
public class TaskService {

    private final TasksRepository repository;

    /**
     * Получить все задачи.
     * @return список задач.
     */

    public List<Task> getAllTask(){
        return repository.findAll();
    }

    /**
     * Создать задачу.
     * @param task Задача.
     * @return Задачу.
     */

    public Task createTask(Task task){
        return repository.save(task);
    }

    /**
     * Получить задачи с одинаковым статусом.
     * @param taskStatus Статус.
     * @return Список задач.
     */

    public List<Task> getTasksByStatus(TaskStatus taskStatus) {

        return repository.findByStatus(taskStatus);
    }

    /**
     * Получить задачу по id.
     * @param id
     * @return Задачу, либо NULL.
     */

    public Optional<Task> getTasksById(Long id) {

        return repository.findById(id);
    }

    /**
     * Обновить статус задачи.
     * @param id
     * @param taskStatus
     * @return Задачу, либо NULL.
     */

    public Optional<Task> updateTaskStatus(Long id, TaskStatus taskStatus) {
        Optional<Task> task = getTasksById(id);
        if (task.isPresent()) {
            repository.changeTask(taskStatus, id);
            return getTasksById(id);
        }
        return task;
    }

    /**
     * Удалить задачу.
     * @param id
     */
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
