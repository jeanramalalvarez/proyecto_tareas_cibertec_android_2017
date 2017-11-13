package pe.edu.cibertec.tareas.dominio.usercase;

import java.util.ArrayList;
import java.util.List;

import pe.edu.cibertec.tareas.dominio.executor.PostExecutionThread;
import pe.edu.cibertec.tareas.dominio.executor.ThreadExecutor;
import pe.edu.cibertec.tareas.dominio.model.Tarea;
import pe.edu.cibertec.tareas.dominio.repository.TareaRepositorio;

public class ListarTareas extends UseCase<List<Tarea>> {

    private final TareaRepositorio tareaRepositorio;

    private  boolean forzarred;

    public ListarTareas(ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread,
                        TareaRepositorio tareaRepositorio) {
        super(threadExecutor, postExecutionThread);
        this.tareaRepositorio = tareaRepositorio;
    }

    public  void setForzarred(boolean forzarred){this.forzarred = forzarred;}

    @Override
    protected void construirUseCase() {
        try {
            List<Tarea> tareaList = this.tareaRepositorio.listarTareas(forzarred);
            notificarUseCaseSatisfactorio(tareaList);
        } catch (Exception e) {
            notificarUseCaseError(e);
        }
    }
}
