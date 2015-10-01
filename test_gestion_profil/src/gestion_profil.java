
public class gestion_profil {
	
	
	

    public boolean verif_champs(String pseudo, String name, String first_name, String pass, String new_pass, String confirm, user[] t_users, int nb_users, int id){
        boolean ok;
        ok=verif_pseudo(id, pseudo,t_users, nb_users);
        if (ok){
            ok=verif_name(name);
            if (ok){
                ok=verif_first_name(first_name);
                if (ok){
                    ok=verif_pass(id,t_users,pass,new_pass,confirm);
                }
                else{
                	ok=false;
                }
            }
            else{
            	ok=false;
            }
        }
        else{
        	ok=false;
        }

        return ok;

    }

    public boolean verif_pseudo(int id,String pseudo, user[] t_users, int nb_users){
        //int nbUsers=20;
        boolean ok=true;
        if(pseudo == t_users[id-1].pseudo){
        	System.out.println("Pseudo inchangé");
        	return true;
        }
        else{
	        String pseudo2;

	        pseudo=pseudo.toLowerCase();                                //Je met les pseudo en lower case pour les comparer
	        for(int i =0; i<nb_users; i++){
	        	System.out.print(i);  
	            pseudo2=t_users[i].pseudo;
	            pseudo2=pseudo2.toLowerCase();                          //Je compare chaque pseudo du tableau de users
	            if (pseudo == pseudo2){
	                System.out.print("Pseudo déjà utilisé");            //S'il y a un doublon, c'est pas ok
	                ok=false;
	            }
	        }
	        if (ok) {                                                     //je retourne si c'est ok ou pas
	            return true;
	        }
	        else{
	            return false;
	        }
        }
    }

    public boolean verif_name(String name){
        boolean ok=true;
        if (name.length()<3){
            ok=false;
            System.out.print("Nom trop court");
        }

        if (ok) {                                                     //je retourne si c'est ok ou pas
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

        if (ok) {                                                     //je retourne si c'est ok ou pas
            return true;
        }
        else{
            return false;
        }

    }

    public boolean verif_pass(int id, user[] t_users, String pass, String new_pass, String confirm){
    	boolean ok=true;
        //T_user t_users;
        //requete pour trouver le user avec le pseudo
        String mdp=t_users[id-1].pass;
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
        return ok;
    }
    

}
