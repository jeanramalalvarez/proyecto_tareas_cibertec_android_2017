package pe.edu.cibertec.tareas.datos.database;

import java.util.List;

import pe.edu.cibertec.tareas.datos.entity.TareaEntity;

public interface TareaCache {

    List<TareaEntity> listar();
    void guardar(List<TareaEntity> tareaEntityList);
    void limpiar();
    void eliminar(String id);
}