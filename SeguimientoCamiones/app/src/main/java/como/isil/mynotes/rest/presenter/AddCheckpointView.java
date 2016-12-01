package como.isil.mynotes.rest.presenter;

import android.content.Context;

/**
 * Created by Carlos Barrenechea on 30/11/2016.
 */

public interface AddCheckpointView {

    void showLoading();
    void hideLoading();
    Context getContext();

    void onMessageError(String message);
    void onAddCheckPointSuccess();
}
