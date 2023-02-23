package com.newseoul.bookfinder.auth.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newseoul.bookfinder.model.BookRental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserAccount {
	@Id
	@NotBlank
	private String username;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	@Transient
	private String confirmPassword;
	@NotBlank
	private String mobileCarrier;
	@NotBlank
	private String phoneNumber;
	private String postalCode;
	private String address;
	private String detailAddress;
	
	@OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<BookRental> bookRentalList = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_account_role", joinColumns = @JoinColumn(name = "username"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public void addRoles(Collection<Role> roles) {
		this.roles.addAll(roles);
	}
	
	// 도서 대출 추가
	public void addBookRental(BookRental bookRental) {
		bookRental.setUserAccount(this);
		bookRentalList.add(bookRental);
	}
}
