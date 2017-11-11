package pe.edu.cibertec.tareas.presentacion.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class TareaModel implements Parcelable {

    private String id;

    private String titulo;

    private String tarea;

    private Date fechaHora;

    private boolean alarma;

    public TareaModel() {
    }

    @Override
    public String toString() {
        return titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isAlarma() {
        return alarma;
    }

    public void setAlarma(boolean alarma) {
        this.alarma = alarma;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(titulo);
        parcel.writeString(tarea);
        if(fechaHora!= null)
            parcel.writeSerializable(fechaHora.getTime());
        parcel.writeByte((byte) (alarma ? 1 : 0));
    }

    protected TareaModel(Parcel in) {
        id = in.readString();
        titulo = in.readString();
        tarea = in.readString();
        //fechaHora = new Date(in.readLong());
        Long dato = (Long)in.readSerializable();
        if(dato != null)
            fechaHora = new Date(dato);
        alarma = in.readByte() != 0;
    }

    public static final Creator<TareaModel> CREATOR = new Creator<TareaModel>() {
        @Override
        public TareaModel createFromParcel(Parcel in) {
            return new TareaModel(in);
        }

        @Override
        public TareaModel[] newArray(int size) {
            return new TareaModel[size];
        }
    };


}
