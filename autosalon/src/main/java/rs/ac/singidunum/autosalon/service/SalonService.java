package rs.ac.singidunum.autosalon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.singidunum.autosalon.exception.ResourceNotFoundException;
import rs.ac.singidunum.autosalon.model.Salon;
import rs.ac.singidunum.autosalon.repository.SalonRepository;

@Service
public class SalonService {
	private final SalonRepository salonRepository;
	
	public SalonService(SalonRepository salonRepository) {
		this.salonRepository = salonRepository;
	}
	
	public List<Salon> findAll() {
		return salonRepository.findAll();
	}
	
	public Salon findById(Long id) {
		return salonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Salon nije pronadjen"));
	}
	
	public Salon save(Salon salon) {
		return salonRepository.save(salon);
	}
	
	public Salon update(Long id, Salon izmenjeniSalon) {
		Salon postojeciSalon = findById(id);
		
		postojeciSalon.setNaziv(izmenjeniSalon.getNaziv());
		postojeciSalon.setAdresa(izmenjeniSalon.getAdresa());
		postojeciSalon.setGrad(izmenjeniSalon.getGrad());
		postojeciSalon.setTelefon(izmenjeniSalon.getTelefon());
		return salonRepository.save(postojeciSalon);
	}
	
	public void deleteById(Long id) {
		Salon salon = findById(id);
		salonRepository.delete(salon);
	}
	
	
}
