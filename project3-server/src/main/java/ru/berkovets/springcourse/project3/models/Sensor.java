package ru.berkovets.springcourse.project3.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Sensor")
public class Sensor {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	@NotNull(message = "Name should not be null")
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 3, max = 30, message = "Name lenght should be betwen 3 and 30 characters")
	private String name;

	@OneToMany(mappedBy = "sensor")
	private List<Measurement> measurements;

	public Sensor() {

	}

	public Sensor(String name, List<Measurement> measurements) {
		this.name = name;
		this.measurements = measurements;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

}
