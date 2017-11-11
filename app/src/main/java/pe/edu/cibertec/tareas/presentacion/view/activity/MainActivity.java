package pe.edu.cibertec.tareas.presentacion.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.edu.cibertec.tareas.R;
import pe.edu.cibertec.tareas.presentacion.model.TareaModel;
import pe.edu.cibertec.tareas.presentacion.view.fragment.TareaDetalleFragment;
import pe.edu.cibertec.tareas.presentacion.view.fragment.TareasFragment;

public class MainActivity extends AppCompatActivity implements TareasFragment.OnTareaClickListener {

    private boolean isDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment tareaDetalleFragment = getSupportFragmentManager().findFragmentById(R.id.frag_detalle);

        isDualPane = tareaDetalleFragment != null;
    }

    @Override
    public void onTareaClick(TareaModel tarea) {
        if (!isDualPane) {
            // Si es telefono
            Intent intent = new Intent(this, TareaDetalleActivity.class);
            intent.putExtra(TareaDetalleActivity.EXTRA_TAREA, tarea);
            startActivity(intent);

        } else {
            // Si es tablet
            TareaDetalleFragment tareaDetalleFragment = (TareaDetalleFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.frag_detalle);
            tareaDetalleFragment.setTarea(tarea);
        }
    }

    @Override
    public void onAgregarTareaClick() {
        if (!isDualPane) {
            // Si es telefono
            Intent intent = new Intent(this, TareaDetalleActivity.class);
            intent.putExtra(TareaDetalleActivity.EXTRA_TAREA, new TareaModel());
            startActivity(intent);

        } else {
            // Si es tablet
            TareaDetalleFragment tareaDetalleFragment = (TareaDetalleFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.frag_detalle);
            tareaDetalleFragment.setTarea(null);
        }
    }
}
