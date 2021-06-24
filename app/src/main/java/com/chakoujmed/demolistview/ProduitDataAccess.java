package com.chakoujmed.demolistview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProduitDataAccess {
    public  ProduitsOpenHelper helper;
    private  SQLiteDatabase db;

    public  ProduitDataAccess(ProduitsOpenHelper helper) {
        this.helper = helper;
        this.db=helper.getReadableDatabase();
    }
    public    void AjouterProduit(Produit p){
        ContentValues cv=new ContentValues();
        cv.put(TableProduit.ColDesigantion,p.getDesignation());
        cv.put(TableProduit.ColPrix,p.getPrix());
        db.insert(TableProduit.Name,null,cv);

    }
    public  void SupprimerProduit(int id){
        db.delete(TableProduit.Name,"id=?",new String[]{Integer.toString(id)});
    }
    public void ModifierProduit(int id,Produit newProduit){
        ContentValues cv=new ContentValues();
        cv.put(TableProduit.ColDesigantion,newProduit.getDesignation());
        cv.put(TableProduit.ColPrix,newProduit.getPrix());
        db.update(TableProduit.Name,cv,"id=?",new String[]{Integer.toString(id)});

    }
    public    ArrayList<Produit> getListe(){
        ArrayList<Produit> ls=new ArrayList<>();
        Cursor cus=db.query(TableProduit.Name,new String[]{TableProduit.ColId,TableProduit.ColDesigantion,TableProduit.ColPrix},"",null,"","",TableProduit.ColId+" asc");
        cus.moveToFirst();
        while (!cus.isAfterLast()){
            int id=cus.getInt(0);
            String des=cus.getString(1);
            float prix=cus.getFloat(2);
            Produit pr=new Produit(id,des,prix);

            ls.add(pr);
            cus.moveToNext();
        }
        return  ls;


    }
}
