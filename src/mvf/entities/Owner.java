package mvf.entities;

public class Owner extends User {

	public Owner() {
		super();
		this.setStatus("O");
	}

	public Owner(String account, String password) {
		super(account, password, "O");
		// TODO Auto-generated constructor stub
	}

}
