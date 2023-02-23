package com.newseoul.bookfinder.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
	@Id
	@GeneratedValue(generator = "book_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "book_seq", allocationSize = 1, sequenceName = "BOOK_SEQ")
	private int bookId;
	private String bookName;
	private String author;
	private Date publicationDate;
	private String publisher;
	private String bookDetail;
	private String locationMemo;
	private String filename;
	private int displayStatus;
	// 대여 상태(대여가능:rentable, 대여중:on_rental, 연체중:overdue)
	private String rentalStatus;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<BookRental> bookRentalList = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id")
	private Location location;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	
	public String getPublicationDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(this.publicationDate);
	}
	
	public void setPublicationDate(String publicationDate) throws ParseException {
		this.publicationDate = new SimpleDateFormat("yyyy-MM-dd").parse(publicationDate);
	}
	
	// 도서 대출 추가
	public void addBookRental(BookRental bookRental) {
		bookRental.setBook(this);
		bookRentalList.add(bookRental);
	}
}
