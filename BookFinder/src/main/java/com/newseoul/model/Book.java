package com.newseoul.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
	@Id
	private int bookId;
	private String author;
	private Date publicationDate;
	private String publisher;
	private String bookDetail;
	private String locationMemo;
	private String filename;
	private int displayStatus;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id")
	private Location location;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
}
