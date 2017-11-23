package pe.edu.cibertec.tareas.datos.entity.mapper;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import pe.edu.cibertec.tareas.datos.entity.TareaEntity;
import pe.edu.cibertec.tareas.dominio.model.Tarea;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;

import static org.junit.Assert.*;

/**
 * Created by MI EQUIPO on 22/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class TareaEntityDataMapperTest {

    private static final String PRUEBA_TITULO = "Titulo de Prueba";
    private static final String PRUEBA_TAREA = "Tarea de Prueba";
    private static final String PRUEBA_URL = "http://prueba.jpg";

    private TareaEntityDataMapper tareaEntityDataMapper;

    @Before
    public void setUp() throws Exception {
        this.tareaEntityDataMapper = new TareaEntityDataMapper();
    }

    @Test
    public void testTransformarTareaEntity(){
     TareaEntity tareaEntity = crearTareaEntityPrueba();
     Tarea tarea = tareaEntityDataMapper.transformar(tareaEntity);

     assertThat(tarea, is(instanceOf(Tarea.class)));
     assertThat(tarea.getTitulo(), is(PRUEBA_TITULO));
     assertThat(tarea.getTarea(),is(PRUEBA_TAREA));


    }

    @Test
    public void testTransformarTareaEntityList() {
        TareaEntity tareaEntity1 = new TareaEntity();
        TareaEntity tareaEntity2 = new TareaEntity();

        List<TareaEntity> tareaEntityList = new ArrayList<>();
        tareaEntityList.add(tareaEntity1);
        tareaEntityList.add(tareaEntity2);

        List<Tarea> tareaList = tareaEntityDataMapper.transformar(tareaEntityList);

        assertThat(tareaList.get(0), is(instanceOf(Tarea.class)));
        assertThat(tareaList.get(1), is(instanceOf(Tarea.class)));
        assertThat(tareaList.size(), is(2));
    }


    private  TareaEntity  crearTareaEntityPrueba(){
        TareaEntity tareaEntity = new TareaEntity();
        tareaEntity.setTitulo(PRUEBA_TITULO);
        tareaEntity.setTarea(PRUEBA_TAREA);

        return  tareaEntity;
    }


}