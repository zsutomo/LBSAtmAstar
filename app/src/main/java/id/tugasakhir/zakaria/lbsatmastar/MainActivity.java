package id.tugasakhir.zakaria.lbsatmastar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void klikTombolMenu(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button1:
                intent = new Intent(this, DaftarLokasiAtm.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(this, GMapsActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent = new Intent(this, Terdekat.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Apakah Anda Yakin Ingin Keluar?").setCancelable(false);
        dialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                MainActivity.this.finish();
            }
        });
        dialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

}
