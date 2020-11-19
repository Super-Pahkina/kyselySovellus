package com.example.kyselySovellus.domain;

import java.util.List;

import javax.persistence.ElementCollection;
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
	private Long vastaus_id;
	private String syote;
	@ElementCollection(targetClass=String.class)
	private List<String> checkbox;
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
	
	public Vastaus(List<String> checkbox, Kysymys kysymys) {
		super();
		this.checkbox = checkbox;
		this.kysymys = kysymys;
	}

	public Vastaus() {
		
	}

	public List<String> getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(List<String> checkbox) {
		this.checkbox = checkbox;
	}

	public Long getVastaus_id() {
		return vastaus_id;
	}

	public void setVastaus_id(Long vastaus_id) {
		this.vastaus_id = vastaus_id;
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