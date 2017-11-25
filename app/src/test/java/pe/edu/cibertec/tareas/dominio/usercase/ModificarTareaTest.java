package pe.edu.cibertec.tareas.dominio.usercase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pe.edu.cibertec.tareas.dominio.executor.PostExecutionThread;
import pe.edu.cibertec.tareas.dominio.executor.ThreadExecutor;
import pe.edu.cibertec.tareas.dominio.model.Tarea;
import pe.edu.cibertec.tareas.dominio.repository.TareaRepositorio;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by MI EQUIPO on 25/11/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ModificarTareaTest {

    private ModificarTarea modificarTarea;

    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Mock
    private TareaRepositorio mockTareaRepositorio;

    @Before
    public void setUp() throws Exception {
        modificarTarea = new ModificarTarea(
                mockThreadExecutor, mockPostExecutionThread, mockTareaRepositorio);
    }

    @Test
    public  void testModificarTarea() throws  Exception{
        Tarea tarea = new Tarea();
        modificarTarea.setParams(tarea);
        modificarTarea.construirUseCase();

        verify(mockTareaRepositorio).modificarTarea(tarea);
        verifyNoMoreInteractions(mockTareaRepositorio);
    }


}