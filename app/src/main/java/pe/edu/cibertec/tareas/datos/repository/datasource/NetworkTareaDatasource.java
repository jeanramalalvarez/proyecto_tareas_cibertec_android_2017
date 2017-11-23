package pe.edu.cibertec.tareas.datos.repository.datasource;

import java.util.List;

import pe.edu.cibertec.tareas.datos.database.TareaCache;
import pe.edu.cibertec.tareas.datos.entity.TareaEntity;
import pe.edu.cibertec.tareas.datos.network.RestApi;

public class NetworkTareaDatasource implements TareaDatasource {

    private final RestApi restApi;

    private  final TareaCache tareaCache;

    public NetworkTareaDatasource(RestApi restApi, TareaCache tareaCache) {
        this.restApi = restApi;
        this.tareaCache = tareaCache;
    }

    @Override
    public List<TareaEntity> listarTareas() throws Exception {
        List<TareaEntity> list = restApi.listarTareas();
        tareaCache.guardar(list);
        return list;
    }

    @Override
    public TareaEntity guardarTarea(TareaEntity tareaEntity) throws Exception {
        return restApi.guardarTarea(tareaEntity);
    }

    @Override
    public TareaEntity modificarTarea(TareaEntity tareaEntity) throws Exception {
        return restApi.modificarTarea(tareaEntity);
    }

    @Override
    public TareaEntity eliminarTarea(TareaEntity tareaEntity) throws Exception {
        tareaCache.eliminar(tareaEntity.getId());
        return restApi.eliminarTarea(tareaEntity);
    }
}
