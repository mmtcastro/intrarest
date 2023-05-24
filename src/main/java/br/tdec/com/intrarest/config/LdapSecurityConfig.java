package br.tdec.com.intrarest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapSecurityConfig {

//	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//	      http
//	       .authorizeHttpRequests()
//	       .anyRequest().fullyAuthenticated()
//	       .and()
//	       .formLogin();
//	      http.authenticationProvider(ldapAuthenticationProvider());
//	      return http.build();
//	    }
//
//	    @Bean
//	    LdapAuthenticationProvider ldapAuthenticationProvider() {
//	       return new LdapAuthenticationProvider(authenticator());
//	    }
//	    
//	    @Bean
//	    BindAuthenticator authenticator() {
//
//	       FilterBasedLdapUserSearch search = new FilterBasedLdapUserSearch("ou=groups", "(uid={0})", contextSource());
//	       BindAuthenticator authenticator = new BindAuthenticator(contextSource());
//	       authenticator.setUserSearch(search);
//	       return authenticator;
//	    }
//
//	    @Bean
//	    public DefaultSpringSecurityContextSource contextSource() {
//	       DefaultSpringSecurityContextSource dsCtx = new DefaultSpringSecurityContextSource("ldap://lexapro.tdec.com.br:389/o=TDec");
//	       dsCtx.setUserDn("(cn={0})");
//	       return dsCtx;
//	    }
//	    }

	// https://stackoverflow.com/questions/45260380/spring-security-ldap-authentication-userdn-and-password-from-login-form

	@Bean
	public BaseLdapPathContextSource contextSource() {

		LdapContextSource contextSource = new LdapContextSource();
//	    	LdapContextSource bean = new LdapContextSource();
		contextSource.setUrl("ldap://lexapro.tdec.com.br:389");
		contextSource.setBase("O=TDec");
		// instead of this i want to put here the username and password provided by the
		// user
		contextSource.setUserDn("mcastro");
		contextSource.setPassword("Hodge$404");
		contextSource.setPooled(true);
		contextSource.setReferral("follow");
		contextSource.afterPropertiesSet();

		System.out.println("--> Isto eh " + contextSource.getPassword());

		System.out.println("contextSource -> " + contextSource.getContext("CN=Marcelo Castro, O=TDec", "Hodge$404"));

		return contextSource;
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		LdapTemplate template = new LdapTemplate(contextSource());

		return template;
	}

//	public void configure(final AuthenticationManagerBuilder auth) throws Exception {
//		auth.ldapAuthentication().userSearchFilter("sAMAccountName={0}").contextSource()
//				.url("ldap://lexapro.tdec.com.br:389)").managerDn("mcastro").managerPassword("Hodge$404");
//		System.out.println("Auth eh " + auth);
//	}

}
