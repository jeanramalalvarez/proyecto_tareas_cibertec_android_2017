package pe.edu.cibertec.tareas.dominio.usercase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import pe.edu.cibertec.tareas.dominio.executor.PostExecutionThread;
import pe.edu.cibertec.tareas.dominio.executor.ThreadExecutor;
import pe.edu.cibertec.tareas.dominio.repository.TareaRepositorio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by MI EQUIPO on 25/11/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListarTareasTest {

    private ListarTareas listarTareas;

    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Mock
    private TareaRepositorio mockTareaRepositorio;

    @Before
    public void setUp() throws Exception {
        listarTareas = new ListarTareas(
                mockThreadExecutor, mockPostExecutionThread, mockTareaRepositorio);
    }

    @Test
    public void testListasTareasUseCase() throws Exception {
        listarTareas.setForzarred(true);
        listarTareas.construirUseCase();

        verify(mockTareaRepositorio).listarTareas(true);
        verifyNoMoreInteractions(mockTareaRepositorio);
    }

}