package model;

import bo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

public class LoginBean implements Serializable {
	
	private static final String FORM_FIELD_LOGIN = "form-login";
	private static final String FORM_FIELD_PWD = "form-pwd";
	public static final String ATT_AUTH_SESSION = "isConnected";

	private String login;
	private String pwd;
	private String authResult;
	
	public LoginBean() {}
	
	public void authenticate( HttpServletRequest request ) {
		
		login = request.getParameter( FORM_FIELD_LOGIN );
		pwd = request.getParameter( FORM_FIELD_PWD );
		HttpSession session = request.getSession( true );

		//TODO Authentification compte
		if ( "sega".equals( login ) && "123".equals( pwd )) {
			//TODO récupération et incrémentation du nombre de connexions
			session.setAttribute( ATT_AUTH_SESSION, new User(1, "testL", "testP", "testU", 1) );
			authResult = "Authentification réussie : Bienvenue "+ login;
		} else {
			session.setAttribute( ATT_AUTH_SESSION, null);
			authResult = "Authentification échouée";
		}
	}
	
	public boolean isConnected( HttpServletRequest request ) {
		HttpSession session = request.getSession();
		User connectedUser = (User)session.getAttribute( ATT_AUTH_SESSION );
		return connectedUser != null;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin( String login ) {
		this.login = login;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd( String pwd ) {
		this.pwd = pwd;
	}
	
	public String getAuthResult() {
		return authResult;
	}
	
	public void setAuthResult(String authResult) {
		this.authResult = authResult;
	}
}
