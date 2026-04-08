package com.app.proDay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.proDay.service.TaskService;
import com.app.proDay.entity.Task;
import java.util.List;


@RestController
@RequestMapping("/tasks")

public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
     }

     //-----------------------------------

 @GetMapping ("/tasksList") //me lista las task
 public List<Task> listarTasks(){
     return taskService.listar();
 }


       // Devuelve la lista de tareas ya completadas
    @GetMapping("/baja")
    public List<Task> listarTaskBaja(){
        return taskService.listarBaja();
    }

   

    // POST /clientes
    // Crea un nuevo cliente en la base de datos
    @PostMapping
    public Task newTask(@RequestBody Task task){
        return taskService.create(task);
    }

    // PUT /clientes/{id}
    // Actualiza los datos de un cliente existente
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.update(id, task);
    }

    // PUT /clientes/{id}/baja
    // Da de baja al cliente (baja lógica, no se elimina de la base)
    // se supone que además de dar de baja el cliente, tambien doy de baja sus inscripciones
    @PutMapping("/{id}/done")
    public Task doneTask(@PathVariable Long id){
        return taskService.doneTask(id);
    }


}
