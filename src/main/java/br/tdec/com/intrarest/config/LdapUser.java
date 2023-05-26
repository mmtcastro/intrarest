package br.tdec.com.intrarest.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

public class LdapUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String commonName;
	private List<String> groups;
	private UserDetails ldapUserDetails;

	public LdapUser(LdapUserDetails ldapUserDetails) {
		this.ldapUserDetails = ldapUserDetails;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return ldapUserDetails.getAuthorities();
	}

	@Override
	public String getPassword() {
		return ldapUserDetails.getPassword();
	}

	@Override
	public String getUsername() {
		return ldapUserDetails.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return ldapUserDetails.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return ldapUserDetails.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return ldapUserDetails.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return ldapUserDetails.isEnabled();
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

}
