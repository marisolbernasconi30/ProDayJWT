package com.app.proDay.entity;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;  

@Entity
@Table(name = "") //esto lo tengo que cambiar, poner el nombre de la tabla de la base de datos 
public class Task {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Task;
    private Date StartDate;
    private Date EndDate;
    private boolean isCompleted = true;

    public Task(){

    }

    public Task (String Task, Date StartDate, Date EndDate){
        this.Task = Task;
        this.StartDate = StartDate;
        this.EndDate = EndDate; 
        
    }
    public Long getId() { // es el getter del id
        return id;
    }

     public void setId(Long id) {
        this.id = id;
    }

    public String getTask() { //es el getter del string task
        return Task;
    }

    public void setTask(String task) { //es el setter del string task
        Task = task;
    }

    public Date getStartDate() { // es el getter de la fecha de inicio
        return StartDate;
    }

    public void setStartDate(Date startDate) { // es el setter de la fecha de inicio
        StartDate = startDate;
    }

    public Date getEndDate() { //es el getter de la fecha de fin
        return EndDate;
    }

    public void setEndDate(Date endDate) { // es el setter de la fecha de fin
        EndDate = endDate;
    }

    public boolean isCompleted() { // es el getter de la propiedad isCompleted
        return isCompleted;
    }

    public void setCompleted(boolean completed) { // es el setter de la propiedad isCompleted
        isCompleted = completed;
    }

}

