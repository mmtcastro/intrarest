package br.tdec.com.intrarest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

@Configuration
public class LdapSecurityConfig {

	// https://stackoverflow.com/questions/45260380/spring-security-ldap-authentication-userdn-and-password-from-login-form
	// https://github.com/eugenp/tutorials/tree/master/spring-security-modules/spring-security-ldap
	@Bean
	public BaseLdapPathContextSource contextSource() {

		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl("ldap://lexapro.tdec.com.br:389");
		contextSource.setBase("O=TDec");
		// instead of this i want to put here the username and password provided by the
		// user
		contextSource.setUserDn("mcastro");
		contextSource.setPassword("Hodge$404");
		contextSource.setPooled(true);
		contextSource.setReferral("follow");
		contextSource.afterPropertiesSet();

		LdapAuthenticationProvider authenticationProvider = new LdapAuthenticationProvider(
				new BindAuthenticator(contextSource), new DefaultLdapAuthoritiesPopulator(contextSource, "O=TDec"));
		authenticationProvider.setUserDetailsContextMapper(new LdapUserDetailsMapper());

		System.out.println("--> Isto eh " + contextSource.getPassword());

		System.out.println("contextSource -> " + contextSource.getContext("CN=Marcelo Castro, O=TDec", "Hodge$404"));

		return contextSource;
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		LdapTemplate template = new LdapTemplate(contextSource());
		return template;
	}

}
