import static org.junit.Assert.*;

import org.junit.Test;

public class test_verif_champ_gestion_profil {

	user u=new user(2,"voldemort","jedusor","tom","orcrux");
	int nb_users =3;
	user T_u[]=new user[nb_users];
	
	
	
	
	gestion_profil gest= new gestion_profil();
	
	@Test
	public void test() {
		T_u[0]= new user(1,"rastar","Blan","Romain","lloyd");
		T_u[1]= new user(2,"voldemort","jedusor","tom","orcrux");
		T_u[2]= new user(3,"mh93","Heinen","Marie","mh93");
		//assertEquals(true, gest.verif_champs(u.pseudo,u.name,u.firstname,u.pass));
		assertEquals(true, gest.verif_pseudo(u.id,u.pseudo, T_u, nb_users));
		assertEquals(true, gest.verif_first_name(u.firstname));
		assertEquals(true, gest.verif_name(u.name));
		assertEquals(true, gest.verif_pass(u.id, T_u, u.pass, "nouveau pass", "nouveau pass"));
		assertEquals(true, gest.verif_champs(u.pseudo,  u.name, u.firstname, u.pass, "ok", "ok", T_u, nb_users, u.id));
		
		assertEquals(true, gest.verif_champs(u.pseudo, u.name, u.firstname, u.pass, "ok", "ok", T_u, nb_users, u.id));
		
	}
	
	

}
