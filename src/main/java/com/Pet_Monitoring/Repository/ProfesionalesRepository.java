package com.Pet_Monitoring.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entity.Establishment;

@Repository
public interface ProfesionalesRepository extends JpaRepository<Establishment, Integer> {

	Optional<Establishment> findByNombre(String nombre);

	boolean existsByNombre(String nombre);
	
}
