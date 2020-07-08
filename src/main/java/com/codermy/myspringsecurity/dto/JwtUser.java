package com.codermy.myspringsecurity.dto;


import com.codermy.myspringsecurity.eneity.TbPermission;
import com.codermy.myspringsecurity.eneity.TbUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
public class JwtUser extends TbUser implements UserDetails {

	private TbUser user;

	@JsonIgnore
	private List<GrantedAuthority> authorities;

	public List<String> getRoles() {
		return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return user.getUserName();
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {

		return user.getStatus() != Status.LOCKED;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}


}
