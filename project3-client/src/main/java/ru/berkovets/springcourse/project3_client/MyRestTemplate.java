package ru.berkovets.springcourse.project3_client;

import java.util.List;

import javax.swing.JFrame;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MyRestTemplate {
	private final String defaultURL = "http://localhost:8080";
	private final String addSensorURL = defaultURL + "/sensors/registration";
	private final String addMeasurementURL = defaultURL + "/measurements/add";
	private final String getMeasurementsURL = defaultURL + "/measurements";
	private final String sensorName = "RandomSensor";

	private final RestTemplate restTemplate;
	private final Sensor sensor;

	private List<Measurement> measurements;

	public MyRestTemplate() {
		this.restTemplate = new RestTemplate();
		this.sensor = new Sensor(sensorName);
	}

	public void createSensor() {
		HttpEntity<Sensor> requestSensor = new HttpEntity<Sensor>(sensor);
		String responseSensor = restTemplate.postForObject(addSensorURL, requestSensor, String.class);

		System.out.println(sensor.toString() + " -> " + responseSensor);
	}

	public void addMeasurements() {
		for (int i = 0; i < 1000; i++) {
			Measurement measurement = new Measurement(sensor);
			HttpEntity<Measurement> requestMeasurement = new HttpEntity<Measurement>(measurement);
			String responseMeasurement = restTemplate.postForObject(addMeasurementURL, requestMeasurement,
					String.class);
			System.out.println(measurement.toString() + " -> " + responseMeasurement);
		}
	}

	public void getMeasurements() {
		ResponseEntity<List<Measurement>> responseEntity = restTemplate.exchange(getMeasurementsURL, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Measurement>>() {
				});
		measurements = responseEntity.getBody();
	}

	public void drawChart() {
		int N = measurements.size();
		double[] xData = new double[N];
		double[] yData = new double[N];

		for (int i = 0; i < N; i++) {
			xData[i] = i;
			yData[i] = measurements.get(i).getValue();
		}

		XYChart chart = QuickChart.getChart("Simple Chart", "#", "Temperature", "Random", xData, yData);
		new SwingWrapper<XYChart>(chart).displayChart();
	}
}
