package com.example.kyselySovellus.domain;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.kyselySovellus.domain.Kysely;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Kysymys {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long kysymys_id;
	@NotNull
    @Size(min=1, max=100)
    String teksti;
	String tyyppi;
	Boolean vaadittu;
	@ElementCollection(targetClass=String.class)
	List<String> monivalinta;
	
	
	@ManyToOne
    @JsonIgnoreProperties ("kysymys") 
    @JoinColumn(name = "kysely_id")
    private Kysely kysely;

	public Kysymys(String teksti, String tyyppi, Boolean vaadittu) {
		super();
		this.tyyppi = tyyppi;
		this.vaadittu = vaadittu;
		this.teksti = teksti;
	
	}
	
	public Kysymys(String teksti, String tyyppi, Boolean vaadittu, Kysely kysely) {
		super();
		this.tyyppi = tyyppi;
		this.vaadittu = vaadittu;
		this.teksti = teksti;
		this.kysely = kysely;
	}
	
	public Kysymys(String teksti, String tyyppi, Boolean vaadittu, List<String> lista, Kysely kysely) {
		super();
		this.tyyppi = tyyppi;
		this.vaadittu = vaadittu;
		this.teksti = teksti;
		this.monivalinta = lista;
		this.kysely = kysely;
	}

	public Kysymys(String teksti, Kysely kysely) {
		super();
		this.teksti = teksti;
		this.kysely = kysely;
	}
	
	public Kysymys() {
		
	}

	public List<String> getMonivalinta() {
		return monivalinta;
	}

	public void setMonivalinta(List<String> monivalinta) {
		this.monivalinta = monivalinta;
	}

	public Long getKysymys_id() {
		return kysymys_id;
	}

	public void setKysymys_id(Long kysymys_id) {
		this.kysymys_id = kysymys_id;
	}

	public String getTyyppi() {
		return tyyppi;
	}

	public void setTyyppi(String tyyppi) {
		this.tyyppi = tyyppi;
	}

	public Boolean getVaadittu() {
		return vaadittu;
	}

	public void setVaadittu(Boolean vaadittu) {
		this.vaadittu = vaadittu;
	}

	public String getTeksti() {
		return teksti;
	}

	public void setTeksti(String teksti) {
		this.teksti = teksti;
	}

	public Kysely getKysely() {
		return kysely;
	}

	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}

//	@Override
//	public String toString() {
//		return "Kysymys [tyyppi=" + tyyppi + ", vaadittu=" + vaadittu + ", teksti=" + teksti + ", kysely=" + kysely + "]";
//§	}
	
}
