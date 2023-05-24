package br.tdec.com.intrarest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class LdapSecurityConfig {

	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

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
	    
	    @Bean
	    public BaseLdapPathContextSource contextSource() {
	    	LdapContextSource bean = new LdapContextSource();
	        bean.setUrl("ldap://10.10.10.10:389");
	        bean.setBase("DC=myDomaine,DC=com");
	        //instead of this i want to put here the username and password provided by the user
	        bean.setUserDn("myDomaine\\username");
	        bean.setPassword("password");
	        bean.setPooled(true);
	        bean.setReferral("follow");
	        bean.afterPropertiesSet();
	        return bean;
	    }



	  @Bean
	  public LdapTemplate ldapTemplate() {
	      LdapTemplate template = new LdapTemplate(contextSource());

	      return template;
	  }
}
