package ru.berkovets.springcourse.project3.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.berkovets.springcourse.project3.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
	Optional<Sensor> findByName(String name);
}
