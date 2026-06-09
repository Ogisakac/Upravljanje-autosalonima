package rs.ac.singidunum.autosalon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "oprema")
public class Oprema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 150)
	private String naziv;
	
	@Column(nullable = false, length = 300)
	private String opis;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "oprema")
	private List<Automobil> automobili;

	public Oprema() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Oprema(String naziv, String opis) {
		super();
		this.naziv = naziv;
		this.opis = opis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Automobil> getAutomobili() {
		return automobili;
	}

	public void setAutomobili(List<Automobil> automobili) {
		this.automobili = automobili;
	}
	
	
}
