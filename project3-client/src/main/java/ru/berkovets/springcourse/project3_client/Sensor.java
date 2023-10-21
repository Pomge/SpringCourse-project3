package ru.berkovets.springcourse.project3_client;

public class Sensor {
	private String name;

	public Sensor() {

	}

	public Sensor(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Sensor [name=" + name + "]";
	}

}
