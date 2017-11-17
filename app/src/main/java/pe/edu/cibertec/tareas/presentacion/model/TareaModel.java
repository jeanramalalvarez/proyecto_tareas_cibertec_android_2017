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

    private int alarmCode;

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

    public int getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(int alarmCode) {
        this.alarmCode = alarmCode;
    }

    //if(fechaHora!= null)
    //            parcel.writeSerializable(fechaHora.getTime());
    //Long dato = (Long)in.readSerializable();
    //    if(dato != null)
    //fechaHora = new Date(dato);

    public void generarAlarmCode(){
        int result = 1;
        result += titulo.hashCode();
        result += tarea.hashCode();
        result += fechaHora.hashCode();
        result += (alarma?1:0);
        alarmCode = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.titulo);
        dest.writeString(this.tarea);
        dest.writeLong(this.fechaHora != null ? this.fechaHora.getTime() : -1);
        dest.writeByte(this.alarma ? (byte) 1 : (byte) 0);
        dest.writeInt(this.alarmCode);
    }

    protected TareaModel(Parcel in) {
        this.id = in.readString();
        this.titulo = in.readString();
        this.tarea = in.readString();
        long tmpFechaHora = in.readLong();
        this.fechaHora = tmpFechaHora == -1 ? null : new Date(tmpFechaHora);
        this.alarma = in.readByte() != 0;
        this.alarmCode = in.readInt();
    }

    public static final Creator<TareaModel> CREATOR = new Creator<TareaModel>() {
        @Override
        public TareaModel createFromParcel(Parcel source) {
            return new TareaModel(source);
        }

        @Override
        public TareaModel[] newArray(int size) {
            return new TareaModel[size];
        }
    };
}
