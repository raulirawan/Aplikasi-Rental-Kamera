package com.example.sewakamera;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sewakamera.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table penyewa (" +
                "nama text," +
                "alamat text," +
                "no_hp text," +
                "primary key(nama)" +
                ");" +
                "");
        db.execSQL("create table kamera(" +
                "merk text," +
                "harga int," +
                "primary key(merk)" +
                ");" +
                "");
        db.execSQL("create table sewa(" +
                "merk text," +
                "nama text," +
                "promo int," +
                "lama int," +
                "total double," +
                "foreign key(merk) references mobil (merk), " +
                "foreign key(nama) references penyewa (nama) " +
                ");" +
                "");

        db.execSQL("insert into kamera values (" +
                "'Canon DSLR Omega 500'," +
                "100000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Canon DSLR 600D'," +
                "100000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Canon DSLR 700D'," +
                "200000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Canon DSLR 800D'," +
                "150000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Canon DSLR 200D'," +
                "250000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Canon DSLR 1100D'," +
                "75000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Canon DSLR 1200D'," +
                "85000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Canon DSLR 100D'," +
                "200000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Canon DSLR 60D'," +
                "250000" +
                ");" +
                "");
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from kamera";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}