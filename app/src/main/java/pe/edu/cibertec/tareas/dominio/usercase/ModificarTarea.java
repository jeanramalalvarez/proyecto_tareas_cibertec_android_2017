package pe.edu.cibertec.tareas.dominio.usercase;

import pe.edu.cibertec.tareas.dominio.executor.PostExecutionThread;
import pe.edu.cibertec.tareas.dominio.executor.ThreadExecutor;
import pe.edu.cibertec.tareas.dominio.model.Tarea;
import pe.edu.cibertec.tareas.dominio.repository.TareaRepositorio;

public class ModificarTarea extends UseCase<Tarea> {

    private final TareaRepositorio tareaRepositorio;
    private Tarea tarea;

    public ModificarTarea(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread,
                          TareaRepositorio tareaRepositorio) {
        super(threadExecutor, postExecutionThread);
        this.tareaRepositorio = tareaRepositorio;
    }

    @Override
    protected void construirUseCase() {
        try {
            Tarea noticia = this.tareaRepositorio.modificarTarea(this.tarea);
            notificarUseCaseSatisfactorio(noticia);
        } catch (Exception e) {
            notificarUseCaseError(e);
        }
    }

    public void setParams(Tarea tarea) {
        this.tarea = tarea;
    }
}
