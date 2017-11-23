package pe.edu.cibertec.tareas.datos.repository.datasource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pe.edu.cibertec.tareas.datos.database.TareaCache;
import pe.edu.cibertec.tareas.datos.entity.TareaEntity;
import pe.edu.cibertec.tareas.datos.network.RestApi;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by MI EQUIPO on 23/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class NetworkTareaDatasourceTest {

    private NetworkTareaDatasource networkTareaDatasource;

    @Mock
    private RestApi mockRestApi;

    @Mock
    private TareaCache mockTareaCache;

    @Before
    public void setUp() throws Exception {
        this.networkTareaDatasource = new NetworkTareaDatasource(
                mockRestApi, mockTareaCache);
    }

    @Test
    public  void testObtenerTareaDesdeRed() throws  Exception{
        this.networkTareaDatasource.listarTareas();
        verify(mockRestApi).listarTareas();
    }

    @Test
    public void testCrearTarea() throws  Exception{
        TareaEntity tareaEntity = new TareaEntity();

        networkTareaDatasource.guardarTarea(tareaEntity);
        verify(mockRestApi).guardarTarea(tareaEntity);
    }



}