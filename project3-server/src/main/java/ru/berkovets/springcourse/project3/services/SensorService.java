package ru.berkovets.springcourse.project3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.berkovets.springcourse.project3.models.Sensor;
import ru.berkovets.springcourse.project3.repositories.SensorRepository;

@Service
@Transactional(readOnly = true)
public class SensorService {

	private final SensorRepository sensorRepository;

	@Autowired
	public SensorService(SensorRepository sensorRepository) {
		this.sensorRepository = sensorRepository;
	}

	public Optional<Sensor> findByName(String name) {
		Optional<Sensor> sensor = sensorRepository.findByName(name);
		return sensor;
	}

	@Transactional
	public void save(Sensor sensor) {
		sensorRepository.save(sensor);
	}
}
