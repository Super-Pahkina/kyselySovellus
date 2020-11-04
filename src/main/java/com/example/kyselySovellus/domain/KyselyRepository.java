package com.example.kyselySovellus.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface KyselyRepository extends CrudRepository<Kysely, Long>{
	List<Kysely> findByNimi(String nimi);
}
