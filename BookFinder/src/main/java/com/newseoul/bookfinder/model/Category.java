package com.newseoul.bookfinder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
	@Id
	@GeneratedValue(generator = "category_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "category_seq", allocationSize = 1, sequenceName = "CATEGORY_SEQ")
	private int categoryId;
	private int categoryType;
	private String categoryName;
}
