package br.tdec.com.intrarest.security;

import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.security.AuthenticationContext;

@Component
public class SecurityService {

	private final AuthenticationContext authenticationContext;
	private final BaseLdapPathContextSource contextSource; // tentativa de passar o LdapContext pra cá como UserService.

	public SecurityService(AuthenticationContext authenticationContext, BaseLdapPathContextSource contextSource) {
		this.authenticationContext = authenticationContext;
		this.contextSource = contextSource;
	}

	public void logout() {
		authenticationContext.logout();
	}

}
