package id.tugasakhir.zakaria.lbsatmastar.controller;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.tugasakhir.zakaria.lbsatmastar.R;

public class Terdekat extends Activity {

    Button btnmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terdekat);

       btnmaps = (Button) findViewById(R.id.buttonmaps);

    }

    public void klikTombolMaps(View view){
        Intent intent = new Intent(this, TerdekatMaps.class);
        startActivity(intent);
        Terdekat.this.finish();
    }

}
