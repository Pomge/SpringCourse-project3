package ru.berkovets.springcourse.project3.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDTO {

	@NotNull(message = "\'value\' should not be empty")
	@Min(value = -100, message = "\'value\' should be greater than -100")
	@Max(value = 100, message = "\'value\' should be less than 100")
	private Float value;

	@NotNull(message = "\'raining\' should not be null")
	private Boolean raining;

	@NotNull(message = "\'sensor\' should not be null")
	private SensorDTO sensor;

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

	public SensorDTO getSensor() {
		return sensor;
	}

	public void setSensor(SensorDTO sensor) {
		this.sensor = sensor;
	}

}
