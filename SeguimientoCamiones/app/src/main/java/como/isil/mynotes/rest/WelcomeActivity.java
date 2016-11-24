package como.isil.mynotes.rest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.isil.mynotes.rest.R;

import como.isil.mynotes.rest.storage.PreferencesHelper;
import como.isil.mynotes.rest.utils.CapitalizeString;
import como.isil.mynotes.rest.view.listeners.OnOrderListener;

public class WelcomeActivity extends AppCompatActivity implements OnOrderListener{

    public static final  int DETAIL_NOTE=101;



    TextView txvWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txvWelcome = (TextView)findViewById(R.id.txvWelcome);
        String username = PreferencesHelper.getUserSession(this);
        if(username!=null)
        {
            txvWelcome.setText("Bienvenido "+ new CapitalizeString(username).first());
        }
    }

    @Override
    public void showParentLoading() {

    }

    @Override
    public void hideParentLoading() {

    }

    @Override
    public void showMessage(String message) {

    }
}
