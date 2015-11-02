package be.ti.groupe2.projetintegration;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GestionEvenement extends Activity implements View.OnClickListener {

    Button accueil;
    Button event;
    Button profil;
    Button creaevent;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_evenement);

        accueil = (Button) findViewById(R.id.buttonAccueil);
        event = (Button) findViewById(R.id.buttonEvent);
        profil = (Button) findViewById(R.id.buttonProfil);
        creaevent = (Button) findViewById(R.id.buttonCreation);

        accueil.setOnClickListener(this);
        event.setOnClickListener(this);
        profil.setOnClickListener(this);
        creaevent.setOnClickListener(this);




    }
    public void onClick(View v) {
        if (v == event) {
            setContentView(R.layout.gestion_evenement);
        }
        if (v == profil) {
            setContentView(R.layout.activity_gestion_du_profil);
        }
        if (v == accueil) {
            setContentView(R.layout.accueil);
        }
        if (v == creaevent) {
            setContentView(R.layout.creation_evenement);
        }

    }
}
