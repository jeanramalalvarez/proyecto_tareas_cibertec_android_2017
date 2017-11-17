package pe.edu.cibertec.tareas.presentacion.model.mapper;

import java.util.ArrayList;
import java.util.List;

import pe.edu.cibertec.tareas.dominio.model.Tarea;
import pe.edu.cibertec.tareas.presentacion.model.TareaModel;

public class TareaModelDataMapper {

    public TareaModel transformar(Tarea tarea) {
        TareaModel tareaModel = new TareaModel();
        tareaModel.setId(tarea.getId());
        tareaModel.setTitulo(tarea.getTitulo());
        tareaModel.setTarea(tarea.getTarea());
        tareaModel.setAlarma(tarea.isAlarma());
        tareaModel.setFechaHora(tarea.getFechaHora());
        tareaModel.setAlarmCode(tarea.getAlarmCode());
        return tareaModel;
    }

    public Tarea transformar(TareaModel tarea) {
        Tarea tareaModel = new Tarea();
        tareaModel.setId(tarea.getId());
        tareaModel.setTitulo(tarea.getTitulo());
        tareaModel.setTarea(tarea.getTarea());
        tareaModel.setAlarma(tarea.isAlarma());
        tareaModel.setFechaHora(tarea.getFechaHora());
        tareaModel.setAlarmCode(tarea.getAlarmCode());
        return tareaModel;
    }

    public List<TareaModel> transformar(List<Tarea> noticiaList) {
        List<TareaModel> noticiaModelList = new ArrayList<>();
        for (Tarea noticia : noticiaList) {
            TareaModel noticiaModel = transformar(noticia);
            noticiaModelList.add(noticiaModel);
        }
        return noticiaModelList;
    }
}
