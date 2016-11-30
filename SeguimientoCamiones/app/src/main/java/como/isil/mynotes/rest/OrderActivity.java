package como.isil.mynotes.rest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.isil.mynotes.rest.R;

import como.isil.mynotes.rest.entity.OrderEntity;
import como.isil.mynotes.rest.utils.LocationService;
import como.isil.mynotes.rest.view.fragments.OrderDetailFragment;
import como.isil.mynotes.rest.view.fragments.TrackMap;
import como.isil.mynotes.rest.view.listeners.OnOrderListener;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int DETAIL_ORDER = 101;
    private static final String TAG = "OrderActivity";
    private OrderDetailFragment orderDetailsFragment = OrderDetailFragment.newInstance(null, null);
    private int fragmentSelected = DETAIL_ORDER;
    private OrderEntity orderEntity;

    private View btnStart;
    private View btnStop;
    private View rlayLoading;

    private GoogleMap googleMap;
    private LocationManager locMan;
    double lat, lng;
    private CustomMapFragment mCustomMapFragment;

    String origen_latitud, origen_longitud, destino_latitud, destino_longitud, id_orden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        origen_latitud = getIntent().getStringExtra("origen_latitud");
        origen_longitud = getIntent().getStringExtra("origen_longitud");
        destino_latitud = getIntent().getStringExtra("destino_latitud");
        destino_longitud = getIntent().getStringExtra("destino_longitud");
        id_orden = getIntent().getStringExtra("id_orden");
        initializeUI();

        btnStart= findViewById(R.id.btnStart);
        btnStop= findViewById(R.id.btnStop);

        btnStart.setOnClickListener(this);
    }

    private void initializeUI() {
        try {
            initilizeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initilizeMap() {
        if (googleMap == null) {
            mCustomMapFragment = ((CustomMapFragment) getFragmentManager().findFragmentById(R.id.map));
            mCustomMapFragment.setOnDragListener((MapWrapperLayout.OnDragListener) getApplicationContext());
            googleMap = mCustomMapFragment.getMap();
            locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            Location lastLoc = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            final double lat = lastLoc.getLatitude();
            final double lng = lastLoc.getLongitude();
            final LatLng lasLatLng = new LatLng(lat, lng);
            googleMap.setMyLocationEnabled(true);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lasLatLng, 18.5F));
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStart:
                startLocationService();
                break;
            case R.id.btnStop:
                stopLocationService();
                break;
        }
    }

    private void startLocationService() {
        startService(new Intent(this, LocationService.class));
    }

    private void stopLocationService() {
        stopService(new Intent(this, LocationService.class));
    }
}
