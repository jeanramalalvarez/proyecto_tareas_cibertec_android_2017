package pe.edu.cibertec.tareas.datos.repository.datasource;

import java.util.List;

import pe.edu.cibertec.tareas.datos.database.TareaCache;
import pe.edu.cibertec.tareas.datos.entity.TareaEntity;

public class DiskTareaDatasource   implements  TareaDatasource{

    private final TareaCache tareaCache;

    public DiskTareaDatasource(TareaCache tareaCache) {
        this.tareaCache = tareaCache;
    }

    @Override
    public List<TareaEntity> listarTareas() throws Exception {
        return tareaCache.listar();
    }

    @Override
    public TareaEntity guardarTarea(TareaEntity tareaEntity) throws Exception {
        throw  new UnsupportedOperationException("no autorizado");
    }

    @Override
    public TareaEntity modificarTarea(TareaEntity tareaEntity) throws Exception {
        return null;
    }

    @Override
    public TareaEntity eliminarTarea(TareaEntity tareaEntity) throws Exception {
        return null;
    }
}
