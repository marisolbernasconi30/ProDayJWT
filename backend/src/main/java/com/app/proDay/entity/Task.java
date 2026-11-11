package com.app.proDay.entity;
 
import java.time.LocalDate;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "task")
public class Task {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false)
    private String task;
 
    private LocalDate startDate;
    private LocalDate endDate;
 
    // false = pendiente, true = completada
    private boolean completed = false;
 
    // Dueño de la tarea. Obligatorio: no existe una tarea sin usuario.
    // LAZY: el usuario se carga solo cuando hace falta (los repositorios lo piden con @EntityGraph).
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
 
    public Task() {
    }
 
    public Task(String task, LocalDate startDate, LocalDate endDate, Usuario usuario) {
        this.task = task;
        this.startDate = startDate;
        this.endDate = endDate;
        this.usuario = usuario;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getTask() {
        return task;
    }
 
    public void setTask(String task) {
        this.task = task;
    }
 
    public LocalDate getStartDate() {
        return startDate;
    }
 
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
 
    public LocalDate getEndDate() {
        return endDate;
    }
 
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
 
    public boolean isCompleted() {
        return completed;
    }
 
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
 
    public Usuario getUsuario() {
        return usuario;
    }
 
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
 