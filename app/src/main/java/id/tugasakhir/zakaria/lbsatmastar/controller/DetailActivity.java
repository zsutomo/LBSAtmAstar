package id.tugasakhir.zakaria.lbsatmastar.controller;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import id.tugasakhir.zakaria.lbsatmastar.R;
import id.tugasakhir.zakaria.lbsatmastar.modul.DirectionFinder;
import id.tugasakhir.zakaria.lbsatmastar.modul.DirectionFinderListener;
import id.tugasakhir.zakaria.lbsatmastar.model.Route;


public class DetailActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener , DirectionFinderListener{

    public TextView tvnama;
    public TextView tvalamat;
    public TextView tvinformasi;
    public TextView tvlatitude;
    public TextView tvlongitude;

    String nama, alamat, informasi;
    double lat, lng;
    private GoogleMap mMap;
    private Location location;
    private GoogleApiClient googleApiClient;
    private Bundle bundle;
    ArrayList<LatLng> markerPoints;
    private double latitude;
    private double longitude;
    private Button btnRoute;
    private ProgressDialog progressDialog;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths  = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        onConnected(bundle);
        DetailData();

        btnRoute = (Button) findViewById(R.id.btn_route);
        btnRoute.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getRoute();
            }
        });
    }

    private void getRoute() {
        LatLng origin = new LatLng(location.getLatitude(), location.getLongitude());
        LatLng destination = new LatLng(getIntent().getDoubleExtra("latitude",lat), getIntent().getDoubleExtra("longitude",lng));

        String ori = origin.latitude + ","  + origin.longitude;
        String dest = destination.latitude + ","  + destination.longitude;
        Log.d("ori",ori);
        Log.d("desti",dest);
        try {
            new DirectionFinder(this,ori, dest).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        latitude = getIntent().getDoubleExtra("latitude", lat);
        longitude = getIntent().getDoubleExtra("longitude", lng);
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(14f).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    private void DetailData() {

        Intent i = getIntent();
        nama = i.getStringExtra("nama");
        alamat = i.getStringExtra("alamat");
        informasi = i.getStringExtra("informasi");
        latitude = i.getDoubleExtra("latitude", lat);
        longitude = i.getDoubleExtra("longitude", lng);

        tvnama = (TextView) findViewById(R.id.tvnama);
        tvalamat = (TextView) findViewById(R.id.tvalamat);
        tvinformasi = (TextView) findViewById(R.id.tvinformasi);
        tvlatitude = (TextView) findViewById(R.id.tvlatitude);
        tvlongitude = (TextView) findViewById(R.id.tvlongitude);

        tvnama.setText("Nama : " + nama);
        tvalamat.setText("Alamat : " + alamat);
        tvinformasi.setText("Informasi : " + informasi);
        tvlatitude.setText(String.valueOf("Latitude : " + lat));
        tvlongitude.setText(String.valueOf("Longitude : " + lng));
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (location == null) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (mMap != null) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()),  14));
                mMap.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title("Start Point")
                .icon(BitmapDescriptorFactory.defaultMarker())
                );
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                Toast.makeText(this, "Lokasi kamu saat ini, sebagai patokan titik awal perjalanan", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed( ConnectionResult connectionResult) {

    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "please wait.", "finding direction....!", true );

        if(originMarkers !=null) {
            for (Marker marker : originMarkers){
                marker.remove();
            }
        }
        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }
        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.CYAN).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }


    }
}