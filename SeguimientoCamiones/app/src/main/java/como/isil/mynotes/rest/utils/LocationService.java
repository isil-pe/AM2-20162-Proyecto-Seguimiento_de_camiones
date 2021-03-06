package como.isil.mynotes.rest.utils;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Carlos Barrenechea on 29/11/2016.
 */

public class LocationService extends IntentService {

    private  static  Timer timer= new Timer();
    private Context context;

    public LocationService() {
        super("LocationService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context= this;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                toastHandler.sendEmptyMessage(0);
            }
        }, 0, 10000);
        return Service.START_STICKY;
    }


    private final Handler toastHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            Toast.makeText(context, "Ubicación enviada", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer.purge();
        }
    }
}
