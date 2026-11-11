package com.app.proDay.controller;
 
import java.util.List;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.app.proDay.dto.TaskResponse;
import com.app.proDay.service.TaskService;
 
// Todo lo que cuelga de /admin/** queda restringido a ADMIN en SecurityConfig.
@RestController
@RequestMapping("/admin")
public class AdminTaskController {
 
    private final TaskService taskService;
 
    public AdminTaskController(TaskService taskService) {
        this.taskService = taskService;
    }
 
    // Tareas de todos los usuarios, cada una con su userId y username
    @GetMapping("/tasks")
    public List<TaskResponse> listarTodas() {
        return taskService.listarTodas();
    }
}