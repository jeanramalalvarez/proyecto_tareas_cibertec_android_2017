package pe.edu.cibertec.tareas.presentacion.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import pe.edu.cibertec.tareas.presentacion.model.TareaModel;
import pe.edu.cibertec.tareas.presentacion.view.fragment.TareaDetalleFragment;

public class TareaDetalleActivity extends AppCompatActivity {

    public static final String EXTRA_TAREA = "activity.TareaDetalleActivity.EXTRA_TAREA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TareaModel tarea = getIntent().getParcelableExtra(EXTRA_TAREA);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(android.R.id.content, TareaDetalleFragment.newInstance(tarea));
        ft.commit();
    }
}
