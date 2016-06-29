package id.tugasakhir.zakaria.lbsatmastar.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.tugasakhir.zakaria.lbsatmastar.R;

public class Terdekat extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terdekat);

        Button btnmaps = (Button) findViewById(R.id.buttonmaps);
        btnmaps.setOnClickListener(onklik());

    }

    private View.OnClickListener onklik() {
        Intent intent = new Intent(getApplicationContext(),TerdekatMaps.class);
        startActivity(intent);

        return null;
    }

}
