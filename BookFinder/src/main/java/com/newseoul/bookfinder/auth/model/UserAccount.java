package com.newseoul.bookfinder.auth.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name="USER_ACCOUNT")
public class UserAccount {
	@Id
	private String username;
	private String email;
	private String name;
	private String password;
	private String mobileCarrier;
	private String phoneNumber;
	private String postalCode;
	private String address;
	private String detailAddress;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_account_role", joinColumns = @JoinColumn(name = "username"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public void addRoles(Collection<Role> roles) {
		this.roles.addAll(roles);
	}
}
