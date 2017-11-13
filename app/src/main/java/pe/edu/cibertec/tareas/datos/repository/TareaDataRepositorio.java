package pe.edu.cibertec.tareas.datos.repository;

import java.util.List;

import pe.edu.cibertec.tareas.datos.entity.TareaEntity;
import pe.edu.cibertec.tareas.datos.entity.mapper.TareaEntityDataMapper;
import pe.edu.cibertec.tareas.datos.repository.datasource.TareaDatasource;
import pe.edu.cibertec.tareas.datos.repository.datasource.TareaDatasourceFactory;
import pe.edu.cibertec.tareas.dominio.model.Tarea;
import pe.edu.cibertec.tareas.dominio.repository.TareaRepositorio;

public class TareaDataRepositorio implements TareaRepositorio {

    private final TareaDatasourceFactory noticiaDatasourceFactory;
    private final TareaEntityDataMapper noticiaEntityDataMapper;

    public TareaDataRepositorio(TareaDatasourceFactory tareaDatasourceFactory,
                                TareaEntityDataMapper tareaEntityDataMapper) {
        this.noticiaDatasourceFactory = tareaDatasourceFactory;
        this.noticiaEntityDataMapper = tareaEntityDataMapper;
    }

    @Override
    public List<Tarea> listarTareas(boolean forzarred) throws Exception {
        final TareaDatasource tareaDatasource = noticiaDatasourceFactory.crear(forzarred);
        List<TareaEntity> tareaEntityList = tareaDatasource.listarTareas();
        return noticiaEntityDataMapper.transformar(tareaEntityList);
    }

    @Override
    public Tarea guardarTarea(Tarea tarea) throws Exception {
        final TareaDatasource noticiaDatasource = noticiaDatasourceFactory.crearNetworkDatasource();
        TareaEntity tareaEntity = noticiaDatasource.guardarTarea(noticiaEntityDataMapper.transformar(tarea));
        return noticiaEntityDataMapper.transformar(tareaEntity);
    }

    @Override
    public Tarea modificarTarea(Tarea tarea) throws Exception {
        final TareaDatasource noticiaDatasource = noticiaDatasourceFactory.crearNetworkDatasource();
        TareaEntity tareaEntity = noticiaDatasource.modificarTarea(noticiaEntityDataMapper.transformar(tarea));
        return noticiaEntityDataMapper.transformar(tareaEntity);
    }

    @Override
    public Tarea eliminarTarea(Tarea tarea) throws Exception {
        final TareaDatasource noticiaDatasource = noticiaDatasourceFactory.crearNetworkDatasource();
        TareaEntity tareaEntity = noticiaDatasource.eliminarTarea(noticiaEntityDataMapper.transformar(tarea));
        return noticiaEntityDataMapper.transformar(tareaEntity);
    }
}
