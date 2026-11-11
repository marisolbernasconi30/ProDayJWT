package com.app.proDay.dto;
 
import java.time.LocalDate;
 
// Lo único que el cliente puede mandar al crear o editar una tarea.
// No incluye id, usuario ni completed: eso lo decide el servidor.
// Las fechas van como "yyyy-MM-dd", por ejemplo "2026-10-28".
public record TaskRequest(String task, LocalDate startDate, LocalDate endDate) {
}
 