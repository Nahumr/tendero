package com.example.apptendero;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Login extends AppCompatActivity{
    Button buttonentrar;
    public EditText TXTpass, TXTuser;
    public static final String stringKey ="skey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonentrar = (Button)findViewById(R.id.ButtonEntrar);
        TXTuser = (EditText)findViewById(R.id.txtUser);
        TXTpass = (EditText)findViewById(R.id.txtPass);

        buttonentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ejecutar("http://74.208.87.37:80/xampp/Develop/log_Android.php?id="+TXTuser.getText()+"&contraseña="+TXTpass.getText()+"");
            }
        });
    }

    private void ejecutar(String URL){

        JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, URL,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

               try {
                    JSONArray jsonArray = response.getJSONArray("json");
                    for(int i = 0; i< response.length(); i++){
                        JSONObject prueba = jsonArray.getJSONObject(i);
                        String name = prueba.getString("mensaje");
                        if(name.equals("Bienvenido")){
                            Toast.makeText(getApplicationContext(), ""+name, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), menu.class);
                            intent.putExtra(stringKey,TXTuser.getText().toString());
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), ""+name, Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Error de conexion",Toast.LENGTH_SHORT).show();
                }
            }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(json);

    }
}
