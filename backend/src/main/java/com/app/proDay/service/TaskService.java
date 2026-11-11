package com.app.proDay.service;
 
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
 
import com.app.proDay.dto.TaskRequest;
import com.app.proDay.dto.TaskResponse;
import com.app.proDay.entity.Task;
import com.app.proDay.entity.Usuario;
import com.app.proDay.repository.TaskRepository;
import com.app.proDay.repository.UsuarioRepository;
 
@Service
public class TaskService {
 
    private final TaskRepository taskRepository;
    private final UsuarioRepository usuarioRepository;
 
    public TaskService(TaskRepository taskRepository, UsuarioRepository usuarioRepository) {
        this.taskRepository = taskRepository;
        this.usuarioRepository = usuarioRepository;
    }
 
    // ---------------------------------------------------------------
    // USUARIO COMÚN: todo se hace sobre SUS tareas (username = el usuario logueado)
    // ---------------------------------------------------------------
 
    @Transactional
    public TaskResponse crear(TaskRequest request, String username) {
        validar(request);
 
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));
 
        Task task = new Task(request.task().trim(), request.startDate(), request.endDate(), usuario);
        return TaskResponse.from(taskRepository.save(task));
    }
 
    @Transactional(readOnly = true)
    public List<TaskResponse> listarPropias(String username) {
        return taskRepository.findByUsuarioUsernameOrderByIdAsc(username).stream()
                .map(TaskResponse::from)
                .toList();
    }
 
    @Transactional(readOnly = true)
    public TaskResponse obtenerPropia(Long id, String username) {
        return TaskResponse.from(buscarPropia(id, username));
    }
 
    @Transactional
    public TaskResponse actualizarPropia(Long id, TaskRequest request, String username) {
        validar(request);
 
        Task task = buscarPropia(id, username);
        task.setTask(request.task().trim());
        task.setStartDate(request.startDate());
        task.setEndDate(request.endDate());
        // No hace falta save(): al terminar el método, JPA guarda los cambios de la entidad.
        return TaskResponse.from(task);
    }
 
    @Transactional
    public TaskResponse completarPropia(Long id, String username) {
        Task task = buscarPropia(id, username);
        task.setCompleted(true);
        return TaskResponse.from(task);
    }
 
    @Transactional
    public void eliminarPropia(Long id, String username) {
        taskRepository.delete(buscarPropia(id, username));
    }
 
    // ---------------------------------------------------------------
    // ADMIN: ve las tareas de todos los usuarios
    // ---------------------------------------------------------------
 
    @Transactional(readOnly = true)
    public List<TaskResponse> listarTodas() {
        return taskRepository.findAllByOrderByIdAsc().stream()
                .map(TaskResponse::from)
                .toList();
    }
 
    // ---------------------------------------------------------------
    // Auxiliares
    // ---------------------------------------------------------------
 
    // Si la tarea no existe o es de otro usuario, la respuesta es la misma (404):
    // así nadie puede descubrir qué ids existen.
    private Task buscarPropia(Long id, String username) {
        return taskRepository.findByIdAndUsuarioUsername(id, username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada"));
    }
 
    private void validar(TaskRequest request) {
        if (request.task() == null || request.task().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La tarea no puede estar vacía");
        }
        if (request.startDate() != null && request.endDate() != null
                && request.endDate().isBefore(request.startDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de fin no puede ser anterior a la de inicio");
        }
    }
}