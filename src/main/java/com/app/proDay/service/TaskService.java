package com.app.proDay.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.app.proDay.entity.Task;
import com.app.proDay.repository.TaskRepository;
import jakarta.transaction.Transactional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    public Task create(Task task) { //este es el metodo POST, para CREAR una task
        return taskRepository.save(task); //este metodo (save) lo heredamos en JPARepository, lo que hace es guardar el objeto task en la base de datos y devuelve el objeto guardado con su ID asignado
        //por eso no es necesario crear el metodo en el repository
    }


    public List<Task> listar() {
        return taskRepository.findAll(); //Metodo get, lista TODAS las tareas
    }

    public Task getTaskById(Long id) { //METODO GET. ME TRAE LA TAREA POR ID
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> listarBaja() { //GET PARA LISTAR LAS TAREAS INACTIVAS, O SEA, LAS QUE YA COMPLETE
        return taskRepository.findByIsCompletedFalse();
    }

    public List<Task> listarAlta() { //GET PARA LISTAR LAS TAREAS ACTIVAS, O SEA, LAS QUE NO HAN COMPLETE
        return taskRepository.findByIsCompletedTrue();
    }

    public Task update(Long id, Task task) { //este es el metodo put para modificar algo de la tarea
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task no encontrada"));
       
            existingTask.setTask(task.getTask());
            existingTask.setStartDate(task.getStartDate());
            existingTask.setEndDate(task.getEndDate());
            existingTask.setCompleted(task.isCompleted());
          
            return taskRepository.save(existingTask);
       
    }

    //esto es para el metodo put del controller, que modifica el estado de la tarea (activo a inactivo)
 @Transactional
    public Task doneTask(Long id) {
       Task terminadaTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task no encontrada"));
       terminadaTask.setCompleted(false); //ESTO ES PARA CAMBIAR EL ESTADO DE LA TAREA, O SEA, PASARLA DE TRUE A FALSE    
       return taskRepository.save(terminadaTask);

    }

    //METODO DELETE POR ID, PARA ELIMINAR LA TAREA DE LA BASE DE DATOS
    public void delete(Long id) { 
        taskRepository.deleteById(id);
    }

}
