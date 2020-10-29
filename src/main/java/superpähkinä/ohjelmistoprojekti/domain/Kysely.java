package superpähkinä.ohjelmistoprojekti.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Kysely {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long kysely_id;
	private String nimi;
	private LocalDate luontipvm;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymys")
	private List<Kysymys> kysymykset;

	public Kysely() {

	}

	public Kysely(String nimi) {

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

	public List<Kysymys> getKysymykset() {
		return kysymykset;
	}

	public void setKysymykset(List<Kysymys> kysymykset) {
		this.kysymykset = kysymykset;
	}

	@Override
	public String toString() {
		return "Kysely [kysely_id=" + kysely_id + ", nimi=" + nimi + ", luontipvm=" + luontipvm + ", kysymykset="
				+ kysymykset + "]";
	}

}
