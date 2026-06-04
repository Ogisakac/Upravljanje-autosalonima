package rs.ac.singidunum.autosalon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "zaposleni")
public class Zaposleni {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String ime;
	
	@Column(nullable = false, length = 50)
	private String prezime;
	
	@Column(nullable = false, length = 50)
	private String pozicija;
	
	@Column(nullable = false, length = 20)
	private String telefon;
	
	@Column(nullable = false, length = 100)
	private String email;
	
//	@JsonBackReference(value = "salon-zaposleni")
	@ManyToOne
	@JoinColumn(name = "salon_id", nullable = false)
	private Salon salon;
	
//	@JsonManagedReference(value = "zaposleni-prodaje")
	@JsonIgnore
	@OneToMany(mappedBy = "zaposleni")
	private List<Prodaja> prodaje;

	public Zaposleni() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Zaposleni(String ime, String prezime, String pozicija, String telefon, String email) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.pozicija = pozicija;
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

	public String getPozicija() {
		return pozicija;
	}

	public void setPozicija(String pozicija) {
		this.pozicija = pozicija;
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

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	public List<Prodaja> getProdaje() {
		return prodaje;
	}

	public void setProdaje(List<Prodaja> prodaje) {
		this.prodaje = prodaje;
	}
	
	
}
