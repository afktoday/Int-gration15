package be.ti.groupe2.projetintegration;

import java.util.*;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class filActu extends ActionBarActivity implements View.OnClickListener {

    private String myJSONString;
    public static final String JSON_URL = "http://projet_groupe2.hebfree.org/Clients.php";
    private static final String USERNAME = "userLogin";
    private static final String MAIL = "userEmail";
    private static final String PASSWORD = "userPassword";


    private ListView tv;
    private TextView editT;

    String myJson;
    Button kiwi;

    List <String> list;
    ArrayAdapter<String> adapter;

    private JSONArray user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        Intent intent = getIntent();
        myJSONString = intent.getStringExtra(MainActivity.MY_JSON);


        tv = (ListView) findViewById(R.id.tvk);
        editT = (TextView) findViewById(R.id.editT);



        kiwi = (Button) findViewById(R.id.kiwi);

        kiwi.setOnClickListener(this);



        extractJSON();


    }


    private void extractJSON() {
        try {
            user = new JSONArray(myJSONString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showData(int i) {
        try {
            JSONObject jsonObject = user.getJSONObject(i);
            list.add((jsonObject.getString(USERNAME)) + "   " + (jsonObject.getString(MAIL)) + "   " + (jsonObject.getString(PASSWORD)));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onClick(View v) {
        if(v == kiwi){
            System.out.println("myjson : " + myJson);
            extractJSON();
            list=new ArrayList<String>();
            int length = user.length();
            System.out.println(length);
            length = length -1;
            while (length >= 0){
                showData(length);
                length --;
            }
            adapter = new ArrayAdapter<String>(this, R.layout.list, R.id.editT, list);
            //adapter = new ArrayAdapter<String>(this,R.layout.accueil,R.id.editT,list);

            tv.setAdapter(adapter);

        }
    }
}