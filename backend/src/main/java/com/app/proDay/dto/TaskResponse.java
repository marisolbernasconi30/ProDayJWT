package com.app.proDay.dto;
 
import java.time.LocalDate;
 
import com.app.proDay.entity.Task;
 
// Lo que devuelve la API: la tarea más quién es su dueño (sin datos sensibles como la contraseña).
public record TaskResponse(
        Long id,
        String task,
        LocalDate startDate,
        LocalDate endDate,
        boolean completed,
        Long userId,
        String username) {
 
    public static TaskResponse from(Task t) {
        return new TaskResponse(
                t.getId(),
                t.getTask(),
                t.getStartDate(),
                t.getEndDate(),
                t.isCompleted(),
                t.getUsuario().getId(),
                t.getUsuario().getUsername());
    }
}