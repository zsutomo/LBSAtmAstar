package id.tugasakhir.zakaria.lbsatmastar;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


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


    }
}
