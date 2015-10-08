package com.test.marie.projetintegration;

    import android.app.Activity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;

    public class Inscription extends Activity {

        TextView defaut = null;
        EditText nom = null;
        EditText prenom = null;
        EditText mail = null;
        EditText mdp = null;
        EditText Cmdp = null;
        Button enregistrer = null;
        String erreur = null;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            //afficher GUI
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //récupération des vues qu'on a besoin
            defaut = (TextView)findViewById(R.id.defaut);
            nom = (EditText)findViewById(R.id.nom);
            prenom = (EditText)findViewById(R.id.prenom);
            mail = (EditText)findViewById(R.id.email);
            mdp = (EditText)findViewById(R.id.mdp);
            Cmdp = (EditText)findViewById(R.id.Cmdp);
            enregistrer = (Button)findViewById(R.id.enregistrer);

            enregistrer.setOnClickListener(envoyerListener);
        }

        private View.OnClickListener envoyerListener = new View.OnClickListener() {
            private String s = null;
            private String n = null;
            private String p = null;
            private String m = null;
            private String mp = null;
            private String Cmp = null;
            @Override
            public void onClick(View v) {
                n = nom.getText().toString();
                p = prenom.getText().toString();
                m = mail.getText().toString();
                mp = mdp.getText().toString();
                Cmp = Cmdp.getText().toString();
                boolean error = false;
                boolean errorMdp = false;

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
                    s = "Erreur les champs doivent être rempli";

                else if (errorMdp)
                    s = "Mot de passe différents";
                else
                    s = "enregistré";

                defaut.setText(s);
            }
        };

    }





