package como.isil.mynotes.rest.presenter;

import android.content.Context;

import java.util.List;

import como.isil.mynotes.rest.entity.CheckPointEntity;

/**
 * Created by Alumno-J on 16/11/2016.
 */
public interface CheckpointsView {
    //void showLoading();
    //void hideLoading();
    Context getContext();

    void onMessageError(String message);
    void renderCheckpoints(List<CheckPointEntity> checkpoints);
}
