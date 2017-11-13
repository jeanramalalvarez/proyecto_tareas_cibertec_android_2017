package pe.edu.cibertec.tareas.datos.entity;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TareaEntity extends RealmObject {

    @PrimaryKey
    @SerializedName("objectId")
    private String id;

    private String titulo;

    private String tarea;

    @SerializedName("fecha_hora")
    @JsonAdapter(DateTypeAdapter.class)
    private Date fechaHora;

    private boolean alarma;

    public TareaEntity() {
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
}
