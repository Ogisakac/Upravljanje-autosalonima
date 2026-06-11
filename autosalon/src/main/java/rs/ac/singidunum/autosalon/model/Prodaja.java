package rs.ac.singidunum.autosalon.model;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prodaja")
public class Prodaja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date datumProdaje;
	
	@Column(nullable = false)
	private Double cenaProdaje;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NacinPlacanja nacinPlacanja;
	
	@ManyToOne
	@JoinColumn(name = "kupac_id", nullable = false)
	private Kupac kupac;
	
	@ManyToOne
	@JoinColumn(name = "zaposleni_id", nullable = false)
	private Zaposleni zaposleni;
	
	@OneToOne
	@JoinColumn(name = "automobil_id", nullable = false, unique = true)
	private Automobil automobil;

	public Prodaja() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prodaja(Date datumProdaje, Double cenaProdaje, NacinPlacanja nacinPlacanja) {
		super();
		this.datumProdaje = datumProdaje;
		this.cenaProdaje = cenaProdaje;
		this.nacinPlacanja = nacinPlacanja;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumProdaje() {
		return datumProdaje;
	}

	public void setDatumProdaje(Date datumProdaje) {
		this.datumProdaje = datumProdaje;
	}

	public Double getCenaProdaje() {
		return cenaProdaje;
	}

	public void setCenaProdaje(Double cenaProdaje) {
		this.cenaProdaje = cenaProdaje;
	}

	public NacinPlacanja getNacinPlacanja() {
		return nacinPlacanja;
	}

	public void setNacinPlacanja(NacinPlacanja nacinPlacanja) {
		this.nacinPlacanja = nacinPlacanja;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	public Automobil getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}
	
	
	
	
	
	
}
