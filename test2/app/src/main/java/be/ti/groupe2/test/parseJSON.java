package be.ti.groupe2.test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class parseJSON extends ActionBarActivity implements View.OnClickListener{

    private String myJSONString;

    private static final String ID = "userID";
    private static final String USERNAME= "userLogin";
    private static final String MAIL = "userEmail";
    private static final String PASSWORD = "userPassword";

    private JSONArray users;

    private int TRACK = 0;

    private EditText editTextId;
    private EditText editTextUserName;
    private EditText editTextEMail;
    private EditText editTextPassword;

    Button btnPrev;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);

        Intent intent = getIntent();
        myJSONString = intent.getStringExtra(MainActivity.MY_JSON);


        editTextId = (EditText) findViewById(R.id.editTextID);
        editTextUserName = (EditText) findViewById(R.id.editTextUsername);
        editTextEMail = (EditText) findViewById(R.id.editTextMail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        btnPrev = (Button) findViewById(R.id.buttonPrev);
        btnNext = (Button) findViewById(R.id.buttonNext);

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        extractJSON();
        if(users != null) {


            showData();
        }
        else
            System.out.print("---------------------users null-------------------------------------");
    }



    private void extractJSON(){
        try {
            users = new JSONArray(myJSONString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void moveNext(){
        if(TRACK<users.length()){
            TRACK++;
        }
        showData();
    }

    private void movePrev(){
        if(TRACK>0){
            TRACK--;
        }
        showData();
    }

    private void showData(){
        try {
            JSONObject jsonObject = users.getJSONObject(TRACK);

            editTextId.setText(jsonObject.getString(ID));
            editTextUserName.setText(jsonObject.getString(USERNAME));
            editTextEMail.setText(jsonObject.getString(MAIL));
            editTextPassword.setText(jsonObject.getString(PASSWORD));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v == btnNext){
            moveNext();
        }
        if(v == btnPrev){
            movePrev();
        }
    }
}