package id.tugasakhir.zakaria.lbsatmastar.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.tugasakhir.zakaria.lbsatmastar.R;

public class TerdekatMaps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terdekat_maps);

        Button btnmaps = (Button) findViewById(R.id.buttondaftar);
        btnmaps.setOnClickListener(onCreate());
    }

    private View.OnClickListener onCreate() {
        Intent intent = new Intent(getApplicationContext(),TerdekatMaps.class);
        startActivity(intent);

        return null;

    }
}
