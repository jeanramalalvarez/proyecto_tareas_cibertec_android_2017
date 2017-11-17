package pe.edu.cibertec.tareas.presentacion.view.view;

import pe.edu.cibertec.tareas.presentacion.model.TareaModel;

public interface TareaDetalleView extends LoadingView {

    void guardarTarea(TareaModel tarea);

    void modificarTarea(TareaModel tarea);

    void eliminarTarea(TareaModel tarea);

    void notificarTareaGuardada();

    void notificarTareaModificada();

    void notificarTareaEliminada();

    void registrarAlarma(TareaModel tarea);

    void eliminarAlarma(TareaModel tarea);
}
