/**
 * 
 */

/**
 * @author Romain
 *
 */
public class user {
	int id;
	String pseudo;
	String name;
	String firstname;
	String pass;
	
	public user(int id, String pseudo, String name, String firstname, String pass){
		this.id=id;
		this.pseudo=pseudo;
		this.name=name;
		this.firstname=firstname;
		this.pass=pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	

}
