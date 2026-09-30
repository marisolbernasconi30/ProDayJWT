package com.app.proDay.entity;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;  

@Entity
@Table(name = "task") // poner el nombre de la tabla de la base de datos 
public class Task {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String task;
    private Date startDate;
    private Date endDate;
    private boolean isCompleted = true; //HACE REFERENCIA A QUE SI ESTÁ LA TASK COMPLETADA O NO

    // RELACIONO CON LA OTRA BASE DE DATOS
    @ManyToOne
    @JoinColumn(name = "usuario_id") // columna en la DB DE USUARIOS
    private Usuario usuario;

    public Task(){

    }

    public Task (String task, Date startDate, Date endDate){
        this.task = task;
        this.startDate = startDate;
        this.endDate = endDate; 
        //aca esta raro
    }
    public Long getId() { // es el getter del id
        return id;
    }

     public void setId(Long id) {
        this.id = id;
    }

    public String getTask() { //es el getter del string task
        return task;
    }

    public void setTask(String task) { //es el setter del string task
        this.task = task;
    }

    public Date getStartDate() { // es el getter de la fecha de inicio
        return startDate;
    }

    public void setStartDate(Date startDate) { // es el setter de la fecha de inicio
        this.startDate = startDate;
    }

    public Date getEndDate() { //es el getter de la fecha de fin
        return endDate;
    }

    public void setEndDate(Date endDate) { // es el setter de la fecha de fin
        this.endDate = endDate;
    }

    public boolean isCompleted() { // es el getter de la propiedad isCompleted
        return isCompleted;
    }

    public void setCompleted(boolean completed) { // es el setter de la propiedad isCompleted
        isCompleted = completed;
    }

    public void setUsuario(Usuario usuario2) {
        this.usuario = usuario2;
    }

}

