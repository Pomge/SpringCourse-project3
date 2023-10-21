package ru.berkovets.springcourse.project3_client;

public class Measurement {
	private Float value;
	private Boolean raining;
	private Sensor sensor;

	public Measurement() {

	}

	public Measurement(Sensor sensor) {
		float min = -5.0f;
		float max = 25.0f;

		this.value = (float) (min + Math.random() * (max - min));
		this.raining = Math.random() < 0.5;
		this.sensor = sensor;
	}

	public Measurement(Float value, Boolean raining, Sensor sensor) {
		this.value = value;
		this.raining = raining;
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

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	@Override
	public String toString() {
		return "Measurement [value=" + value + ", raining=" + raining + ", sensor=" + sensor + "]";
	}

}
