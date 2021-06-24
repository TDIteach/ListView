package com.chakoujmed.demolistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  ListView lvpro;
  EditText etDes,etPrix;
    ArrayList<Produit> ls;
    ProduitsOpenHelper helper;
    ProduitDataAccess dataAccess;
    Button btnA,btnV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper=new ProduitsOpenHelper(this,"dbProduits",null,1);
        dataAccess=new ProduitDataAccess(helper);
        lvpro = findViewById(R.id.lvProduits);
        registerForContextMenu(lvpro);
        etDes=findViewById(R.id.etDes);
        etPrix=findViewById(R.id.etPrix);
        btnA=findViewById(R.id.btnAjouter);
        btnV=findViewById(R.id.btnVider);
        btnA.setOnClickListener(this);
        btnV.setOnClickListener(this);
       ls = new ArrayList<>();
       Actualiser();

        }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_supp_modif,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        TextView lblId=(TextView)info.targetView.findViewById(R.id.tvId);
        int id=Integer.parseInt(lblId.getText().toString());
        switch (item.getItemId()){
           case R.id.btnSupp:
               dataAccess.SupprimerProduit(id);
               Toast.makeText(MainActivity.this,"Suppression avec succes",Toast.LENGTH_LONG).show();
               Actualiser();
            break;
            case R.id.btnModif:
                  String des=etDes.getText().toString();
                  Float prix=Float.parseFloat(etPrix.getText().toString());
                  Produit newP=new Produit(des,prix);
                  dataAccess.ModifierProduit(id,newP);
                Toast.makeText(MainActivity.this,"Modification avec succes",Toast.LENGTH_LONG).show();
                Actualiser();
                break;


        }
        return super.onContextItemSelected(item);

    }

    public  void  Actualiser(){
        ls=dataAccess.getListe();
        ProduitsAdapter adapter=new ProduitsAdapter(ls,MainActivity.this);
        lvpro.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAjouter:
                String des=etDes.getText().toString();
                Float prix=Float.parseFloat(etPrix.getText().toString());
                Produit p=new Produit(des,prix);
                dataAccess.AjouterProduit(p);
                Toast.makeText(MainActivity.this,"Produit ajoute avec succes",Toast.LENGTH_LONG).show();
                Actualiser();
                break;
            case R.id.btnVider:
                 etDes.setText("");
                 etPrix.setText("");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
