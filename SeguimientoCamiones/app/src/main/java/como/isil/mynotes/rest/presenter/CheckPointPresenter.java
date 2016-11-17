package como.isil.mynotes.rest.presenter;

import android.util.Log;

import java.util.List;

import como.isil.mynotes.rest.entity.CheckPointEntity;
import como.isil.mynotes.rest.storage.entity.CheckPointsResponse;
import como.isil.mynotes.rest.storage.request.ApiProy;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno-J on 16/11/2016.
 */
public class CheckPointPresenter {

    private static final String TAG = "CheckpointsPresenter";
    private final String ERROR_MESSAGE= "Ocurrió un error";
    private CheckpointsView checkpointsView;

    public   void attachedView(CheckpointsView checkpointsView){
        this.checkpointsView = checkpointsView;
    }

    public  void detachView(){
        this.checkpointsView=null;
    }

    public void loadCheckpoints(){
        Call<CheckPointsResponse> call= ApiProy.getMyApiClient().checkpoints();
        call.enqueue(new Callback<CheckPointsResponse>() {
            @Override
            public void onResponse(Call<CheckPointsResponse> call, Response<CheckPointsResponse> response) {
                if(response.isSuccessful()){

                    checkpointsSuccess(response.body());
                }else {
                    checkpointsError(ERROR_MESSAGE);
                }
            }

            @Override
            public void onFailure(Call<CheckPointsResponse> call, Throwable t) {
                String json="Error ";
                try {
                    json= new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v(TAG, "json >>>> " + json);

                checkpointsError(json);
            }
        });
    }

    private void checkpointsSuccess(CheckPointsResponse checkPointsResponse) {

        if(checkPointsResponse!=null){
            List<CheckPointEntity> checkpoints= checkPointsResponse.getData();
            checkpointsView.renderCheckpoints(checkpoints);
        }

    }
    private void checkpointsError(String messageError){
        checkpointsView.onMessageError(messageError);
    }

}
