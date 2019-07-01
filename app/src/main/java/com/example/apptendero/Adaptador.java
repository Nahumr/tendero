package com.example.apptendero;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Entidad> listItems;

    public Adaptador(Context context, ArrayList<Entidad> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entidad item = (Entidad)getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            ImageView imgFoto =(ImageView)convertView.findViewById(R.id.imgFoto);
            TextView tvTitulo = (TextView) convertView.findViewById(R.id.tvTitulo);
            TextView tvContenido = (TextView)convertView.findViewById(R.id.tvContenido);
            TextView tvContenido2 = (TextView)convertView.findViewById(R.id.tvContenido2);

            Spinner spinner = (Spinner) convertView.findViewById(R.id.Spinner);
            String [] max = new String[Integer.parseInt(item.getNumero())+1];
            //String max = ""+Integer.parseInt(item.getNumero())+1;
            imgFoto.setImageResource(item.getImgFoto());
            tvTitulo.setText(item.getTitulo());
            tvContenido.setText(item.getContenido());
            tvContenido2.setText(item.getContenido2());
            for(int i=0; i<= Integer.parseInt(item.getNumero());i++){
                max[i] = ""+i;
            }
            spinner.setAdapter(new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,max));
            //spinner.setText(max);
        }

        return convertView;
    }
}
