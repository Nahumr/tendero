package com.example.apptendero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class compra extends AppCompatActivity {
    private ListView lv;
    private Adaptador adaptador;
    String [] datos;
    String deo;
    int count;
    Button Comprar, BuscarC;
    ArrayList<Entidad> listItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);



        Comprar = (Button)findViewById(R.id.button);
        BuscarC = (Button)findViewById(R.id.ButtonBuscarC);

        BuscarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://74.208.87.37:80/xampp/Develop/listar_productos.php";
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        datos = new String[response.length()];


                        for (int i = 0; i < response.length(); i++) {
                            try {
                                jsonObject = response.getJSONObject(i);
                                String a = jsonObject.getString("nombre");
                                String b = jsonObject.getString("precio_v");
                                String c = jsonObject.getString("cantidad");
                                String d = jsonObject.getString("precio_c");
                                datos[i] = "" + a + "-" + b + "-" + c + "-" + d + "";

                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        ejecutar();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonArrayRequest);
            }
        });

        String sas = getIntent().getStringExtra(menu.stringK);
        deo = sas;




        Comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cad ="";
                String nom ="";

                for (int i=0; i<lv.getChildCount(); i++){
                    ViewGroup row = (ViewGroup)lv.getChildAt(i);
                    Spinner spinner = (Spinner) row.findViewById(R.id.Spinner);
                    TextView textView = (TextView)row.findViewById(R.id.tvTitulo);
                    String sel = (String) spinner.getSelectedItem();
                    cad = sel;
                    nom = textView.getText().toString();
                    Toast.makeText(getApplicationContext(),"Gracias por comprar",Toast.LENGTH_SHORT).show();
                   comprar_producto("http://74.208.87.37:80/xampp/Develop/comprar_producto.php?num="+cad+"&nom="+nom+"&id="+deo+"");
                    Intent refresh = new Intent(getApplicationContext(), menu.class);
                    startActivity(refresh);

                }

            }
        });
    }

    private void comprar_producto(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Has comprado nuevos productos", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Hubo algun error", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(stringRequest);


    }

    private void ejecutar() {

            lv=(ListView) findViewById(R.id.lvItems);
            adaptador = new Adaptador(getApplicationContext(), GetArrayItems(listItems));
            lv.setAdapter(adaptador);
            count = lv.getAdapter().getCount();







    }



    private ArrayList<Entidad> GetArrayItems(ArrayList<Entidad> listItems){

        if(listItems.isEmpty()) {

            for (int i = 0; i < datos.length; i++) {
                String[] parts = datos[i].split("-");
                listItems.add(new Entidad(R.drawable.ic_android, parts[0], "Precio de venta: " + parts[1], parts[2], "Precio de compra: " + parts[3]));
            }
        }

        return listItems;
    }



}
