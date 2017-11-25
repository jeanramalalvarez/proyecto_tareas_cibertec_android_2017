package pe.edu.cibertec.tareas.dominio.usercase;

import android.support.transition.Transition;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pe.edu.cibertec.tareas.datos.entity.TareaEntity;
import pe.edu.cibertec.tareas.dominio.executor.PostExecutionThread;
import pe.edu.cibertec.tareas.dominio.executor.ThreadExecutor;
import pe.edu.cibertec.tareas.dominio.model.Tarea;
import pe.edu.cibertec.tareas.dominio.repository.TareaRepositorio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by MI EQUIPO on 25/11/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GuardarTareaTest {

    private GuardarTarea guardarTarea;

    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Mock
    private TareaRepositorio mockTareaRepositorio;

    @Before
    public void setUp() throws Exception {
        guardarTarea = new GuardarTarea(
                mockThreadExecutor, mockPostExecutionThread, mockTareaRepositorio);
    }

    @Test
    public  void testGuardarTarea() throws  Exception{
        Tarea tareaEntity = new Tarea();
        guardarTarea.setParams(tareaEntity);
        guardarTarea.construirUseCase();

        verify(mockTareaRepositorio).guardarTarea(tareaEntity);
        verifyNoMoreInteractions(mockTareaRepositorio);
    }


}