package pe.edu.cibertec.tareas.presentacion.view.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import pe.edu.cibertec.tareas.R;
import pe.edu.cibertec.tareas.presentacion.model.TareaModel;
import pe.edu.cibertec.tareas.presentacion.view.activity.TareaDetalleActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {

    private Context context;
    private TareaModel tarea;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Bundle bundle = intent.getBundleExtra("bundle");
        this.tarea = bundle.getParcelable("tarea");
        if(this.tarea!=null){
            Log.d("AlarmReceiver","Se entro aqui");
            mostrarNotificacion(crearNotificacion(),tarea.getAlarmCode());
            Log.d("AlarmReceiver","Se mostro");
        }
        Log.d("AlarmReceiver","Se paso");
    }

    private void mostrarNotificacion(Notification notification, int id){
        //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("basic", "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(id,notification);
    }

    private Notification crearNotificacion(){

        Intent intent = new Intent(context, TareaDetalleActivity.class);
        intent.putExtra(TareaDetalleActivity.EXTRA_TAREA,tarea);
        intent.putExtra(TareaDetalleActivity.EXTRA_ALARMA_DETALLE,true);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addParentStack(TareaDetalleActivity.class);
        taskStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,
                    PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"basic");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle(tarea.getTitulo());
        builder.setContentText(tarea.getTarea());
        builder.setSmallIcon(R.drawable.ic_alarm);
        builder.setAutoCancel(true);
        builder.setColor(Color.BLUE);
        builder.setContentIntent(pendingIntent);
        return builder.build();
    }
}
