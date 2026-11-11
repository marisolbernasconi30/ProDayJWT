package com.app.proDay.repository;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.app.proDay.entity.Task;
 
public interface TaskRepository extends JpaRepository<Task, Long> {
 
    // ADMIN: todas las tareas. @EntityGraph trae el usuario en la misma consulta,
    // así no se hace una consulta extra por cada tarea.
    @EntityGraph(attributePaths = "usuario")
    List<Task> findAllByOrderByIdAsc();
 
    // USUARIO COMÚN: solo sus tareas (se filtra por el username del usuario logueado)
    @EntityGraph(attributePaths = "usuario")
    List<Task> findByUsuarioUsernameOrderByIdAsc(String username);
 
    // Para ver, editar, completar o borrar: solo si la tarea es de ese usuario.
    // Si es de otro, devuelve vacío, igual que si no existiera.
    @EntityGraph(attributePaths = "usuario")
    Optional<Task> findByIdAndUsuarioUsername(Long id, String username);
}