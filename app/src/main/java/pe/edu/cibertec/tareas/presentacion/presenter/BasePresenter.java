package pe.edu.cibertec.tareas.presentacion.presenter;

import pe.edu.cibertec.tareas.presentacion.view.view.BaseView;

public class BasePresenter<V extends BaseView> {

    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }
}
