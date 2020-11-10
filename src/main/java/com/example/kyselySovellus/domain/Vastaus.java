package com.example.kyselySovellus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Vastaus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long kysely_id;
	private String syote;
//	private LocalDate luontipvm;
	
	@ManyToOne
    @JsonIgnoreProperties ("vastaus") 
    @JoinColumn(name = "kysymys_id")
    private Kysymys kysymys;

	public Vastaus(String syote, Kysymys kysymys) {
		super();
		this.syote = syote;
		this.kysymys = kysymys;
	}

	public Vastaus() {
		
	}

	public Long getKysely_id() {
		return kysely_id;
	}

	public void setKysely_id(Long kysely_id) {
		this.kysely_id = kysely_id;
	}

	public String getSyote() {
		return syote;
	}

	public void setSyote(String syote) {
		this.syote = syote;
	}

	public Kysymys getKysymys() {
		return kysymys;
	}

	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}
	
	
}