package br.tdec.com.intrarest.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.directory.Attributes;

import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

public class CustomUserDetailsContextMapper extends LdapUserDetailsMapper {

	private LdapUser ldapUser = null;
	private String commonName;
	private List<String> groups = new LinkedList<String>();

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		Attributes attributes = ctx.getAttributes();
		UserDetails ldapUserDetails;
		ldapUserDetails = (UserDetails) super.mapUserFromContext(ctx, username, authorities);
		try {
			commonName = attributes.get("cn").get().toString();

			Arrays.stream(ctx.getObjectAttributes("memberOf")).iterator().forEachRemaining(m -> {
				groups.add(m.toString());
			});

		} catch (NamingException | javax.naming.NamingException e) {
			e.printStackTrace();
		}
		ldapUser = new LdapUser((LdapUserDetails) ldapUserDetails);
		ldapUser.setCommonName(commonName);
		ldapUser.setGroups(groups);
		return ldapUser;
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {

	}
}
