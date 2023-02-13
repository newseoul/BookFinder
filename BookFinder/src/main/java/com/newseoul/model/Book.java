package com.newseoul.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	private int bookId;
	private String author;
	private Date publicationDate;
	private String publisher;
	private String bookDetail;
	private int locationId;
	private String locationMemo;
	private int categoryId;
	private String filename;
	private int displayStatus;
	
	@OneToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;
}
