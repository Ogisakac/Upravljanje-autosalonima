package rs.ac.singidunum.autosalon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.singidunum.autosalon.exception.BusinessException;
import rs.ac.singidunum.autosalon.exception.ResourceNotFoundException;
import rs.ac.singidunum.autosalon.model.Automobil;
import rs.ac.singidunum.autosalon.model.Kupac;
import rs.ac.singidunum.autosalon.model.Prodaja;
import rs.ac.singidunum.autosalon.model.StatusAutomobila;
import rs.ac.singidunum.autosalon.model.Zaposleni;
import rs.ac.singidunum.autosalon.repository.AutomobilRepository;
import rs.ac.singidunum.autosalon.repository.KupacRepository;
import rs.ac.singidunum.autosalon.repository.ProdajaRepository;
import rs.ac.singidunum.autosalon.repository.ZaposleniRepository;

@Service
public class ProdajaService {
	
	private final ProdajaRepository prodajaRepository;
	private final KupacRepository kupacRepository;
	private final AutomobilRepository automobilRepository;
	private final ZaposleniRepository zaposleniRepository;
	
	public ProdajaService(ProdajaRepository prodajaRepository,
			KupacRepository kupacRepository,
			AutomobilRepository automobilRepository,
			ZaposleniRepository zaposleniRepository) {
		this.prodajaRepository = prodajaRepository;
		this.kupacRepository = kupacRepository;
		this.automobilRepository = automobilRepository;
		this.zaposleniRepository = zaposleniRepository;
	}
	
	public List<Prodaja> findAll() {
		return prodajaRepository.findAll();
	}
	
	public Prodaja findById(Long id) {
		return prodajaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Prodaja nije pronadjena"));
	}
	
	public Prodaja save(Prodaja prodaja) {
		Long automobilId = prodaja.getAutomobil().getId();
		
		if(prodajaRepository.existsByAutomobilId(automobilId)) {
			throw new BusinessException("Automobil je vec prodat");
		}
		
		pripremiRelacije(prodaja);
		
		Automobil automobil = prodaja.getAutomobil();
		
		automobil.setStatus(StatusAutomobila.PRODAT);
		
		return prodajaRepository.save(prodaja);
	}
	
	public Prodaja update(Long id, Prodaja izmenjenaProdaja) {
		Prodaja postojecaProdaja = findById(id);
		
		postojecaProdaja.setDatumProdaje(izmenjenaProdaja.getDatumProdaje());
		postojecaProdaja.setCenaProdaje(izmenjenaProdaja.getCenaProdaje());
		postojecaProdaja.setNacinPlacanja(izmenjenaProdaja.getNacinPlacanja());
		
		postojecaProdaja.setKupac(izmenjenaProdaja.getKupac());
		postojecaProdaja.setAutomobil(izmenjenaProdaja.getAutomobil());
		postojecaProdaja.setZaposleni(izmenjenaProdaja.getZaposleni());
		
		pripremiRelacije(postojecaProdaja);
		return prodajaRepository.save(postojecaProdaja);
		
	}
	
	public void deleteById(Long id) {
		Prodaja prodaja = findById(id);
		prodajaRepository.delete(prodaja);
	}
	
	private void pripremiRelacije(Prodaja prodaja) {
		Long kupacId = prodaja.getKupac().getId();
		Long automobilId = prodaja.getAutomobil().getId();
		Long zaposleniId = prodaja.getZaposleni().getId();
		
		Kupac kupac = kupacRepository.findById(kupacId)
				.orElseThrow(() -> new ResourceNotFoundException("Kupac nije pronadjen")); 
		
		Automobil automobil = automobilRepository.findById(automobilId)
				.orElseThrow(() -> new ResourceNotFoundException("Automobil nije pronadjen"));
		
		Zaposleni zaposleni = zaposleniRepository.findById(zaposleniId)
				.orElseThrow(() -> new ResourceNotFoundException("Zaposleni nije pronadjen"));
		
		prodaja.setKupac(kupac);
		prodaja.setAutomobil(automobil);
		prodaja.setZaposleni(zaposleni);
	}
	
	public List<Prodaja> findByKupacId(Long kupacId){
		
		kupacRepository.findById(kupacId)
		.orElseThrow(() -> new ResourceNotFoundException("Kupac nije pronadjen"));
		
		return prodajaRepository.findByKupacId(kupacId);
		
		
	}
	
	public Double izracunajUkupanPrihod() {
		return prodajaRepository.findAll()
				.stream()
				.mapToDouble(Prodaja::getCenaProdaje)
				.sum();
	}
	
	public List<Prodaja> findByZaposleniId(Long zaposleniId){
		
		zaposleniRepository.findById(zaposleniId)
				.orElseThrow(() -> new ResourceNotFoundException("Zaposleni nije pronadjen."));
		
			
		
		return prodajaRepository.findByZaposleniId(zaposleniId);
	}
}
