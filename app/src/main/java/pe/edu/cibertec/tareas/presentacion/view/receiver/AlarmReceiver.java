package pe.edu.cibertec.tareas.presentacion.view.receiver;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import pe.edu.cibertec.tareas.presentacion.model.TareaModel;

public class AlarmReceiver extends BroadcastReceiver {

    private Context context;
    private TareaModel tarea;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.tarea = intent.getParcelableExtra("tarea");
        if(this.tarea!=null){
            mostrarNotificacion(crearNotificacion(),tarea.getAlarmCode());
        }
    }

    private void mostrarNotificacion(Notification notification, int id){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(id,notification);
    }

    private Notification crearNotificacion(){
        // TODO: terminar de implementar la notificacion

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"basic");

        return null;
    }
}
