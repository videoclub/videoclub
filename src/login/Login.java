package login;

public class Login {
	
	private static ManageLogin ml;

	public static void main(String[] args) {
		ml = new ManageLogin();
		ml.checkLogin("greg");
	}

}
