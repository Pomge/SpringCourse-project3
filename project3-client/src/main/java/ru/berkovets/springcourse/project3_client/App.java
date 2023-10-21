package ru.berkovets.springcourse.project3_client;

public class App {
	public static void main(String[] args) {
		MyRestTemplate myRestTemplate = new MyRestTemplate();
		myRestTemplate.createSensor();
		myRestTemplate.addMeasurements();
		myRestTemplate.getMeasurements();
		myRestTemplate.drawChart();
	}
}
