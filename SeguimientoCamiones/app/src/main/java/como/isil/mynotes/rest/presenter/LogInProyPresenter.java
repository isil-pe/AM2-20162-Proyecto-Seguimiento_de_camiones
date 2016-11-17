package como.isil.mynotes.rest.presenter;

import android.util.Log;

import org.json.JSONObject;

import como.isil.mynotes.rest.entity.UserEntity;
import como.isil.mynotes.rest.entity.UsuarioEntity;
import como.isil.mynotes.rest.storage.entity.LogInProyRaw;
import como.isil.mynotes.rest.storage.entity.LogInProyResponse;
import como.isil.mynotes.rest.storage.entity.LogInRaw;
import como.isil.mynotes.rest.storage.entity.LogInResponse;
import como.isil.mynotes.rest.storage.request.ApiProy;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno-J on 09/11/2016.
 */
public class LogInProyPresenter {


    private static final String TAG = "LogInPresenter";
    private final String ERROR_MESSAGE= "Error: ";
    private LogInProyView logInView;
    private String email;
    private String password;

    public   void attachedView(LogInProyView logInView){
        this.logInView = logInView;
    }

    public  void detachView(){
        this.logInView=null;
    }

    public void logIn(String email,String password) {
        this.email = email;
        this.password = password;
        LogInProyRaw logInRaw= new LogInProyRaw();
        logInRaw.setLogin(this.email);
        logInRaw.setPassword(this.password);

        logInView.showLoading();

        Call<LogInProyResponse> call = ApiProy.getMyApiClient().login(logInRaw);
        call.enqueue(new Callback<LogInProyResponse>() {
            @Override
            public void onResponse(Call<LogInProyResponse> call, Response<LogInProyResponse> response) {
                if(response.isSuccessful()){

                    loginSuccess(response.body());
                }else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        loginError( ERROR_MESSAGE + jObjError.getString("message"));
                    } catch (Exception e) {
                        loginError(e.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<LogInProyResponse> call, Throwable t) {
                String json="Error ";
                try {
                    json= new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v(TAG, "json >>>> " + json);

                loginError(json);
            }
        });

    }
    public void loginSuccess(LogInProyResponse loginResponse){
        if(loginResponse!=null){
            UsuarioEntity userEntity= new UsuarioEntity();
            userEntity.setEmail(loginResponse.getEmail());
            userEntity.setNombre(loginResponse.getName());
            userEntity.setApellido(loginResponse.getLastname());
            userEntity.setDNI(loginResponse.getDni());
            userEntity.setVehiculo_id(loginResponse.getVehiculo_id());
            userEntity.setObjectId(loginResponse.getObjectId());
            userEntity.setToken(loginResponse.getToken());
        }
        logInView.hideLoading();
        logInView.successLogin("Logeado correctamente.");
        logInView.gotoWelcome();
    }
    public void loginError(String messageError){
        logInView.hideLoading();
        logInView.onMessageError(messageError);
    }
}
