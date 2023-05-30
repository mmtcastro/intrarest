package br.tdec.com.intrarest.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

@Configuration
public class LdapSecurityConfig {

	// https://stackoverflow.com/questions/45260380/spring-security-ldap-authentication-userdn-and-password-from-login-form
	// https://github.com/eugenp/tutorials/tree/master/spring-security-modules/spring-security-ldap

	@Bean
	public BaseLdapPathContextSource contextSource() {

		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl("ldap://lexapro.tdec.com.br:389");
		contextSource.setBase("O=TDec");
		contextSource.setUserDn("mcastro");
		contextSource.setPassword("Hodge$404");
		contextSource.setPooled(true);
		contextSource.setReferral("follow");
		contextSource.afterPropertiesSet();

		System.out.println("contextSource -> " + contextSource.getContext("CN=Marcelo Castro, O=TDec", "Hodge$404"));

		return contextSource;

	}

	@Bean
	LdapAuthoritiesPopulator authorities(BaseLdapPathContextSource contextSource) {
		String groupSearchBase = "";
		DefaultLdapAuthoritiesPopulator authorities = new DefaultLdapAuthoritiesPopulator(contextSource,
				groupSearchBase);
		// authorities.setGroupSearchFilter("(member={0})");
		return authorities;
	}

	@Bean
	AuthenticationManager authenticationManager(BaseLdapPathContextSource contextSource,
			LdapAuthoritiesPopulator authorities) {
		LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(contextSource);
		factory.setUserSearchBase("O=TDec");
		factory.setUserSearchFilter("(uid={0})");
		return factory.createAuthenticationManager();
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		LdapTemplate ldapTemplate = new LdapTemplate(contextSource());
		return ldapTemplate;
	}

	public static String digestSHA(String input) {
		byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8));
		return new String(encodedBytes, StandardCharsets.UTF_8);
	}

}
