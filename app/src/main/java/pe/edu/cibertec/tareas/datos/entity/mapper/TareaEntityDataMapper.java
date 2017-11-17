package pe.edu.cibertec.tareas.datos.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import pe.edu.cibertec.tareas.datos.entity.TareaEntity;
import pe.edu.cibertec.tareas.dominio.model.Tarea;

public class TareaEntityDataMapper {

    public Tarea transformar(TareaEntity tarea) {
        Tarea tareaModel = new Tarea();
        tareaModel.setId(tarea.getId());
        tareaModel.setTitulo(tarea.getTitulo());
        tareaModel.setTarea(tarea.getTarea());
        tareaModel.setAlarma(tarea.isAlarma());
        tareaModel.setFechaHora(tarea.getFechaHora());
        tareaModel.setAlarmCode(tarea.getAlarmCode());
        return tareaModel;
    }

    public TareaEntity transformar(Tarea tarea) {
        TareaEntity tareaModel = new TareaEntity();
        tareaModel.setId(tarea.getId());
        tareaModel.setTitulo(tarea.getTitulo());
        tareaModel.setTarea(tarea.getTarea());
        tareaModel.setAlarma(tarea.isAlarma());
        tareaModel.setFechaHora(tarea.getFechaHora());
        tareaModel.setAlarmCode(tarea.getAlarmCode());
        return tareaModel;
    }

    public List<Tarea> transformar(List<TareaEntity> noticiaList) {
        List<Tarea> noticiaModelList = new ArrayList<>();
        for (TareaEntity noticia : noticiaList) {
            Tarea noticiaModel = transformar(noticia);
            noticiaModelList.add(noticiaModel);
        }
        return noticiaModelList;
    }
}
