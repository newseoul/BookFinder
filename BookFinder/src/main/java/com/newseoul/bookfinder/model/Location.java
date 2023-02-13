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
public class Location {
	@Id
	@GeneratedValue(generator = "location_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "location_seq", allocationSize = 1, sequenceName = "LOCATION_SEQ")
	private int locationId;
	private String locationName;
	private String locationDetail;
}
