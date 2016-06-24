package id.tugasakhir.zakaria.lbsatmastar.controller;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import id.tugasakhir.zakaria.lbsatmastar.R;


public class DetailActivity extends FragmentActivity implements OnMapReadyCallback {

    public TextView tvnama;
    public TextView tvalamat;
    public TextView tvinformasi;
    public TextView tvlatitude;
    public TextView tvlongitude;

    String nama;
    String alamat;
    String informasi;
    double latitude;
    double longitude;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

      DetailData();

    }

    private void DetailData() {

        Intent i = getIntent();
        nama = i.getStringExtra("nama");
        alamat = i.getStringExtra("alamat");
        informasi = i.getStringExtra("informasi");
        latitude = i.getDoubleExtra("latitude", latitude);
        longitude = i.getDoubleExtra("longitude", longitude);

        tvnama = (TextView) findViewById(R.id.tvnama);
        tvalamat = (TextView) findViewById(R.id.tvalamat);
        tvinformasi = (TextView) findViewById(R.id.tvinformasi);
        tvlatitude = (TextView) findViewById(R.id.tvlatitude);
        tvlongitude = (TextView) findViewById(R.id.tvlongitude);

        tvnama.setText("Nama : " + nama);
        tvalamat.setText("Alamat : " + alamat);
        tvinformasi.setText("Informasi : " + informasi);
        tvlatitude.setText(String.valueOf("Latitude : " + latitude));
        tvlongitude.setText(String.valueOf("Longitude : " + longitude));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager
                .PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);

        latitude = getIntent().getDoubleExtra("latitude", latitude);
        longitude = getIntent().getDoubleExtra("longitude", longitude);

        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude))).setIcon(BitmapDescriptorFactory.defaultMarker());

        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(14f).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}