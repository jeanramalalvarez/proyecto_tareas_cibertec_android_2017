package pe.edu.cibertec.tareas.dominio.usercase;

import pe.edu.cibertec.tareas.dominio.executor.PostExecutionThread;
import pe.edu.cibertec.tareas.dominio.executor.ThreadExecutor;

public abstract class UseCase<T> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private Callback<T> callback;

    protected UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected void notificarUseCaseSatisfactorio(final T response) {
        postExecutionThread.execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(response);
                }
            }
        });
    }

    protected void notificarUseCaseError(final Throwable t) {
        postExecutionThread.execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError(t);
                }
            }
        });
    }

    public void ejecutar(final Callback<T> callback) {
        this.callback = callback;
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                construirUseCase();
            }
        });
    }

    protected abstract void construirUseCase();

    public interface Callback<T> {
        void onSuccess(T response);
        void onError(Throwable t);
    }
}
