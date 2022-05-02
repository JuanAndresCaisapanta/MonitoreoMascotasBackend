package com.Pet_Monitoring.Entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String manufacturer;

	@NotNull
	private String batch;

	@NotNull
	private String applicator;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date production_date;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date expiration_date;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date application_date;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date create_date;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date update_date;

	@ManyToOne
	@JoinColumn(name = "typeMedicine_id")
	private TypeMedicine typeMedicine;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

}
