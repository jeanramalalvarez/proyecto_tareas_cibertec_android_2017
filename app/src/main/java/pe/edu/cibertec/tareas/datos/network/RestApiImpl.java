package pe.edu.cibertec.tareas.datos.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

import pe.edu.cibertec.tareas.datos.entity.TareaEntity;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiImpl implements RestApi {

    private final ApiService apiService;
    private final Context context;

    public RestApiImpl(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.backendless.com/F56ADB4A-F904-9A41-FF25-5C0C3B8DE600/96A1B1D0-C060-68E5-FF95-035CD4246000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiService = retrofit.create(ApiService.class);
        this.context = context;
    }

    @Override
    public List<TareaEntity> listarTareas() throws Exception {
        if (hayInternet()) {
            Call<List<TareaEntity>> call = apiService.listarTareas();
            Response<List<TareaEntity>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    @Override
    public TareaEntity guardarTarea(TareaEntity tareaEntity) throws Exception {
        if (hayInternet()) {
            Call<TareaEntity> call = apiService.guardarTarea(tareaEntity);
            Response<TareaEntity> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    @Override
    public TareaEntity modificarTarea(TareaEntity tareaEntity) throws Exception {
        if (hayInternet()) {
            Call<TareaEntity> call = apiService.modificarTarea(tareaEntity.getId(), tareaEntity);
            Response<TareaEntity> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    @Override
    public TareaEntity eliminarTarea(TareaEntity tareaEntity) throws Exception {
        if (hayInternet()) {
            Call<TareaEntity> call = apiService.eliminarTarea(tareaEntity.getId());
            Response<TareaEntity> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    public boolean hayInternet() {
        boolean hayInternet;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        hayInternet = (networkInfo != null & networkInfo.isConnectedOrConnecting());
        return hayInternet;
    }
}
