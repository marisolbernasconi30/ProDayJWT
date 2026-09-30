package com.app.proDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.proDay.entity.Task;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>{

    List<Task> findByIsCompletedTrue(); //me devuelve la lista de tareas ya completadas
    List<Task> findByIsCompletedFalse(); //me devuelve la lista de tareas pendientes
    
}
