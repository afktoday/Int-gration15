package com.example.romain.cyoe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Gestion_du_profil extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_du_profil);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gestion_du_profil, menu);
        return true;
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

    public void verif_champs(String pseudo, String name, String first_name, String pass, String new_pass, String confirm){
        boolean ok;
        ok=verif_pseudo(pseudo);
        if ok{
            ok=verif_name(name);
            if ok{
                ok=verif_first_name(first_name);
                if ok{
                    ok=verif_pass(pseudo,pass,new_pass,confirm);
                }
            }
        }



    }

    public boolean verif_pseudo(String pseudo){
        int nbUsers=20;
        T_user[] t_users;                                           //Doit être de type tableau de user
        String pseudo2;
        boolean ok=true;
        pseudo=pseudo.toLowerCase();                                //Je met les pseudo en lower case pour les comparer
        for(int i =0; i<nbUsers; i++){
            pseudo2=t_users[i].pseudo;
            pseudo2=pseudo2.toLowerCase();                          //Je compare chaque pseudo du tableau de users
            if (pseudo == pseudo2){
                System.out.print("Pseudo déjà utilisé");            //S'il y a un doublon, c'est pas ok
                ok=false;
            }
        }
        if ok {                                                     //je retourne si c'est ok ou pas
            return true;
        }
        else{
            return false;
        }
    }

    public boolean verif_name(String name){
        boolean ok=true;
        if (name.length()<3){
            ok=false;
            System.out.print("Nom trop court");
        }

        if ok {                                                     //je retourne si c'est ok ou pas
            return true;
        }
        else{
            return false;
        }

    }

    public boolean verif_first_name(String first_name){
        boolean ok=true;
        if (first_name.length()<3){
            ok=false;
            System.out.print("Prénom trop court");
        }

        if ok {                                                     //je retourne si c'est ok ou pas
            return true;
        }
        else{
            return false;
        }

    }

    public boolean verif_pass(String pseudo, String pass, String new_pass, String confirm){
        T_user t_users;
        //requete pour trouver le user avec le pseudo
        mdp=user.pseudo;
        if(mdp==pass){
            if(new_pass!=confirm){
                ok=false;
                System.out.print("Erreur mot de passe et confirmation différents");
            }
        }
        else{
            ok=false;
            System.out.print("Mauvais mot de passe");
        }
    }

}
