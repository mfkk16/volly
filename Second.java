package com.example.calsys.api27nov;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Second extends AppCompatActivity {
    
    RecyclerView recyc;
    ArrayAdapter adapter;
    ArrayList<Inventory> array_name = new ArrayList<>();

    private RequestQueue fasilreqest;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyc = findViewById(R.id.recyc);
        adapter = new ArrayAdapter(array_name);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        recyc.setLayoutManager(lm);
        recyc.setItemAnimator(new DefaultItemAnimator());
        recyc.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

//        int i = 0;
//        BUTTON.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                i++;
//                Login(i, "GETITEMS", "1", "123456", "1", "", "10");
//
//            }
//        });

        Login( "GETITEMS", "1", "123456", "1", "", "10");

    }

    private void Login( final String Method, final String BranchID, final String Token, final String WarehouseID, final String itemID, final String SearchString) {

        fasilreqest = Volley.newRequestQueue(this);


        StringRequest strReq = new StringRequest(Request.Method.POST,
                ContantKK.URL, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                inputdata(response);
                pDialog.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Err", "Error: " + error.getMessage());
                        pDialog.dismiss();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Method", Method);
                params.put("BranchID", BranchID);
                params.put("Token", Token);
                params.put("WarehouseID", WarehouseID);
                params.put("itemID", itemID);
                params.put("SearchString", SearchString);
//                params.put("count", 10);
//                params.put("page", page);
                return params;
            }
        };
        fasilreqest.add(strReq);
    }

    private void inputdata(String res) {

        try {
            JSONObject obj1 = new JSONObject(res);
            JSONArray jsonArray = obj1.getJSONArray("data");
//            array_name.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String ID = jsonObject1.getString("ID");
                String ItemCode = jsonObject1.getString("ItemCode");
                String ItemName = jsonObject1.getString("ItemName");
                String Unit = jsonObject1.getString("Unit");

                Inventory d = new Inventory(ID, ItemCode, ItemName, Unit);
                array_name.add(d);
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
