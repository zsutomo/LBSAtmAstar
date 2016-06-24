package id.tugasakhir.zakaria.lbsatmastar.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import id.tugasakhir.zakaria.lbsatmastar.R;
import id.tugasakhir.zakaria.lbsatmastar.database.DatabaseHelper;
import id.tugasakhir.zakaria.lbsatmastar.model.Atm;

public class GMapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {


    private GoogleMap mMap;
    private List<Atm> mapsAtm;
    private SupportMapFragment mapFragment;
    private String provider = null;
    private LocationManager mLocationManager = null;
    private Marker mCurrentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(id.tugasakhir.zakaria.lbsatmastar.R.layout.activity_gmaps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        TampilLokasi();
    }

    private void TampilLokasi() {
        mapsAtm = new ArrayList<Atm>();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
                DatabaseHelper dbHelper = new DatabaseHelper(GMapsActivity.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM nodeatm", null);
                while (cursor.moveToNext()) {
                    Atm atm = new Atm(
                            cursor.getString(cursor.getColumnIndexOrThrow("nama")),
                            cursor.getString(cursor.getColumnIndexOrThrow("alamat")),
                            cursor.getString(cursor.getColumnIndexOrThrow("informasi")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("latitude")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("longitude"))
                    );
                    mapsAtm.add(atm);
                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mapFragment.getMapAsync(GMapsActivity.this);
//                    }
//                });
//            }
//        }).start();
//
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission (this,
                android.Manifest.permission
                        .ACCESS_FINE_LOCATION) != PackageManager
                        .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission
                        .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        mMap.setMyLocationEnabled(true);

        for (Atm atm : mapsAtm) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(atm.getLatitude(), atm.getLongitude()))
                    .title(atm.getNama())
                    .snippet(atm.getAlamat())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            );
        }

        if (isProviderAvailable() && (provider !=null)) {
            locateCurrentPosition();
        }
    }

    private void locateCurrentPosition() {
            int status = getPackageManager().checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION, getPackageName());
                    if (status == PackageManager.PERMISSION_GRANTED) {
                        Location location = mLocationManager.getLastKnownLocation(provider);
                        updateWithNewLocation(location);

                        long minTime = 5000;
                        float minDist = 5.0f;

                        mLocationManager.requestLocationUpdates(provider, minTime, minDist, (LocationListener) this);
                    }
    }

    private boolean isProviderAvailable() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        provider = mLocationManager.getBestProvider(criteria, true);
        if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            provider = LocationManager.NETWORK_PROVIDER;

            return  true;
        }

        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
            return true;
        }

        if (provider != null) {
            return true;
        }

        return false;
    }

    private void updateWithNewLocation(Location location) {
        if (location != null && provider !=null ) {
            double lng = location.getLongitude();
            double lat = location.getLatitude();

            addBoundaryToCurrentPosition (lat, lng);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(lat, lng)).zoom(12f).build();

            if (mMap != null)
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        } else {
            Log.d("Location Error", "Something went wrong");
        }

    }

    private void addBoundaryToCurrentPosition(double lat, double lang) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(lat, lang));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
        markerOptions.title("My Location");
        //markerOptions.anchor(0.5f, 0.5f);

        CircleOptions mOptions = new CircleOptions()
                .center(new LatLng(lat, lang)).radius(10000)
                .strokeColor(0x110000FF).strokeWidth(1).fillColor(0x110000FF);
        mMap.addCircle(mOptions);
        if (mCurrentPosition != null)
            mCurrentPosition.remove();
        mCurrentPosition = mMap.addMarker(markerOptions);
    }


    @Override
    public void onLocationChanged(Location location) {
        updateWithNewLocation(location);
    }


    @Override
    public void onProviderDisabled(String provider) {
        updateWithNewLocation(null);
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                break;
            case LocationProvider.AVAILABLE:
                break;
        }
    }
}