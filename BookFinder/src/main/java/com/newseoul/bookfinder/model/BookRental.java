package com.newseoul.bookfinder.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.newseoul.bookfinder.auth.model.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookRental {
	@Id
	@GeneratedValue(generator = "book_rental_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "book_rental_seq", allocationSize = 1, sequenceName = "BOOK_RENTAL_SEQ")
	private int rentalId;
	private String rentalStatus;
	private Date rentalDate;
	private Date returnDueDate;
	private Date returnDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id")
	private Book book;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username")
	private UserAccount userAccount;
	
	public String getRentalDate() {
		if(this.rentalDate != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(this.rentalDate);			
		}
		return "";
	}
	public String getReturnDueDate() {
		if(this.returnDueDate != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(this.returnDueDate);			
		}
		return "";
	}
	public String getReturnDate() {
		if(this.returnDate != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(this.returnDate);			
		}
		return "";
	}
	
	public void setRentalDate(String rentalDate) throws ParseException {
		this.rentalDate = new SimpleDateFormat("yyyy-MM-dd").parse(rentalDate);
	}
	public void setReturnDueDate(String returnDueDate) throws ParseException {
		this.returnDueDate = new SimpleDateFormat("yyyy-MM-dd").parse(returnDueDate);
	}
	public void setReturnDate(String returnDate) throws ParseException {
		this.rentalDate = new SimpleDateFormat("yyyy-MM-dd").parse(returnDate);
	}
}
