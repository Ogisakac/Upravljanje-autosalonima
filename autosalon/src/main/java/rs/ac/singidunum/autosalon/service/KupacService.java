package rs.ac.singidunum.autosalon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.singidunum.autosalon.model.Kupac;
import rs.ac.singidunum.autosalon.repository.KupacRepository;

@Service
public class KupacService {
	private final KupacRepository kupacRepository;
	
	public KupacService(KupacRepository kupacRepository) {
		this.kupacRepository = kupacRepository;
	}
	
	public List<Kupac> findAll() {
		return kupacRepository.findAll();
	}
	
	public Kupac findById(Long id) {
		return kupacRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Kupac nije pronadjen"));
	}
	
	public Kupac save(Kupac kupac) {
		return kupacRepository.save(kupac);
	}
	
	public Kupac update(Long id, Kupac izmenjeniKupac) {
		Kupac postojeciKupac = findById(id);
		
		postojeciKupac.setIme(izmenjeniKupac.getIme());
		postojeciKupac.setPrezime(izmenjeniKupac.getPrezime());
		postojeciKupac.setTelefon(izmenjeniKupac.getTelefon());
		postojeciKupac.setEmail(izmenjeniKupac.getEmail());
		
		return kupacRepository.save(postojeciKupac);
	}
	
	public void deleteById(Long id) {
		Kupac kupac = findById(id);
		kupacRepository.delete(kupac);
	}
}
