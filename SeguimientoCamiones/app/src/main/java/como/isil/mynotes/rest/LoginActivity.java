package como.isil.mynotes.rest;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.isil.mynotes.rest.R;

import como.isil.mynotes.rest.presenter.LogInProyPresenter;
import como.isil.mynotes.rest.presenter.LogInProyView;
import como.isil.mynotes.rest.presenter.LogInView;
import como.isil.mynotes.rest.storage.PreferencesHelper;


public class LoginActivity extends ActionBarActivity implements LogInProyView {

    private Button btnLogin;
    private EditText eteUsername;
    private EditText etePassword;
    private String username;
    private String password;
    private View rlayLoading,container;

    private LogInProyPresenter logInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logInPresenter= new LogInProyPresenter();
        logInPresenter.attachedView(this);
        init();
    }

    private void init() {
        eteUsername=(EditText)findViewById(R.id.eteUsername);
        etePassword=(EditText)findViewById(R.id.etePassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        rlayLoading=findViewById(R.id.rlayLoading);
        container=findViewById(R.id.container);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    //gotoMain();
                    logInPresenter.logIn(username,password);
                }
            }
        });


        etePassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(event!=null){
                    Log.v("CONSOLE ","keycode "+event.getKeyCode()+ " actionId "+actionId);
                }

                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (validateForm()) {
                        logInPresenter.logIn(username,password);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void gotoWelcome() {
        savePreferences();
        Intent intent= new Intent(this,WelcomeActivity.class);
        startActivity(intent);
    }

    private void savePreferences() {
        PreferencesHelper.saveSession(this,username,password);
    }

    private boolean validateForm() {
        username= eteUsername.getText().toString();
        password= etePassword.getText().toString();

        if(username.isEmpty())
        {
            eteUsername.setError("Error campo username");
            return false;
        }
        if(password.isEmpty())
        {
            etePassword.setError("Error campo password");
            return false;
        }
        return true;
    }


    @Override
    public void showLoading() {
        this.rlayLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.rlayLoading.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onMessageError(String message) {
        Snackbar snackbar = Snackbar
                .make(container,message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void successLogin(String message) {
        Snackbar snackbar = Snackbar
                .make(container,message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
