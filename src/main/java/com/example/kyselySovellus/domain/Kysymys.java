package com.example.kyselySovellus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.kyselySovellus.domain.Kysely;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Kysymys {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long kysymys_id;
	String tyyppi;
	Boolean vaadittu;
	String teksti;
	
	@ManyToOne
    @JsonIgnoreProperties ("kysymys") 
    @JoinColumn(name = "kysely_id")
    private Kysely kysely;

	public Kysymys(String tyyppi, Boolean vaadittu, String teksti) {
		super();
		this.tyyppi = tyyppi;
		this.vaadittu = vaadittu;
		this.teksti = teksti;
	
	}
	
	public Kysymys(String tyyppi, Boolean vaadittu, String teksti, Kysely kysely) {
		super();
		this.tyyppi = tyyppi;
		this.vaadittu = vaadittu;
		this.teksti = teksti;
		this.kysely = kysely;
	}

	public Kysymys(String teksti, Kysely kysely) {
		super();
		this.teksti = teksti;
		this.kysely = kysely;
	}
	
	public Kysymys() {
		
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

	@Override
	public String toString() {
		return "Kysymys [tyyppi=" + tyyppi + ", vaadittu=" + vaadittu + ", teksti=" + teksti + ", kysely=" + kysely + "]";
	}
	
}
