package com.app.proDay.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/task")

public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


// Crea una nueva tarea. El cliente envía los datos de la tarea en el cuerpo de la solicitud, y el servidor devuelve la tarea creada con su ID asignado.
//yo lo creo con los datos del constructor del entity (en la peticion de postman)
    @PostMapping
    public Task newTask(@RequestBody Task task){
        return taskService.create(task); //esto ya esta creado en el service 
    }


// 

 @GetMapping ("/tasksList") //me lista las task ¿TODAS ? O SEA, ACTIVAS E INACTIVAS
 public List<Task> listarTasks(){
     return taskService.listar();
 }

 // puedo ver las tareas que COMPLETE, TAREAS INACTIVAS A HACER

@GetMapping("/baja")
    public List<Task> listarTaskBaja(){
        return taskService.listarBaja();
    }

// Devuelve la lista de tareas NO COMPLETADAS, TAREAS ACTIVAS, TAREAS PENDIENTES A HACER
    @GetMapping("/alta")
    public List<Task> listarTaskAlta(){
        return taskService.listarAlta();
    }

    @GetMapping("/{id}") //ME TRAE LA TAREA POR ID 
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }


 // 5) puedo eliminar de la faz de la tierra esa tarea //METODO DELETE 

    @DeleteMapping("/eliminar/{id}")
    public void deleteTask(@PathVariable Long id) {
       taskService.delete(id);
    }


    @PutMapping("/{id}/done") //ESTE ES EL METODO PARA COMPLETAR LA TAREA, O SEA, PASARLA DE TRUE A FALSE
    public Task doneTask(@PathVariable Long id){
        return taskService.doneTask(id);
    }


    //puedo editar el contenido de  esa tarea // METODO PUT 

    @PutMapping("/modificar/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.update(id, task);
    }



}
