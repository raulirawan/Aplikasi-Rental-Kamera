package com.example.sewakamera;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sewakamera.R;
import com.example.sewakamera.DataHelper;
public class DetailKameraActivity extends AppCompatActivity{
    protected Cursor cursor;
    String sMerk, sHarga, sGambar;
    DataHelper dbHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kamera);

        Bundle terima = getIntent().getExtras();

        dbHelper = new DataHelper(this);
        Intent intent = getIntent();

        String merk = terima.getString("merk");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from kamera where merk = '" + merk + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sMerk = cursor.getString(0);
            sHarga = cursor.getString(1);
        }

        if (sMerk.equals("Canon DSLR Omega 500")) {
            sGambar = "dslr";
        } else if (sMerk.equals("Canon DSLR Omega 5001")) {
            sGambar = "dslr";
        } else if (sMerk.equals("Canon DSLR Omega 5002")) {
            sGambar = "dslr";
        } else if (sMerk.equals("Canon DSLR Omega 5003")) {
            sGambar = "dslr";
        } else if (sMerk.equals("Canon DSLR Omega 5004")) {
            sGambar = "dslr";
        } else if (sMerk.equals("Canon DSLR Omega 5005")) {
            sGambar = "dslr";
        } else if (sMerk.equals("Canon DSLR Omega 5006")) {
            sGambar = "dslr";
        } else if (sMerk.equals("Canon DSLR Omega 5007")) {
            sGambar = "dslr";
        } else if (sMerk.equals("Canon DSLR Omega 5008")) {
            sGambar = "dslr";
        }

        ImageView ivGambar = findViewById(R.id.ivMobil);
        TextView tvMerk = findViewById(R.id.JMobil);
        TextView tvHarga = findViewById(R.id.JHarga);

        tvMerk.setText(sMerk);
        ivGambar.setImageResource(getResources().getIdentifier(sGambar, "drawable", getPackageName()));
        tvHarga.setText("Rp. " + sHarga);

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbDetailMbl);
        toolbar.setTitle("Detail Kamera");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}