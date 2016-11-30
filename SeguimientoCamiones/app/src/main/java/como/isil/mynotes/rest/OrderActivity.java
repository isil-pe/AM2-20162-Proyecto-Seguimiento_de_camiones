package como.isil.mynotes.rest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.isil.mynotes.rest.R;

import como.isil.mynotes.rest.entity.OrderEntity;
import como.isil.mynotes.rest.utils.LocationService;
import como.isil.mynotes.rest.view.fragments.OrderDetailFragment;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback{

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

    Handler h = new Handler();
    int delay = 15000; //15 seconds
    Runnable runnable;

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

        mapElements();

        btnStart= findViewById(R.id.btnStart);
        btnStop= findViewById(R.id.btnStop);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);


    }



    private void initializeUI() {
        try {
            initializeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapita);
        mapFragment.getMapAsync(this);

        /*

        if (googleMap == null) {
            mCustomMapFragment = ((CustomMapFragment) getFragmentManager().findFragmentById(R.id.mapita));
            mCustomMapFragment.setOnDragListener((MapWrapperLayout.OnDragListener) getApplicationContext());
            mCustomMapFragment.getMapAsync(this);

        }*/
    }

    public void crearMarcador(GoogleMap googleMap){


    }



    public void onMapReady(GoogleMap googleMap) {

        locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(OrderActivity.this, "Por favor ceder permisos en configuración.", Toast.LENGTH_LONG).show();
            return;
        }


        Location lastLoc = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        final double lat = lastLoc.getLatitude();
        final double lng = lastLoc.getLongitude();


        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title("Actual"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(lat, lng),12));
/*
        TextView txvCoord = (TextView) findViewById(R.id.tviCoord);
        txvCoord.setText("Latitud: "+lat+", Longitud"+lng);*/


        Toast.makeText(getApplicationContext(),
                "Latitud: "+origen_latitud+", Longitud"+origen_longitud, Toast.LENGTH_SHORT)
                .show();
        googleMap.setMyLocationEnabled(true);
        if (googleMap == null) {
            Toast.makeText(getApplicationContext(),
                    "Error al crear los mapas", Toast.LENGTH_SHORT)
                    .show();
        }




        Double origlat = Double.parseDouble(origen_latitud);
        Double origlon = Double.parseDouble(origen_longitud);
        Double destlat = Double.parseDouble(destino_latitud);
        Double destlon = Double.parseDouble(destino_longitud);


        LatLng origen = new LatLng(origlat,origlon);
        LatLng destino = new LatLng(destlat,destlon);

        googleMap.addMarker(new MarkerOptions()
                .position(origen)
                .title("Origen"));


        googleMap.addMarker(new MarkerOptions()
                .position(destino)
                .title("Destino"));



    }



    private void mapElements() {
        locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(OrderActivity.this, "Por favor ceder permisos en configuración.", Toast.LENGTH_LONG).show();
            return;
        }


        Location lastLoc = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        final double lat = lastLoc.getLatitude();
        final double lng = lastLoc.getLongitude();



        TextView txvCoord = (TextView) findViewById(R.id.tviCoord);
        txvCoord.setText("Latitud: " + lat + ", Longitud" + lng);


        final LatLng lasLatLng = new LatLng(lat, lng);
        Toast.makeText(getApplicationContext(),
                "Latitud: " + lat + ", Longitud" + lng, Toast.LENGTH_SHORT)
                .show();
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

        Toast.makeText(getApplicationContext(), "Rastreo iniciado", Toast.LENGTH_SHORT).show();
            h.postDelayed(new Runnable() {
                public void run() {

                    locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(OrderActivity.this, "Por favor ceder permisos en configuración.", Toast.LENGTH_LONG).show();
                        return;
                    }


                    Location lastLoc = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    final double lat = lastLoc.getLatitude();
                    final double lng = lastLoc.getLongitude();

                    TextView txvCoord = (TextView) findViewById(R.id.tviCoord);
                    txvCoord.setText("Ub. actual: Lat: "+lat+", Lon: "+lng);

                    locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(OrderActivity.this, "Por favor ceder permisos en configuración.", Toast.LENGTH_LONG).show();
                        return;
                    }


                    Toast.makeText(getApplicationContext(), "Ubicación enviada", Toast.LENGTH_SHORT).show();




                    runnable=this;

                    h.postDelayed(runnable, delay);
                }
            }, delay);


      /*  startService(new Intent(this, LocationService.class));*/
    }

    private void stopLocationService() {

            h.removeCallbacks(runnable);
        Toast.makeText(getApplicationContext(), "Envio de coordenadas detenido", Toast.LENGTH_SHORT).show();

    }
}
