package be.ti.groupe2.projetintegration;

/**
 * Created by martin on 30-09-15.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;




public class CreationEvenement extends Activity {
    // La chaîne de caractères de champVide
    private final String champVide = "Vous devez compléter tous les champs";

    Button suivant = null;
    Button raz = null;

    TextView messageError =null;

    EditText nomEvent = null;
    EditText motDePasse = null;
    EditText nombreEtape = null;

    RadioGroup radioGroup = null;
    RadioButton radioPublic =null;
    RadioButton radioPrivee =null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_evenement);

        // On récupère toutes les vues dont on a besoin
        suivant = (Button)findViewById(R.id.suivant);
        raz = (Button)findViewById(R.id.raz);

        nomEvent = (EditText)findViewById(R.id.nomEvent);
        motDePasse = (EditText)findViewById(R.id.motDePasse);
        nombreEtape = (EditText)findViewById(R.id.nombreEtape);

        messageError =(TextView)findViewById(R.id.messageError);

        radioGroup = (RadioGroup)findViewById(R.id.group);
        radioPublic = (RadioButton)findViewById(R.id.radio1);
        radioPrivee = (RadioButton)findViewById(R.id.radio2);

        // On attribue un listener adapté aux vues qui en ont besoin
        suivant.setOnClickListener(suivantListener);
        raz.setOnClickListener(razListener);
        radioGroup.setOnClickListener(radioListener);
        radioPublic.setOnClickListener(radioListener);
        radioPrivee.setOnClickListener(radioListener);
    }

    // Uniquement pour le bouton "suivant"
    private OnClickListener suivantListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(nomEvent.getText().length() == 0){
                messageError.setText(champVide);
            }
            else if(nombreEtape.getText().length() == 0){
                messageError.setText(champVide);
            }
            else{
                messageError.setText("ca fonctionne plus qu'a trouvé cmt chnagé d'activity");
                //Changer d'activity mais comment ?
            }
        }
    };

    // Listener du bouton de remise à zéro
    private OnClickListener razListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            nomEvent.getText().clear();
            motDePasse.getText().clear();
            nombreEtape.getText().clear();
        }
    };

    private OnClickListener radioListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean publique = false;
            // On récupère l'identifiant du bouton qui est coché
            int id = radioGroup.getCheckedRadioButtonId();
            System.out.println(id);

            switch (v.getId()){

                case R.id.radio1: publique = true; break;
                case R.id.radio2: publique = false; break;
            }

            if(publique){
                motDePasse.setVisibility(View.INVISIBLE);
            }
            else{
                motDePasse.setVisibility(View.VISIBLE);
            }
        }
    };

}