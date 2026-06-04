package rs.ac.singidunum.autosalon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "automobil")
public class Automobil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 20, unique = true)
	private String registracija;
	
	@Column(nullable = false, length = 30)
	private String marka;
	
	@Column(nullable = false, length = 80)
	private String model;
	
	@Column(nullable = false)
	private Integer godiste;
	
	@Column(nullable = false)
	private Double kilometraza;
	
	@Column(nullable = false)
	private Double cena;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusAutomobila status;
	
//	@JsonBackReference(value = "salon-automobili")
	@ManyToOne
	@JoinColumn(name = "salon_id", nullable = false)
	private Salon salon;
	
	
//	@JsonManagedReference(value = "automobil-prodaja")
	@JsonIgnore
	@OneToOne(mappedBy = "automobil")
	private Prodaja prodaja;
	
	@ManyToMany
	@JoinTable(
	name = "automobil_oprema",
	joinColumns = @JoinColumn(name = "automobil_id"),
	inverseJoinColumns = @JoinColumn(name = "oprema_id")
			)
	private List<Oprema> oprema;

	public Automobil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Automobil(String registracija, String marka, String model, Integer godiste, Double kilometraza, Double cena,
			StatusAutomobila status) {
		super();
		this.registracija = registracija;
		this.marka = marka;
		this.model = model;
		this.godiste = godiste;
		this.kilometraza = kilometraza;
		this.cena = cena;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getGodiste() {
		return godiste;
	}

	public void setGodiste(Integer godiste) {
		this.godiste = godiste;
	}

	public Double getKilometraza() {
		return kilometraza;
	}

	public void setKilometraza(Double kilometraza) {
		this.kilometraza = kilometraza;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public StatusAutomobila getStatus() {
		return status;
	}

	public void setStatus(StatusAutomobila status) {
		this.status = status;
	}

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	public Prodaja getProdaja() {
		return prodaja;
	}

	public void setProdaja(Prodaja prodaja) {
		this.prodaja = prodaja;
	}

	public List<Oprema> getOprema() {
		return oprema;
	}

	public void setOprema(List<Oprema> oprema) {
		this.oprema = oprema;
	}
	
	
	
	
}
