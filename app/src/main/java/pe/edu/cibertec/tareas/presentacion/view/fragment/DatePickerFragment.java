package pe.edu.cibertec.tareas.presentacion.view.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.telecom.Call;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    public static DatePickerFragment newInstance(String fecha){
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putString("fecha",fecha);
        datePickerFragment.setArguments(args);
        return datePickerFragment;
    }

    public void setOnDateSetListener(DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.onDateSetListener = onDateSetListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        int year, month,day;
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        if(getArguments()!=null){
            String fecha = getArguments().getString("fecha");
            if(fecha!=null && !fecha.isEmpty()){
                String[] fechaArray = fecha.split("/");
                day = Integer.valueOf(fechaArray[0]);
                month = Integer.valueOf(fechaArray[1]) - 1;
                year = Integer.valueOf(fechaArray[2]);
            }
        }
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);
    }
}