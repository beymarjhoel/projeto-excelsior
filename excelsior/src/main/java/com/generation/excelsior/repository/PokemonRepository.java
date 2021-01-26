package com.generation.excelsior.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.excelsior.model.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
	public List<Pokemon> findAllByTipoContainingIgnoreCase (String categoria);
}