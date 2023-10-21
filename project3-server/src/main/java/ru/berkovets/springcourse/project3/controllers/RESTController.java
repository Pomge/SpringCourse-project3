package ru.berkovets.springcourse.project3.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.berkovets.springcourse.project3.dto.MeasurementDTO;
import ru.berkovets.springcourse.project3.dto.SensorDTO;
import ru.berkovets.springcourse.project3.models.Measurement;
import ru.berkovets.springcourse.project3.models.Sensor;
import ru.berkovets.springcourse.project3.services.MeasurementService;
import ru.berkovets.springcourse.project3.services.SensorService;
import ru.berkovets.springcourse.project3.util.MeasurementErrorResponse;
import ru.berkovets.springcourse.project3.util.MeasurementNotCreatedException;
import ru.berkovets.springcourse.project3.util.SensorErrorResponse;
import ru.berkovets.springcourse.project3.util.SensorNotCreatedException;
import ru.berkovets.springcourse.project3.util.SensorValidator;

@RestController
public class RESTController {

	private final ModelMapper modelMapper;
	private final SensorValidator sensorValidator;
	private final SensorService sensorService;
	private final MeasurementService measurementService;

	@Autowired
	public RESTController(ModelMapper modelMapper, SensorValidator sensorValidator, SensorService sensorService,
			MeasurementService measurementService) {
		this.modelMapper = modelMapper;
		this.sensorValidator = sensorValidator;
		this.sensorService = sensorService;
		this.measurementService = measurementService;
	}

	@PostMapping("/sensors/registration")
	public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO,
			BindingResult bindingResult) {
		sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);

		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();

			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				errorMessage.append(error.getField()).append(" : ").append(error.getDefaultMessage()).append("; ");
			}

			throw new SensorNotCreatedException(errorMessage.toString());
		}
		sensorService.save(convertToSensor(sensorDTO));

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping("/measurements/add")
	public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
			BindingResult bindingResult) {
		SensorDTO sensorDTO = measurementDTO.getSensor();
		Optional<Sensor> sensor = sensorService.findByName(sensorDTO.getName());

		if (!sensor.isPresent()) {
			throw new MeasurementNotCreatedException("\'sensor\' not found");
		}

		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();

			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				errorMessage.append(error.getField()).append(" : ").append(error.getDefaultMessage()).append("; ");
			}

			throw new MeasurementNotCreatedException(errorMessage.toString());
		}
		measurementService.save(convertToMeasurement(measurementDTO), sensor.get());

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/measurements")
	public List<MeasurementDTO> getMeasurements() {
		return measurementService.findAll().stream().map(this::convertoMeasurementDTO).collect(Collectors.toList());
	}

	@GetMapping("/measurements/rainyDaysCount")
	public long getRainyDaysCount() {
		return measurementService.countByRaining(true);
	}

	@ExceptionHandler
	private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException exception) {
		SensorErrorResponse response = new SensorErrorResponse(exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException exception) {
		MeasurementErrorResponse response = new MeasurementErrorResponse(exception.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	private Sensor convertToSensor(SensorDTO sensorDTO) {
		return modelMapper.map(sensorDTO, Sensor.class);
	}

	private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
		return modelMapper.map(measurementDTO, Measurement.class);
	}

	private MeasurementDTO convertoMeasurementDTO(Measurement measurement) {
		return modelMapper.map(measurement, MeasurementDTO.class);
	}
}
