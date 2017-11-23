package pe.edu.cibertec.tareas.datos.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pe.edu.cibertec.tareas.datos.entity.mapper.TareaEntityDataMapper;
import pe.edu.cibertec.tareas.datos.repository.datasource.TareaDatasource;
import pe.edu.cibertec.tareas.datos.repository.datasource.TareaDatasourceFactory;
import pe.edu.cibertec.tareas.dominio.model.Tarea;
import pe.edu.cibertec.tareas.dominio.repository.TareaRepositorio;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by MI EQUIPO on 23/11/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TareaDataRepositorioTest {

    private TareaRepositorio tareaRepositorio;

    @Mock
    private TareaDatasourceFactory mockTareaDatasourceFactory;

    @Mock
    private TareaEntityDataMapper mockTareaEntityDataMapper;

    @Mock
    private TareaDatasource mockTareaDatasource;

    @Before
    public void setUp() throws Exception {
        this.tareaRepositorio = new TareaDataRepositorio(
                mockTareaDatasourceFactory, mockTareaEntityDataMapper);

        given(mockTareaDatasourceFactory.crear(true)).willReturn(mockTareaDatasource);
    }

    @Test
    public void testListarTareas() throws Exception {
        tareaRepositorio.listarTareas(true);
        verify(mockTareaDatasourceFactory).crear(true);
    }


}