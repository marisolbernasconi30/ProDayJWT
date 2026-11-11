package com.app.proDay.controller;
 
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
 
import com.app.proDay.dto.TaskRequest;
import com.app.proDay.dto.TaskResponse;
import com.app.proDay.service.TaskService;
 
// Rutas del usuario logueado. Todas actúan SOLO sobre sus propias tareas:
// authentication.getName() es el username de quien hace la request.
@RestController
@RequestMapping("/task")
public class TaskController {
 
    private final TaskService taskService;
 
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
 
    @PostMapping("/CreateTask")
    public TaskResponse crear(@RequestBody TaskRequest request, Authentication authentication) {
        return taskService.crear(request, authentication.getName());
    }
 
    // Lista solo las tareas del usuario logueado
    @GetMapping("/tasksList")
    public List<TaskResponse> listar(Authentication authentication) {
        return taskService.listarPropias(authentication.getName());
    }
 
    @GetMapping("/{id}")
    public TaskResponse obtener(@PathVariable Long id, Authentication authentication) {
        return taskService.obtenerPropia(id, authentication.getName());
    }
 
    @PutMapping("/modificar/{id}")
    public TaskResponse actualizar(@PathVariable Long id, @RequestBody TaskRequest request,
            Authentication authentication) {
        return taskService.actualizarPropia(id, request, authentication.getName());
    }
 
    // Marca la tarea como completada (completed = true)
    @PutMapping("/{id}/done")
    public TaskResponse completar(@PathVariable Long id, Authentication authentication) {
        return taskService.completarPropia(id, authentication.getName());
    }
 
    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id, Authentication authentication) {
        taskService.eliminarPropia(id, authentication.getName());
    }
}
 