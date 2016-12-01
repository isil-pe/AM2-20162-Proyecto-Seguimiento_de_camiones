package como.isil.mynotes.rest.presenter;

import android.content.Context;

import java.util.Date;
import java.util.List;

import como.isil.mynotes.rest.entity.CheckPointEntity;

/**
 * Created by Alumno-J on 16/11/2016.
 */
public interface CheckpointsView {
    //void showLoading();
    //void hideLoading();
    Context getContext();

    void  sendCheckpoint(String lat, String lon, String order, Date stamp);

    void onMessageError(String message);
    void renderCheckpoints(List<CheckPointEntity> checkpoints);
}
