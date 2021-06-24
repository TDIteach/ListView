package com.chakoujmed.demolistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProduitsAdapter extends BaseAdapter {
    ArrayList<Produit> listePro;
    Context ctx;
    LayoutInflater inflater;

    public ProduitsAdapter(ArrayList<Produit> listePro, Context ctx) {
        this.listePro = listePro;
        this.ctx = ctx;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return listePro.size();
    }

    @Override
    public Object getItem(int position) {
        return listePro.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listePro.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produit p=listePro.get(position);
        if(convertView==null) {
            inflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.element_produit,parent,false);
        }
        TextView tvID=convertView.findViewById(R.id.tvId);
        TextView tvDes=convertView.findViewById(R.id.tvDes);
        TextView tvPrix=convertView.findViewById(R.id.tvPrix);
        tvID.setText(p.getId()+"");
        tvDes.setText(p.getDesignation());
        tvPrix.setText(p.getPrix()+" DH");

        return convertView;
    }
}
