package rs.ac.singidunum.autosalon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "salon")
public class Salon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 70)
	private String naziv;
	
	@Column(nullable = false, length = 100)
	private String adresa;
	
	@Column(nullable = false, length = 	50)
	private String grad;
	
	@Column(nullable = false, length = 20)
	private String telefon;
	
//	@JsonManagedReference(value = "salon-automobili")
	@JsonIgnore
	@OneToMany(mappedBy = "salon")
	private List<Automobil> automobili;
	
//	@JsonManagedReference(value = "salon-zaposleni")
	@JsonIgnore
	@OneToMany(mappedBy = "salon")
	private List<Zaposleni> zaposleni;

	public Salon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Salon(String naziv, String adresa, String grad, String telefon) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.grad = grad;
		this.telefon = telefon;
		
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<Automobil> getAutomobili() {
		return automobili;
	}

	public void setAutomobili(List<Automobil> automobili) {
		this.automobili = automobili;
	}

	public List<Zaposleni> getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(List<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}
	
	
	
	
	
	
	
	
}
