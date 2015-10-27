package be.ti.groupe2.projetintegration;



import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    public static final String MY_JSON ="MY_JSON";
    //==============================================================================================
    //============================================MENU==============================================
    //==============================================================================================

    public void VersProfil(View view) {setContentView(R.layout.activity_gestion_du_profil);}
    public void VersEvent(View view) {setContentView(R.layout.creation_evenement);}
    public void VersFil(View view) {setContentView(R.layout.accueil);}

    //==============================================================================================
    //=========================================CONNEXION============================================
    //==============================================================================================
    public static final String JSON_URL = "http://projet_groupe2.hebfree.org/Clients.php";
    private static final String ID = "userID";
    private static final String USERNAME= "userLogin";
    private static final String MAIL = "userEmail";
    private static final String PASSWORD = "userPassword";

    Button connexion;
    Button inscription;
    TextView lv;
    TextView tLogin;
    TextView tMdp;
    TextView cError;

    String login;
    String mdp;
    public String myJson;

    Boolean granted = false;

    private JSONArray users = null;

    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String>{
            ProgressDialog loading;

            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine()) != null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();
                }catch (Exception e){
                    return null;
                }
            }

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Please Wait...",null,true,true);
            }

            @Override
            protected void onPostExecute(String s){
                super.onPreExecute();
                loading.dismiss();
                lv.setText(s);
                myJson = s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);

    }

    private void extractJson(){
        try{
            users = new JSONArray(myJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void searchLogin(){
        int i = 0;
        String cLogin;
        String cMdp;

        try{
            while(i<users.length() && !granted){
                JSONObject jsonObject = users.getJSONObject(i);
                cLogin = jsonObject.getString(USERNAME);
                cMdp = jsonObject.getString(PASSWORD);
                if(cLogin.equals(login) && cMdp.equals(mdp)){
                    granted = true;
                }
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //==============================================================================================
    //=========================================Inscription==========================================
    //==============================================================================================

    public static final String iURL = "http://projet_groupe2.hebfree.org/inscription.php";

    EditText nom;
    EditText prenom;
    EditText ilogin;
    EditText mail;
    EditText iMdp;
    EditText iCMdp;

    TextView defaut;
    TextView outputView;

    Button btn_enregistrer;
    RequestQueue requestQueue;

    String s = null;
    String n = null;
    String p = null;
    String l = null;
    String m = null;
    String mp = null;
    String Cmp = null;
    boolean error = false;
    boolean errorMdp = false;
    Boolean present = true;


    public void VersInscription(View view) {
        setContentView(R.layout.inscription);
    }

    public void recupererVue(View v){
        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        ilogin = (EditText) findViewById(R.id.login);
        mail = (EditText) findViewById(R.id.email);
        iMdp = (EditText) findViewById(R.id.mdp);
        iCMdp = (EditText) findViewById(R.id.Cmdp);

        defaut = (TextView) findViewById(R.id.defaut);
        outputView = (TextView) findViewById(R.id.outputView);

        btn_enregistrer = (Button) findViewById(R.id.enregistrer);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    private void searchExistant(){
        int i = 0;
        String cMail;
        String cLogin;

        try{
            while(i<users.length() && present){
                JSONObject jsonObject = users.getJSONObject(i);
                cMail = jsonObject.getString(MAIL);
                cLogin = jsonObject.getString(USERNAME);
                System.out.println("mail : "+ cMail);
                System.out.println("login : "+ cLogin);
                /*if(!cMail.equals(m) && !cLogin.equals(l)){
                    present = false;
                }*/
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void envoyerInscription(View v){

        recupererVue(v);

        n = nom.getText().toString();
        p = prenom.getText().toString();
        l = ilogin.getText().toString();
        m = mail.getText().toString();
        mp = iMdp.getText().toString();
        Cmp = iCMdp.getText().toString();

        if(n != null && !n.isEmpty())
            if(p != null && !p.isEmpty())
                if(m != null && !m.isEmpty())
                    if(mp != null && !mp.isEmpty())
                        if(Cmp != null && !Cmp.isEmpty())
                            error = false;
                        else
                            error = true;
                    else
                        error = true;
                else
                    error = true;
            else
                error = true;
        else
            error = true;


        if(!Cmp.equals(mp))
            errorMdp = true;
        else
            errorMdp = false;

        if(error)
            s = "Erreur,les champs doivent être remplis";

        else if (errorMdp)
            s = "Mot de passe différents";
        else
            s = "Enregistré";

        defaut.setText(s);
        //searchExistant();
        EnvoyerData();


    }

    public void EnvoyerData(){
        btn_enregistrer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, iURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("login",l);
                        parameters.put("pwd",mp);
                        parameters.put("email",m);

                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });


    }


    //==============================================================================================
    //====================================CREATION EVENEMENT========================================
    //==============================================================================================

    public void estPublique(View v){
        EditText motDePasse = (EditText)findViewById(R.id.motDePasse);
        motDePasse.setVisibility(View.INVISIBLE);
    }

    public void estPrive(View v){
        EditText motDePasse = (EditText)findViewById(R.id.motDePasse);
        motDePasse.setVisibility(View.VISIBLE);
    }




    //==============================================================================================
    //====================================??????????????????========================================
    //==============================================================================================

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

    //==============================================================================================
    //=========================================On Create============================================
    //==============================================================================================


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connexion = (Button) findViewById(R.id.button);
        inscription = (Button) findViewById(R.id.button2);

        lv = (TextView) findViewById(R.id.lv);
        tLogin = (TextView) findViewById(R.id.tLogin);
        tMdp =(TextView) findViewById(R.id.tMdp);
        cError = (TextView) findViewById(R.id.Error);

        cError.setVisibility(View.INVISIBLE);


        getJSON(JSON_URL);

        connexion.setOnClickListener(this);
        inscription.setOnClickListener(this);
    }
    //==============================================================================================
    //=========================================On Click=============================================
    //==============================================================================================
    @Override
    public void onClick(View v) {
        if(v == connexion){
            login = tLogin.getText().toString();
            mdp = tMdp.getText().toString();

            System.out.println("login : " + login);
            System.out.println("myjson : " + myJson);

            extractJson();
            searchLogin();

            if(granted) {
                System.out.println("Connexion réussie");
                //Toast.makeText(this, "Connexion réussie!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, filActu.class);
                intent.putExtra(MY_JSON, lv.getText().toString());
                startActivity(intent);
            }
            else {
                //Toast.makeText(this, "Connexion échouée!", Toast.LENGTH_SHORT).show();
                cError.setText("Login/mdp incorrect(s)");
                cError.setVisibility(View.VISIBLE);
            }
        }
        else if(v == inscription){
            VersInscription(v);
        }
    }
}
