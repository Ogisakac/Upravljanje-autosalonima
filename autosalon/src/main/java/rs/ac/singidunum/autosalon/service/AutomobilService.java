package rs.ac.singidunum.autosalon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.singidunum.autosalon.exception.BusinessException;
import rs.ac.singidunum.autosalon.exception.ResourceNotFoundException;
import rs.ac.singidunum.autosalon.model.Automobil;
import rs.ac.singidunum.autosalon.model.Salon;
import rs.ac.singidunum.autosalon.model.StatusAutomobila;
import rs.ac.singidunum.autosalon.repository.AutomobilRepository;
import rs.ac.singidunum.autosalon.repository.OpremaRepository;
import rs.ac.singidunum.autosalon.repository.SalonRepository;

@Service
public class AutomobilService {
	private final AutomobilRepository automobilRepository;
	private final SalonRepository salonRepository;
	private final OpremaRepository opremaRepository;
	
	public AutomobilService(AutomobilRepository automobilRepository,
			SalonRepository salonRepository, OpremaRepository opremaRepository) {
		this.automobilRepository = automobilRepository;
		this.salonRepository = salonRepository;
		this.opremaRepository = opremaRepository;
	}
	
	public List<Automobil> findAll() {
		return automobilRepository.findAll();
	}
	
	public Automobil findById(Long id) {
		return automobilRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Automobil nije pronadjen"));
	}
	
	public Automobil save(Automobil automobil) {
		Long salonId = automobil.getSalon().getId();
		Salon salon = salonRepository.findById(salonId)
				.orElseThrow(() -> new ResourceNotFoundException("Salon nije pronadjen"));
		automobil.setSalon(salon);
		if (automobilRepository.existsByRegistracija(automobil.getRegistracija())) {
		    throw new BusinessException("Automobil sa ovom registracijom već postoji.");
		}
		return automobilRepository.save(automobil);
	}
	
	public Automobil update(Long id, Automobil izmenjeniAutomobil) {
		Automobil postojeciAutomobil = findById(id);
		Long salonId = izmenjeniAutomobil.getSalon().getId();
		Salon salon = salonRepository.findById(salonId)
				.orElseThrow(() -> new ResourceNotFoundException("Salon nije pronadjen"));
		
		automobilRepository.findByRegistracija(izmenjeniAutomobil.getRegistracija())
        .ifPresent(a -> {
            if (!a.getId().equals(id)) {
                throw new BusinessException("Automobil sa ovom registracijom vec postoji.");
            }
        });
		
		postojeciAutomobil.setRegistracija(izmenjeniAutomobil.getRegistracija());
		postojeciAutomobil.setMarka(izmenjeniAutomobil.getMarka());
		postojeciAutomobil.setModel(izmenjeniAutomobil.getModel());
		postojeciAutomobil.setGodiste(izmenjeniAutomobil.getGodiste());
		postojeciAutomobil.setKilometraza(izmenjeniAutomobil.getKilometraza());
		postojeciAutomobil.setCena(izmenjeniAutomobil.getCena());
		postojeciAutomobil.setOprema(izmenjeniAutomobil.getOprema());
		postojeciAutomobil.setStatus(izmenjeniAutomobil.getStatus());
		postojeciAutomobil.setSalon(salon);
		
		return automobilRepository.save(postojeciAutomobil);
	}
	
	public void deleteById(Long id) {
		Automobil automobil = findById(id);
		automobilRepository.delete(automobil);
	}
	
	public List<Automobil> findDostupniAutomobili(){
		return automobilRepository.findByStatus(StatusAutomobila.DOSTUPAN);
	}
	
	public List<Automobil> findByMarka(String marka){
		List<Automobil> automobili = automobilRepository.findByMarkaIgnoreCase(marka);
		return automobilRepository.findByMarkaIgnoreCase(marka);
	}
	
	public void postaviOpremu(Automobil automobil, List<Long> opremaIds) {
		if(opremaIds != null && !opremaIds.isEmpty()) {
			automobil.setOprema(opremaRepository.findAllById(opremaIds));
		} else {
			automobil.setOprema(new ArrayList<>());
		}
	}
}
