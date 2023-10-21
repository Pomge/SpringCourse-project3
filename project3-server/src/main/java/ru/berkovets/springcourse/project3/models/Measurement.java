package ru.berkovets.springcourse.project3.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "measurement")
public class Measurement {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@NotNull(message = "\'sensor\' should not be null")
	@JoinColumn(name = "sensor_id", referencedColumnName = "id")
	private Sensor sensor;

	@Column(name = "temperature")
	@NotNull(message = "\'value\' should not be empty")
	@Min(value = -100, message = "\'value\' should be greater than -100")
	@Max(value = 100, message = "\'value\' should be less than 100")
	private Float value;

	@Column(name = "raining")
	@NotNull(message = "\'raining\' should not be null")
	private Boolean raining;

	@Column(name = "writen_at")
	private LocalDateTime writenAt;

	public Measurement() {

	}

	public Measurement(Sensor sensor, Float value, Boolean raining) {
		this.sensor = sensor;
		this.value = value;
		this.raining = raining;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Boolean getRaining() {
		return raining;
	}

	public void setRaining(Boolean raining) {
		this.raining = raining;
	}

	public LocalDateTime getWritenAt() {
		return writenAt;
	}

	public void setWritenAt(LocalDateTime writenAt) {
		this.writenAt = writenAt;
	}

}
