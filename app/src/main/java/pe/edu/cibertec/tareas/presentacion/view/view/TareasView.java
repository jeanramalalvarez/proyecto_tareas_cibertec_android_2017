package pe.edu.cibertec.tareas.presentacion.view.view;

import java.util.List;

import pe.edu.cibertec.tareas.presentacion.model.TareaModel;

public interface TareasView extends LoadingView {

    void listarTareas(List<TareaModel> tareaList);

    void verTarea(TareaModel tarea);

    void agregarTarea();
}
