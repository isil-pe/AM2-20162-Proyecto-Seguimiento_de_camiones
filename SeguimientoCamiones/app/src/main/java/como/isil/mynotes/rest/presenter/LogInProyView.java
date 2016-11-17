package como.isil.mynotes.rest.presenter;

import android.content.Context;

/**
 * Created by Alumno-J on 09/11/2016.
 */
public interface LogInProyView {

    void showLoading();
    void hideLoading();
    Context getContext();

    void onMessageError(String message);

    void successLogin(String message);
    void gotoWelcome();
}
