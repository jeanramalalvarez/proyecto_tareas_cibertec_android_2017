package pe.edu.cibertec.tareas.datos.network;

import java.util.List;

import pe.edu.cibertec.tareas.datos.entity.TareaEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("data/Tareas")
    Call<List<TareaEntity>> listarTareas();

    @POST("data/Tareas")
    Call<TareaEntity> guardarTarea(@Body TareaEntity tareaEntity);

    @PUT("data/Tareas/{id}")
    Call<TareaEntity> modificarTarea(@Path("id") String id, @Body TareaEntity tareaEntity);

    @DELETE("data/Tareas/{objectId}")
    Call<TareaEntity> eliminarTarea(@Path("objectId") String id);
}
