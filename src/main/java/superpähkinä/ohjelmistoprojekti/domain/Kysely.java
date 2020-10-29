package superpähkinä.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Kysely {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long kysely_id;
	private String nimi;
	private LocalDate luontipvm;
	
	@ManyToOne
	@JoinColumn(name = "kysymys_id")
	private Kysymys kysymys;
	
	public Kysely() {
		
	}
	
	public Kysely(long kysely_id, String nimi) {

		this.kysely_id = kysely_id;
		this.nimi = nimi;
		this.luontipvm = LocalDate.now();
	}

	public long getKysely_id() {
		return kysely_id;
	}

	public void setKysely_id(long kysely_id) {
		this.kysely_id = kysely_id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public LocalDate getLuontipvm() {
		return luontipvm;
	}

	public void setLuontipvm(LocalDate luontipvm) {
		this.luontipvm = luontipvm;
	}

	@Override
	public String toString() {
		return "Kysely [kysely_id=" + kysely_id + ", nimi=" + nimi + ", luontipvm=" + luontipvm + "]";
	}
	

}
