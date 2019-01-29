package com.example.calsys.api27nov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {
    EditText usr, pwd;
    Button btn;
    private RequestQueue fasilreqest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        usr = findViewById(R.id.usr);
        pwd = findViewById(R.id.pwd);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = usr.getText().toString();
                String password = pwd.getText().toString();
                Login(name, password);
            }
        });
    }

    private void Login(final String name, final String password) {
        fasilreqest = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.POST,
                ContantKK.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj1 = new JSONObject(response);
                    JSONObject obj2 = obj1.getJSONObject("status");
                    String code = obj2.getString("code");
                    if (code.equals("100")) {
                        Toast.makeText(Home.this, "Sucess", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), Second.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Home.this, "FAIL", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Err", "Error: " + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Method", "LOGIN");
                params.put("Username", name);
                params.put("Password", password);
                return params;
            }
        };
        fasilreqest.add(strReq);
    }
}

