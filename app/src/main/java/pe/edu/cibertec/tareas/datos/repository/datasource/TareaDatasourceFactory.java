package pe.edu.cibertec.tareas.datos.repository.datasource;

import android.content.Context;

import pe.edu.cibertec.tareas.datos.database.TareaCache;
import pe.edu.cibertec.tareas.datos.database.TareaCacheImpl;
import pe.edu.cibertec.tareas.datos.network.RestApi;
import pe.edu.cibertec.tareas.datos.network.RestApiImpl;

public class TareaDatasourceFactory {

    private final Context context;

    public TareaDatasourceFactory(Context context) {
        this.context = context;
    }

    public TareaDatasource crearNetworkDatasource() {
        RestApi restApi = new RestApiImpl(context);
        TareaCache tareaCache = new TareaCacheImpl();
        return new NetworkNoticiaDatasource(restApi, tareaCache);
    }

    public  TareaDatasource  crearDiskDatasource(){
        TareaCache tareaCache = new TareaCacheImpl();
        return  new DiskTareaDatasource(tareaCache);
    }

    public TareaDatasource crear(boolean  forzarRed){
        return  forzarRed ?  crearNetworkDatasource() : crearDiskDatasource();
    }
}
