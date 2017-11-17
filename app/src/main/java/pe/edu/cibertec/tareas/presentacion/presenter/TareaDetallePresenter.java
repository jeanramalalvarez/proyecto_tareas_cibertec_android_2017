package pe.edu.cibertec.tareas.presentacion.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import pe.edu.cibertec.tareas.datos.entity.mapper.TareaEntityDataMapper;
import pe.edu.cibertec.tareas.datos.repository.TareaDataRepositorio;
import pe.edu.cibertec.tareas.datos.repository.datasource.TareaDatasourceFactory;
import pe.edu.cibertec.tareas.dominio.executor.JobExecutor;
import pe.edu.cibertec.tareas.dominio.executor.UIThread;
import pe.edu.cibertec.tareas.dominio.model.Tarea;
import pe.edu.cibertec.tareas.dominio.repository.TareaRepositorio;
import pe.edu.cibertec.tareas.dominio.usercase.EliminarTarea;
import pe.edu.cibertec.tareas.dominio.usercase.GuardarTarea;
import pe.edu.cibertec.tareas.dominio.usercase.ModificarTarea;
import pe.edu.cibertec.tareas.dominio.usercase.UseCase;
import pe.edu.cibertec.tareas.presentacion.model.TareaModel;
import pe.edu.cibertec.tareas.presentacion.model.mapper.TareaModelDataMapper;
import pe.edu.cibertec.tareas.presentacion.view.view.TareaDetalleView;

public class TareaDetallePresenter extends BasePresenter<TareaDetalleView> {

    private static final String TAG = "TareaDetallePresenter";

    private final GuardarTarea guardarTarea;
    private final ModificarTarea modificarTarea;
    private final EliminarTarea eliminarTarea;
    private final TareaModelDataMapper tareaModelDataMapper;

    public TareaDetallePresenter(TareaDetalleView view) {
        super(view);

        this.tareaModelDataMapper = new TareaModelDataMapper();

        TareaRepositorio noticiaRepositorio = new TareaDataRepositorio(
                new TareaDatasourceFactory(view.context()),
                new TareaEntityDataMapper()
        );

        this.guardarTarea = new GuardarTarea(
                new JobExecutor(),
                new UIThread(),
                noticiaRepositorio
        );

        this.modificarTarea = new ModificarTarea(
                new JobExecutor(),
                new UIThread(),
                noticiaRepositorio
        );

        this.eliminarTarea = new EliminarTarea(
                new JobExecutor(),
                new UIThread(),
                noticiaRepositorio
        );
    }

    public void guardarTarea(final TareaModel tarea) {
        view.mostrarLoading();

        this.guardarTarea.setParams(tareaModelDataMapper.transformar(tarea));

        this.guardarTarea.ejecutar(new UseCase.Callback<Tarea>(){
            @Override
            public void onSuccess(Tarea response) {
                if(tarea.isAlarma())
                    view.registrarAlarma(tarea);
                else
                    view.eliminarAlarma(tarea);
                view.ocultarLoading();
                view.notificarTareaGuardada();
            }

            @Override
            public void onError(Throwable t) {
                view.ocultarLoading();
                Log.e(TAG, "onError: ", t);
            }
        });
    }

    public void modificarTarea(final TareaModel tarea) {
        view.mostrarLoading();

        this.modificarTarea.setParams(tareaModelDataMapper.transformar(tarea));

        this.modificarTarea.ejecutar(new UseCase.Callback<Tarea>(){
            @Override
            public void onSuccess(Tarea response) {
                if(tarea.isAlarma())
                    view.registrarAlarma(tarea);
                else
                    view.eliminarAlarma(tarea);
                view.ocultarLoading();
                view.notificarTareaModificada();
            }

            @Override
            public void onError(Throwable t) {
                view.ocultarLoading();
                Log.e(TAG, "onError: ", t);
            }
        });
    }

    public void eliminarTarea(final TareaModel tarea) {
        view.mostrarLoading();

        this.eliminarTarea.setParams(tareaModelDataMapper.transformar(tarea));

        this.eliminarTarea.ejecutar(new UseCase.Callback<Tarea>(){
            @Override
            public void onSuccess(Tarea response) {
                view.eliminarAlarma(tarea);
                view.ocultarLoading();
                view.notificarTareaEliminada();
            }

            @Override
            public void onError(Throwable t) {
                view.ocultarLoading();
                Log.e(TAG, "onError: ", t);
            }
        });
    }

}
