package ru.berkovets.springcourse.project3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.berkovets.springcourse.project3.models.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
	long countByRaining(boolean raining);
}