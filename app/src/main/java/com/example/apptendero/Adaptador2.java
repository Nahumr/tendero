package com.example.apptendero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador2 extends BaseAdapter {
    private Context context2;
    private ArrayList<Entidad2> listItems2;

    public Adaptador2(Context context2, ArrayList<Entidad2> listItems2) {
        this.context2 = context2;
        this.listItems2 = listItems2;
    }


    public int getCount() {
        return listItems2.size();
    }


    public Object getItem(int position) {
        return listItems2.get(position);
    }


    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView2, ViewGroup parent) {
        Entidad2 item2 = (Entidad2)getItem(position);

        convertView2 = LayoutInflater.from(context2).inflate(R.layout.item2, null);
        ImageView imgFoto2 =(ImageView)convertView2.findViewById(R.id.imgFoto2);
        TextView tvTitulo2 = (TextView) convertView2.findViewById(R.id.tvTitulo2);
        TextView tvContenido2 = (TextView)convertView2.findViewById(R.id.tvContenido2);


        final String orden = item2.getOrden();


        EditText spinner2 = (EditText) convertView2.findViewById(R.id.Txtnum);
        String [] max = new String[Integer.parseInt(item2.getNumero())+1];

        imgFoto2.setImageResource(item2.getImgFoto());
        tvTitulo2.setText(item2.getTitulo());
        tvContenido2.setText(item2.getContenido());



        spinner2.setText(item2.getNumero());
        return convertView2;
    }
}
