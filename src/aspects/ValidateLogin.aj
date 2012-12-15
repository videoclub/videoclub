package aspects;

public aspect ValidateLogin {
	
	/**
	 * To pointcut leitourgei os endiamesos metaksi joinpoint kai advice.
	 * Ena pointcut apoteleite apo 1-* joinpoints. O sindiasmos twn joinpoints
	 * ginete mesw twn telestwn || , && , ! 'h mesw wildcards(*)
	 */
	pointcut callCheckLogin(String user):
		call(String login.ManageLogin.checkLogin(String)) //joinpoint (stin ousia to signature mias methodou). To joinpoint edo einai typou - call
		&& args(user);
	/**
	 * Advice typou - after. O kodikas dld pou tha ektelestei meta(after) thn klhsh(call) tis methodou(joinpoint).
	 * H prosbasi, enos advice, sti timi pou egine return kata ti klisi tis methodou sto programma, apoktatai meso parametrwn.
	 * Arxika i parametros(String user) tou pointcut "sigxronizetai" me to argument(args(user)) tou joinpoint kai parakato,
	 * h parametros(String u) tou advice "sigxronizetai" me to argument(u) tou pointcut kai etsi mporoume kai to tiponoume.
	 */
	after(String u) returning: callCheckLogin(u){ //tha mporousa na grapso k edo user anti gia u, alla to egrapsa etsi giati den mas ipoxreonei kaneis
		System.out.println("code after checkLogin(String) returned: " + u); //na ksanagrapsoume user, opos mpike arxika.
	}

}
