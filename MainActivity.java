package be.ti.groupe2.projetintegration;



import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //==============================================================================================
    //====================================??????????????????========================================
    //==============================================================================================

    public void VersProfil(View view) {setContentView(R.layout.activity_gestion_du_profil);}
    public void VersEvent(View view) {
        setContentView(R.layout.creation_evenement);
    }
    public void VersInscription(View view) {
        setContentView(R.layout.inscription);
    }
    //==============================================================================================
    //========================================CONNEXION============================================
    //==============================================================================================

    public static final String strURL = "http://projet_groupe2.hebfree.org/connexion.php";
    public TextView txt;
    public void Connexion(Bundle savedInstanceState) {

        LinearLayout rootLayout = new LinearLayout(getApplicationContext());
        txt = new TextView(getApplicationContext());
        rootLayout.addView(txt);
        setContentView(rootLayout);

        // Définir le texte et appeler la fonction de connexion.
        txt.setText("Connexion...");
        // Appeler la méthode pour récupérer les données JSON
        txt.setText(getServerData(strURL));
    }

    private String getServerData(String returnString) {
        InputStream is = null;
        String result = "";

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("userLogin", "B"));

        // Envoie de la commande http
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(strURL);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }

        // Convertion de la requête en string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }
        // Parse les données JSON
        try {
            JSONArray jArray = new JSONArray(result);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                // Affichage ID_ville et Nom_ville dans le LogCat
                Log.i("log_tag", "ID_client: " + json_data.getInt("userId") +
                                ", Nom_client: " + json_data.getString("userLogin")
                );
                // Résultats de la requête
                returnString += "\n\t" + jArray.getJSONObject(i);
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
        return returnString;
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
}
