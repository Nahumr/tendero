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

public class venta extends AppCompatActivity {
    private ListView lvItems2;
    private Adaptador2 adaptador;
    String [] datos;
    String mensaje, num_orden;
    Button Buscar, Cobrar;
    public EditText orden;
    public TextView txventa;
    int count2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);


        Buscar = (Button)findViewById(R.id.ButtonBuscar);
        Cobrar = (Button)findViewById(R.id.ButtonCobrar);
        orden = (EditText)findViewById(R.id.NumeroOrden);

        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(orden.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"El campo de orden, esta vacio.",Toast.LENGTH_SHORT).show();
                }
                else{
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://74.208.87.37:80/xampp/Develop/listar_orden.php?id_ticket="+orden.getText().toString()+"", new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            JSONObject jsonObject = null;
                            datos = new String[response.length()];
                            num_orden = orden.getText().toString();


                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    jsonObject = response.getJSONObject(i);
                                    String a = jsonObject.getString("nombre");
                                    String b = jsonObject.getString("venta");
                                    String d = jsonObject.getString("cantidad");
                                    mensaje = jsonObject.getString("mensaje");
                                    datos[i] = "" + a + "-" + b +  "-" + d + "";

                                } catch(Exception e){
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            if (mensaje == null)
                                Toast.makeText(getApplicationContext(),"No existe el ticket introcido",Toast.LENGTH_SHORT).show();
                            else
                                init();
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

            }
        });

        Cobrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cobrar("http://192.168.1.15:80/xampp/Develop/confirmar.php?id_ticket="+orden.getText().toString()+"");
                Intent refresh = new Intent(getApplicationContext(), menu.class);
                startActivity(refresh);
            }
        });

    }

    private void cobrar(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Felicidades, este numero de orden ya se ha cobrado", Toast.LENGTH_SHORT).show();
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

    private void init() {
        lvItems2=(ListView) findViewById(R.id.lvItems2);
        adaptador = new Adaptador2(getApplicationContext(), GetArrayItems());
        lvItems2.setAdapter(adaptador);
        final int count = lvItems2.getAdapter().getCount();
        count2 = count;
    }

    private ArrayList<Entidad2> GetArrayItems(){
        ArrayList<Entidad2> listItems = new ArrayList<>();
        for(int i=0; i<datos.length; i++){
            String[] parts = datos[i].split("-");
            listItems.add(new Entidad2(R.drawable.ic_android, parts[0], "$: "+parts[1], parts[2], num_orden));
            }
        return listItems;
    }
}
