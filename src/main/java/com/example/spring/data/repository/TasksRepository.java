package com.example.spring.data.repository;

import com.example.spring.data.domain.Task;
import com.example.spring.data.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Интерфейс для работы с Базой данных.
 */

@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {

    /**
     * Получить список задач по статусу.
     * @param taskStatus
     * @return лист задач.
     */

    @Query("Select t from Task t where t.taskStatus = :taskStatus")
    List<Task> findByStatus(TaskStatus taskStatus);

    /**
     * Обновить статус задачи.
     * @param taskStatus
     * @param id
     */

    @Modifying
    @Transactional
    @Query("UPDATE Task SET taskStatus = :taskStatus WHERE id = :id")
    void changeTask(TaskStatus taskStatus, Long id);
}
