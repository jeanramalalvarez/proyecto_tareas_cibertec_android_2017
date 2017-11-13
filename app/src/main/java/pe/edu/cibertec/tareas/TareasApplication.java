package pe.edu.cibertec.tareas;

import android.app.Application;

import io.realm.Realm;

public class TareasApplication   extends Application{

    @Override
    public void onCreate() {

        super.onCreate();
        Realm.init(this);

    }
}
