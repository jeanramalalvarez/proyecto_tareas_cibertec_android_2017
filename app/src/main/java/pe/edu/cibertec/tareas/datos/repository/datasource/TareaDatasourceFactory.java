package pe.edu.cibertec.tareas.datos.repository.datasource;

import android.content.Context;

import pe.edu.cibertec.tareas.datos.network.RestApi;
import pe.edu.cibertec.tareas.datos.network.RestApiImpl;

public class TareaDatasourceFactory {

    private final Context context;

    public TareaDatasourceFactory(Context context) {
        this.context = context;
    }

    public TareaDatasource crearNetworkDatasource() {
        RestApi restApi = new RestApiImpl(context);
        return new NetworkNoticiaDatasource(restApi);
    }
}
