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

@Entity
public class Kupac {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String ime;
	
	@Column(nullable = false, length = 50)
	private String prezime;
	
	@Column(nullable = false, length = 20)
	private String telefon;
	
	@Column(nullable = true, length = 80)
	private String email;
	
//	@JsonManagedReference(value = "kupac-prodaje")
	@JsonIgnore
	@OneToMany(mappedBy = "kupac")
	private List<Prodaja> prodaje;

	public Kupac() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Kupac(String ime, String prezime, String telefon, String email) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Prodaja> getProdaje() {
		return prodaje;
	}

	public void setProdaje(List<Prodaja> prodaje) {
		this.prodaje = prodaje;
	}
	
	
	
	
}
