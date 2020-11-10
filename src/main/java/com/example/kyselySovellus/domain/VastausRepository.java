package com.example.kyselySovellus.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VastausRepository extends CrudRepository<Vastaus, Long>{
	List<Vastaus> findBySyote(String syote);
}
