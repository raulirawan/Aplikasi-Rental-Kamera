package com.example.sewakamera;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sewakamera.R;
import com.example.sewakamera.DataHelper;

import java.util.List;

public class SewaKameraActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText nama, alamat, no_hp, lama;
    RadioGroup promo;
    RadioButton weekday, weekend;
    Button selesai;

    String sNama, sAlamat, sNo, sMerk, sLama;
    double dPromo;
    int iLama, iPromo, iHarga;
    double dTotal;

    private Spinner spinner;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa);

        dbHelper = new DataHelper(this);

        spinner = findViewById(R.id.spinner);
        selesai = findViewById(R.id.selesaiHitung);
        nama = findViewById(R.id.eTNama);
        alamat = findViewById(R.id.eTAlamat);
        no_hp = findViewById(R.id.eTHP);
        promo = findViewById(R.id.promoGroup);
        weekday = findViewById(R.id.rbWeekDay);
        weekend = findViewById(R.id.rbWeekEnd);
        lama = findViewById(R.id.eTLamaSewa);

        spinner.setOnItemSelectedListener(this);

        loadSpinnerData();

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sNama = nama.getText().toString();
                sAlamat = alamat.getText().toString();
                sNo = no_hp.getText().toString();
                sLama = lama.getText().toString();
                if (sNama.isEmpty() || sAlamat.isEmpty() || sNo.isEmpty() || sLama.isEmpty()) {
                    Toast.makeText(SewaKameraActivity.this, "(*) tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (weekday.isChecked()) {
                    dPromo = 0.1;
                } else if (weekend.isChecked()) {
                    dPromo = 0.25;
                }

                if (sMerk.equals("Canon DSLR Omega 500")) {
                    iHarga = 400000;
                } else if (sMerk.equals("Canon DSLR Omega 5001")) {
                    iHarga = 400000;
                } else if (sMerk.equals("Canon DSLR Omega 5002")) {
                    iHarga = 400000;
                } else if (sMerk.equals("Canon DSLR Omega 5003")) {
                    iHarga = 450000;
                } else if (sMerk.equals("Canon DSLR Omega 5004")) {
                    iHarga = 500000;
                } else if (sMerk.equals("Canon DSLR Omega 5005")) {
                    iHarga = 550000;
                } else if (sMerk.equals("Canon DSLR Omega 5006")) {
                    iHarga = 550000;
                } else if (sMerk.equals("Canon DSLR Omega 5007")) {
                    iHarga = 700000;
                } else if (sMerk.equals("Canon DSLR Omega 5008")) {
                    iHarga = 1500000;
                }

                iLama = Integer.parseInt(sLama);
                iPromo = (int) (dPromo * 100);
                dTotal = (iHarga * iLama) - (iHarga * iLama * dPromo);

                SQLiteDatabase dbH = dbHelper.getWritableDatabase();
                dbH.execSQL("INSERT INTO penyewa (nama, alamat, no_hp) VALUES ('" +
                        sNama + "','" +
                        sAlamat + "','" +
                        sNo + "');");
                dbH.execSQL("INSERT INTO sewa (merk, nama, promo, lama, total) VALUES ('" +
                        sMerk + "','" +
                        sNama + "','" +
                        iPromo + "','" +
                        iLama + "','" +
                        dTotal + "');");
                PenyewaActivity.m.RefreshList();
                finish();

            }
        });

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbSewaMobl);
        toolbar.setTitle("Sewa Kamera");
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

    private void loadSpinnerData() {
        DataHelper db = new DataHelper(getApplicationContext());
        List<String> categories = db.getAllCategories();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sMerk = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

