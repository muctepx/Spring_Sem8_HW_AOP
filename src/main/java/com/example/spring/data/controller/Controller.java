package com.example.spring.data.controller;

import com.example.spring.data.domain.Task;
import com.example.spring.data.domain.TaskStatus;
import com.example.spring.data.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Контроллер для работы с задачами.
 */

@RestController
@AllArgsConstructor
public class Controller {

    private final TaskService taskService;

    /**
     * Обработчик стартовой страницы.
     * @return Список задач.
     */

    @GetMapping("/")
    public List<Task> getAllTask(){
        return taskService.getAllTask();
    }

    /**
     * Обработчик запроса на добавление задачи.
     * @param task
     * @return Задачу.
     */
    @PostMapping("/")
    public Task addTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }


    /**
     * Обработчик запроса на предоставление задач с заданным статусом.
     * @param status
     * @return Список задач.
     */

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    /**
     * Обработчик запроса на обновление статуса задачи.
     * @param id
     * @param task
     * @return Задачу, либо NULL.
     */

    @PutMapping("/{id}")
    public Optional<Task> updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {

        return taskService.updateTaskStatus(id, task.getTaskStatus());

    }

    /**
     * Обработчик запроса на удаление задачи.
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
