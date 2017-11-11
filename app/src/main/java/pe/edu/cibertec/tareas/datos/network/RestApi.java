package pe.edu.cibertec.tareas.datos.network;

import java.util.List;

import pe.edu.cibertec.tareas.datos.entity.TareaEntity;

public interface RestApi {

    List<TareaEntity> listarTareas() throws Exception;

    TareaEntity guardarTarea(TareaEntity tareaEntity) throws Exception;

    TareaEntity modificarTarea(TareaEntity tareaEntity) throws Exception;

    TareaEntity eliminarTarea(TareaEntity tareaEntity) throws Exception;
}
