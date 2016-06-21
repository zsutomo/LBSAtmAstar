package id.tugasakhir.zakaria.lbsatmastar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

import id.tugasakhir.zakaria.lbsatmastar.adapter.DaftarAtmAdapter;
import id.tugasakhir.zakaria.lbsatmastar.database.DatabaseHelper;
import id.tugasakhir.zakaria.lbsatmastar.model.Atm;

public class DaftarLokasiAtm extends Activity {

    private ListView listViewAtm;
    private DatabaseHelper mDBHelper;
    private List<Atm> atmList;
    private DaftarAtmAdapter AtmAdapter;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_lokasi_atm);

        listViewAtm = (ListView) findViewById(R.id.listview_atm);
        editText = (EditText) findViewById(R.id.editText);
        mDBHelper = new DatabaseHelper(this);

        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (false == database.exists()) {
            mDBHelper.getReadableDatabase();
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy database not successfully", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        atmList = mDBHelper.getAtmList();
        AtmAdapter = new DaftarAtmAdapter(this, atmList);
        listViewAtm.setAdapter(AtmAdapter);
        listViewAtm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

            }

        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                AtmAdapter.filter(text);

            }
        });
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte [] buff = new byte [1024];
            int length =0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("ATM", "DB COPIED");
            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
