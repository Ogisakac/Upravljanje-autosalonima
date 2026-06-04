package rs.ac.singidunum.autosalon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.singidunum.autosalon.model.Automobil;
import rs.ac.singidunum.autosalon.model.Salon;
import rs.ac.singidunum.autosalon.repository.AutomobilRepository;
import rs.ac.singidunum.autosalon.repository.SalonRepository;

@Service
public class AutomobilService {
	private final AutomobilRepository automobilRepository;
	private final SalonRepository salonRepository;
	
	public AutomobilService(AutomobilRepository automobilRepository,
			SalonRepository salonRepository) {
		this.automobilRepository = automobilRepository;
		this.salonRepository = salonRepository;
	}
	
	public List<Automobil> findAll() {
		return automobilRepository.findAll();
	}
	
	public Automobil findById(Long id) {
		return automobilRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Automobil nije pronadjen"));
	}
	
	public Automobil save(Automobil automobil) {
		Long salonId = automobil.getSalon().getId();
		Salon salon = salonRepository.findById(salonId)
				.orElseThrow(() -> new RuntimeException("Salon nije pronadjen"));
		automobil.setSalon(salon);
		return automobilRepository.save(automobil);
	}
	
	public Automobil update(Long id, Automobil izmenjeniAutomobil) {
		Automobil postojeciAutomobil = findById(id);
		Long salonId = izmenjeniAutomobil.getSalon().getId();
		Salon salon = salonRepository.findById(salonId)
				.orElseThrow(() -> new RuntimeException("Salon nije pronadjen"));
		
		postojeciAutomobil.setRegistracija(izmenjeniAutomobil.getRegistracija());
		postojeciAutomobil.setMarka(izmenjeniAutomobil.getMarka());
		postojeciAutomobil.setModel(izmenjeniAutomobil.getModel());
		postojeciAutomobil.setGodiste(izmenjeniAutomobil.getGodiste());
		postojeciAutomobil.setKilometraza(izmenjeniAutomobil.getKilometraza());
		postojeciAutomobil.setCena(izmenjeniAutomobil.getCena());
		postojeciAutomobil.setStatus(izmenjeniAutomobil.getStatus());
		postojeciAutomobil.setSalon(salon);
		
		return automobilRepository.save(postojeciAutomobil);
	}
	
	public void deleteById(Long id) {
		Automobil automobil = findById(id);
		automobilRepository.delete(automobil);
	}
	
}
