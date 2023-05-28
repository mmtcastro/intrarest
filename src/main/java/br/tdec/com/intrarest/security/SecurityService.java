package br.tdec.com.intrarest.security;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.security.AuthenticationContext;

@Component
public class SecurityService {

	private final AuthenticationContext authenticationContext;

	public SecurityService(AuthenticationContext authenticationContext) {
		this.authenticationContext = authenticationContext;
	}

	public void logout() {
		authenticationContext.logout();
	}

}
