package ru.berkovets.springcourse.project3.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.berkovets.springcourse.project3.models.Measurement;
import ru.berkovets.springcourse.project3.models.Sensor;
import ru.berkovets.springcourse.project3.repositories.MeasurementRepository;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

	private final MeasurementRepository measurementRepository;

	@Autowired
	public MeasurementService(MeasurementRepository measurementRepository) {
		this.measurementRepository = measurementRepository;
	}

	public List<Measurement> findAll() {
		return measurementRepository.findAll(Sort.by("writenAt"));
	}

	public long countByRaining(boolean b) {
		return measurementRepository.countByRaining(b);
	}

	@Transactional
	public void save(Measurement measurement, Sensor sensor) {
		measurement.setSensor(sensor);
		measurement.setWritenAt(LocalDateTime.now());
		measurementRepository.save(measurement);
	}
}
