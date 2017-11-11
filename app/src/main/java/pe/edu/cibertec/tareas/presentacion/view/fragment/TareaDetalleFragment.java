package pe.edu.cibertec.tareas.presentacion.view.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pe.edu.cibertec.tareas.R;
import pe.edu.cibertec.tareas.presentacion.model.TareaModel;
import pe.edu.cibertec.tareas.presentacion.presenter.TareaDetallePresenter;
import pe.edu.cibertec.tareas.presentacion.view.view.TareaDetalleView;

public class TareaDetalleFragment extends Fragment implements TareaDetalleView{

    private static final String ARG_TAREA = "fragment.TareaDetalleFragment.ARG_TAREA";

    @BindView(R.id.edt_titulo)
    EditText edtTitulo;

    @BindView(R.id.edt_tarea)
    EditText edtTarea;

    @BindView(R.id.edt_fecha)
    EditText edtFecha;

    @BindView(R.id.edt_hora)
    EditText edtHora;

    @BindView(R.id.tgl_alarma)
    ToggleButton tglAlarma;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.btn_guardar)
    Button btnGuardar;

    @BindView(R.id.btn_eliminar)
    Button btnEliminar;

    private Unbinder unbinder;

    private TareaModel tarea;

    private TareaDetallePresenter tareaDetallePresenter;

    public static TareaDetalleFragment newInstance(TareaModel tarea) {
        TareaDetalleFragment f = new TareaDetalleFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TAREA, tarea);
        f.setArguments(args);
        return f;
    }

    public TareaDetalleFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            tarea = getArguments().getParcelable(ARG_TAREA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tarea_detalle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initUI();

        tareaDetallePresenter = new TareaDetallePresenter(this);
    }

    private void initUI() {
        getActivity().setTitle("Nueva tarea");
        if (tarea != null) {
            edtTitulo.setText(tarea.getTitulo());
            edtTarea.setText(tarea.getTarea());

            if(tarea.getFechaHora() != null){
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String fecha = sdf.format(calendar.getTime());
                Log.i("initUI(","fecha: " + fecha);
                calendar.setTime(tarea.getFechaHora());
                fecha = sdf.format(calendar.getTime());
                Log.i("initUI(","fecha: " + fecha);

                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH) + 1;
                int anio = calendar.get(Calendar.YEAR);

                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minuto = calendar.get(Calendar.MINUTE);

                edtFecha.setText(dia + "/" + mes + "/" + anio);
                edtHora.setText(hora + ":" + minuto);
            }

            tglAlarma.setChecked(tarea.isAlarma());

            if(tarea.getId() != null){
                btnEliminar.setVisibility(Button.VISIBLE);
                getActivity().setTitle("Editar tarea");
            }
        }else{
            edtTitulo.setText("");
            edtTarea.setText("");
        }
    }

    public void setTarea(TareaModel tarea) {
        this.tarea = tarea;
        initUI();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void mostrarLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void guardarTarea(TareaModel tarea) {
        tareaDetallePresenter.guardarTarea(tarea);
    }

    @Override
    public void modificarTarea(TareaModel tarea) {
        tareaDetallePresenter.modificarTarea(tarea);
    }

    @Override
    public void eliminarTarea(TareaModel tarea) {
        tareaDetallePresenter.eliminarTarea(tarea);
    }

    @Override
    public void notificarTareaGuardada() {
        getActivity().finish();
    }

    @Override
    public void notificarTareaModificada() {
        getActivity().finish();
    }

    @Override
    public void notificarTareaEliminada() {
        getActivity().finish();
    }

    @OnClick(R.id.btn_guardar)
    public void onGuardarClick() {
        if (tarea == null){
            TareaModel tarea = new TareaModel();
        }
        tarea.setTitulo(edtTitulo.getText().toString());
        tarea.setTarea(edtTarea.getText().toString());

        Calendar calendar = Calendar.getInstance();

        if(edtFecha.getText() != null){

            String[] fechaArray = edtFecha.getText().toString().split("/");
            int dia = Integer.valueOf(fechaArray[0]);
            int mes = Integer.valueOf(fechaArray[1])-1;
            int anio = Integer.valueOf(fechaArray[2]);
            calendar.set(Calendar.DAY_OF_MONTH, dia);
            calendar.set(Calendar.MONTH, mes);
            calendar.set(Calendar.YEAR, anio);
        }

        if(edtHora.getText() != null){
            String[] horaArray = edtHora.getText().toString().split(":");
            int hora = Integer.valueOf(horaArray[0]);
            int minuto = Integer.valueOf(horaArray[1]);
            calendar.set(Calendar.HOUR_OF_DAY, hora);
            calendar.set(Calendar.MINUTE, minuto);
        }

        if(edtFecha.getText() != null && edtHora.getText() != null){
            tarea.setFechaHora(calendar.getTime());
        }

        tarea.setAlarma(tglAlarma.isChecked());
        //tarea.setTitulo(edtTitulo.getText().toString());
        //tarea.setTitulo(edtTitulo.getText().toString());
        if(tarea.getId() == null){
            guardarTarea(tarea);
        }else{
            modificarTarea(tarea);
        }
    }

    @OnClick(R.id.btn_eliminar)
    public void onEliminarClick() {
        eliminarTarea(tarea);
    }

    @OnClick(R.id.btn_date)
    public void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                edtFecha.setText(day+"/"+(month+1)+"/"+year);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @OnClick(R.id.btn_time)
    public void showTimePickerDialog() {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                edtHora.setText(hourOfDay+":"+minute);
            }
        });
        newFragment.show(getFragmentManager(), "timePicker");
    }

}