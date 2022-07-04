package com.Pet_Monitoring.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Entities.Breed;

@Repository
public interface BreedRepository extends CrudRepository<Breed, Long> {
	Optional<Breed> findByName(String breedName);

	boolean existsByName(String breedName);
	
	List<Breed>findAllBySpeciesId(Long speciesId);
}
