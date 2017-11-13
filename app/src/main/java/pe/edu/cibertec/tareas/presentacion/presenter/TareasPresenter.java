package pe.edu.cibertec.tareas.presentacion.presenter;

import java.util.List;

import pe.edu.cibertec.tareas.datos.entity.mapper.TareaEntityDataMapper;
import pe.edu.cibertec.tareas.datos.repository.TareaDataRepositorio;
import pe.edu.cibertec.tareas.datos.repository.datasource.TareaDatasourceFactory;
import pe.edu.cibertec.tareas.dominio.executor.JobExecutor;
import pe.edu.cibertec.tareas.dominio.executor.UIThread;
import pe.edu.cibertec.tareas.dominio.model.Tarea;
import pe.edu.cibertec.tareas.dominio.repository.TareaRepositorio;
import pe.edu.cibertec.tareas.dominio.usercase.ListarTareas;
import pe.edu.cibertec.tareas.dominio.usercase.UseCase;
import pe.edu.cibertec.tareas.presentacion.NetworkUtils;
import pe.edu.cibertec.tareas.presentacion.model.mapper.TareaModelDataMapper;
import pe.edu.cibertec.tareas.presentacion.view.view.TareasView;


public class TareasPresenter extends BasePresenter<TareasView> {

    private final ListarTareas listarTareas;
    private final TareaModelDataMapper tareaModelDataMapper;

    public TareasPresenter(TareasView view) {
        super(view);

        this.tareaModelDataMapper = new TareaModelDataMapper();

        TareaRepositorio tareaRepositorio = new TareaDataRepositorio(
                new TareaDatasourceFactory(view.context()),
                new TareaEntityDataMapper()
        );

        this.listarTareas = new ListarTareas(
                new JobExecutor(),
                new UIThread(),
                tareaRepositorio
        );
    }

    public void listarTareas() {
        view.mostrarLoading();

        listarTareas.setForzarred(NetworkUtils.HayInternet(view.context()));

        listarTareas.ejecutar(new UseCase.Callback<List<Tarea>>() {
            @Override
            public void onSuccess(List<Tarea> response) {
                view.listarTareas(tareaModelDataMapper.transformar(response));
                view.ocultarLoading();
            }

            @Override
            public void onError(Throwable t) {
                view.ocultarLoading();
            }
        });

    }
}
