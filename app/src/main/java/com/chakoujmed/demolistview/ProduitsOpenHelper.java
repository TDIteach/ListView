package com.chakoujmed.demolistview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

public class ProduitsOpenHelper extends SQLiteOpenHelper {
    private final  String dbName="produits.db";

    public ProduitsOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table "+TableProduit.Name+"("+ TableProduit.ColId +" integer  primary key autoincrement,"+TableProduit.ColDesigantion+" text,"+TableProduit.ColPrix+" real)";
       db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TableProduit.Name);
        onCreate(db);

    }
}
