package com.app.proDay.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.app.proDay.entity.Task;
import com.app.proDay.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    public List<Task> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    public Task getTaskById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTaskById'");
    }

    public List<Task> listarBaja() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarBaja'");
    }

    public Task create(Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    public Task update(Long id, Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public Task doneTask(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doneTask'");
    }

}
