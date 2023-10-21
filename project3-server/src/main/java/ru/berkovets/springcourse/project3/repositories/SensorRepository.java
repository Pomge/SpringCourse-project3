package ru.berkovets.springcourse.project3.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.berkovets.springcourse.project3.models.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
	Optional<Sensor> findByName(String name);
}
