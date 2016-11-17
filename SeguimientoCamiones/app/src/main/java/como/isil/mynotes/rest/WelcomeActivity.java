package como.isil.mynotes.rest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.isil.mynotes.rest.R;

import como.isil.mynotes.rest.storage.PreferencesHelper;
import como.isil.mynotes.rest.utils.CapitalizeString;

public class WelcomeActivity extends AppCompatActivity {

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
}
