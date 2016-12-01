package como.isil.mynotes.rest.presenter;

import android.util.Log;

import java.util.Date;
import java.util.List;

import como.isil.mynotes.rest.entity.CheckPointEntity;
import como.isil.mynotes.rest.storage.entity.CheckPointRaw;
import como.isil.mynotes.rest.storage.entity.CheckPointResponse;
import como.isil.mynotes.rest.storage.entity.CheckPointsResponse;
import como.isil.mynotes.rest.storage.entity.NoteResponse;
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

    private AddCheckpointView addCheckpointView;
    private String lat,lon,order;

    public   void attachedView(AddCheckpointView addCheckpointView){
        this.addCheckpointView = addCheckpointView;
    }

    public  void detachView(){
        this.addCheckpointView=null;
    }

    public void addCheckpoint(String lat, String lon, String order ){
        CheckPointRaw checkPointRaw= new CheckPointRaw();
        checkPointRaw.setLatitud(lat);
        checkPointRaw.setLongitud(lon);
        checkPointRaw.setId_order(order);

        //addCheckpointView.showLoading();
        Call<CheckPointResponse> call = ApiProy.getMyApiClient().addCheckPoint(checkPointRaw);
        call.enqueue(new Callback<CheckPointResponse>(){
            @Override
            public void onResponse(Call<CheckPointResponse> call, Response<CheckPointResponse> response) {
                if(response.isSuccessful()){
                    addCheckPointSucess(response.body());
                }else {
                    addCheckPointError(ERROR_MESSAGE);
                }
            }

            @Override
            public void onFailure(Call<CheckPointResponse> call, Throwable t) {
                String json="Error ";
                try {
                    json= new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v(TAG, "json >>>> " + json);

                addCheckPointError(json);
            }
        });

    }
    public void addCheckPointSucess(CheckPointResponse checkPointResponse){

        if(checkPointResponse!=null){
            CheckPointEntity checkPointEntity= new CheckPointEntity();
            checkPointEntity.setObjectId(checkPointResponse.getObjectId());
            checkPointEntity.setLongitud(checkPointResponse.getLongitud());
            checkPointEntity.setLatitud(checkPointResponse.getLatitud());
            checkPointEntity.setId_order(checkPointResponse.getId_order());
        }
        addCheckpointView.hideLoading();
        addCheckpointView.onAddCheckPointSuccess();
    }

    public void addCheckPointError(String messageError){
        addCheckpointView.hideLoading();
        addCheckpointView.onMessageError(messageError);
    }















}
