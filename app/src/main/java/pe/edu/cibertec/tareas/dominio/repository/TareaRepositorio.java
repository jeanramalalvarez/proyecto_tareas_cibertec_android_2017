package pe.edu.cibertec.tareas.dominio.repository;

import java.util.List;

import pe.edu.cibertec.tareas.dominio.model.Tarea;

public interface TareaRepositorio {

    List<Tarea> listarTareas(boolean forzarred) throws Exception;

    Tarea guardarTarea(Tarea tarea) throws Exception;

    Tarea modificarTarea(Tarea tarea) throws Exception;

    Tarea eliminarTarea(Tarea tarea) throws Exception;
}
