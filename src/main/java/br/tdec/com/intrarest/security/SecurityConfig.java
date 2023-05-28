package br.tdec.com.intrarest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vaadin.flow.spring.security.VaadinWebSecurity;

import br.tdec.com.intrarest.views.LoginView;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

	@Autowired
	AuthenticationManager ldapAuthenticationManager;

	@Autowired
	LdapAuthoritiesPopulator ldapAuthoritiesPopulator;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Delegating the responsibility of general configurations
		// of http security to the super class. It's configuring
		// the followings: Vaadin's CSRF protection by ignoring
		// framework's internal requests, default request cache,
		// ignoring public views annotated with @AnonymousAllowed,
		// restricting access to other views/endpoints, and enabling
		// ViewAccessChecker authorization.
		// You can add any possible extra configurations of your own
		// here (the following is just an example):

		// http.rememberMe().alwaysRemember(false);

		// Configure your static resources with public access before calling
		// super.configure(HttpSecurity) as it adds final anyRequest matcher
		// http.authorizeHttpRequests().requestMatchers(new
		// AntPathRequestMatcher("/public/**")).permitAll();

//		    http.authenticationProvider(
//		            new LdapAuthenticationProvider(authenticator, ldapAuthoritiesPopulator));

		http.authenticationManager(ldapAuthenticationManager).authorizeHttpRequests()
				.requestMatchers(new AntPathRequestMatcher("/public/**")).permitAll();

		super.configure(http);

		// This is important to register your login view to the
		// view access checker mechanism:
		setLoginView(http, LoginView.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Customize your WebSecurity configuration.
		super.configure(web);
	}

//	/**
//	 * Demo UserDetailsManager which only provides two hardcoded in memory users and
//	 * their roles. NOTE: This shouldn't be used in real world applications.
//	 */
//	@Bean
//	public UserDetailsService users() {
//		UserDetails user = User.builder().username("user")
//				// password = password with this hash, don't tell anybody :-)
//				.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW").roles("USER").build();
//		UserDetails admin = User.builder().username("admin")
//				.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW").roles("USER", "ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(user, admin); // <5>
//	}

}
