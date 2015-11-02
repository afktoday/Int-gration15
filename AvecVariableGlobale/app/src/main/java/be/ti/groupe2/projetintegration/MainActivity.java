package be.ti.groupe2.projetintegration;



import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONArray;

public class MainActivity extends Activity implements View.OnClickListener {

        Button connexion;
        Button inscription;
        TextView tLogin;
        TextView tMdp;
        TextView cError;
        String login;
        String mdp;
        Boolean granted = false;
        String resu;

        public static  TextView lv = null;
        public static final String myJson = null;
        public static final String MY_JSON ="MY_JSON";
        public static final String JSON_URL = "http://projet_groupe2.hebfree.org/Clients.php";
        private static final String ID = "userID";

        VariableGlobale context;


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



            resu = functions.getJSON(JSON_URL, lv, myJson);
            context.setListEvent(resu);

            connexion.setOnClickListener(this);
            inscription.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == connexion){
                login = tLogin.getText().toString();
                mdp = tMdp.getText().toString();

                System.out.println("login : " + login);
                System.out.println("myjson : " + myJson);


                JSONArray result = functions.extractJson(myJson);

                functions.searchLogin(result, granted, login, mdp);

                granted =true;

                if(granted) {
                    System.out.println("Connexion réussie");
                    Intent intent = new Intent(this, filActu.class);
                    intent.putExtra(MY_JSON, lv.getText().toString());
                    startActivity(intent);
                }
                else {
                    cError.setText("Login/mdp incorrect(s)");
                    cError.setVisibility(View.VISIBLE);
                }
            }
            else if(v == inscription){
              //  functions.VersInscription(v);
            }
        }
    }