package aspects;

public aspect ValidateLogin {
	
	pointcut callCheckLogin(String user):
		call(String login.ManageLogin.checkLogin(String))
		&& args(user);
	
	after(String u) returning: callCheckLogin(u){
		System.out.println("code after checkLogin(String) returned: " + u);
	}

}
