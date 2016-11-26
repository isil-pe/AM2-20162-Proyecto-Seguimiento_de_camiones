package como.isil.mynotes.rest;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.isil.mynotes.rest.R;

/**
 * Created by Marzio on 31/01/2016.
 */
public class MainMap extends Fragment implements MapWrapperLayout.OnDragListener{

    View vista;
    private GoogleMap googleMap;
    private CustomMapFragment mCustomMapFragment;
    private LocationManager locMan;

    private TextView mLocationTextView;

    public static Fragment newInstance(Context context){
        return new MainMap();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.main_map, container, false);
        return vista;
    }


    @Override
    public void onDrag(MotionEvent motionEvent) {

    }


}
