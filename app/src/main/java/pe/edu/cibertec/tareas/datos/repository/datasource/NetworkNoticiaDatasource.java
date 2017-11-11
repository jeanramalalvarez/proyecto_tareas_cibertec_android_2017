package pe.edu.cibertec.tareas.datos.repository.datasource;

import java.util.List;

import pe.edu.cibertec.tareas.datos.entity.TareaEntity;
import pe.edu.cibertec.tareas.datos.network.RestApi;

public class NetworkNoticiaDatasource implements TareaDatasource {

    private final RestApi restApi;

    public NetworkNoticiaDatasource(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public List<TareaEntity> listarTareas() throws Exception {
        return restApi.listarTareas();
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
        return restApi.eliminarTarea(tareaEntity);
    }
}
