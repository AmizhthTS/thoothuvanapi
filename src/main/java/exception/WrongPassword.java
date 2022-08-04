package exception;

import org.springframework.security.core.AuthenticationException;

public class WrongPassword extends AuthenticationException{

	public WrongPassword(String msg) {
		super(msg);
	}

}
