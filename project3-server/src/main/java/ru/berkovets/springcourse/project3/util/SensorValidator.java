package ru.berkovets.springcourse.project3.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.berkovets.springcourse.project3.models.Sensor;
import ru.berkovets.springcourse.project3.services.SensorService;

@Component
public class SensorValidator implements Validator {

	private final SensorService sensorService;

	@Autowired
	public SensorValidator(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Sensor.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Sensor sensor = (Sensor) target;

		Optional<Sensor> sensorFromDataBase = sensorService.findByName(sensor.getName());
		if (sensorFromDataBase.isPresent() && (sensor.getId() != sensorFromDataBase.get().getId())) {
			errors.rejectValue("name", "", "This name is already exist");
		}
	}
}
