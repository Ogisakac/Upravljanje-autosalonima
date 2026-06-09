package rs.ac.singidunum.autosalon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.singidunum.autosalon.exception.ResourceNotFoundException;
import rs.ac.singidunum.autosalon.model.Salon;
import rs.ac.singidunum.autosalon.model.Zaposleni;
import rs.ac.singidunum.autosalon.repository.SalonRepository;
import rs.ac.singidunum.autosalon.repository.ZaposleniRepository;

@Service
public class ZaposleniService {
	private final ZaposleniRepository zaposleniRepository;
	private final SalonRepository salonRepository;
	
	public ZaposleniService(ZaposleniRepository zaposleniRepository,
			SalonRepository salonRepository){
		this.zaposleniRepository = zaposleniRepository;
		this.salonRepository = salonRepository;
	}
	
	public List<Zaposleni> findAll(){
		return zaposleniRepository.findAll();
	}
	
	public Zaposleni findById(Long id) {
		return zaposleniRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Zaposleni nije pronadjen"));
	}
	
	public Zaposleni save(Zaposleni zaposleni) {
		Long salonId = zaposleni.getSalon().getId();
		
		Salon salon = salonRepository.findById(salonId)
				.orElseThrow(() -> new ResourceNotFoundException("Salon nije pronadjen"));
		
		zaposleni.setSalon(salon);
		
		return zaposleniRepository.save(zaposleni);
	}
	
	public Zaposleni update(Long id, Zaposleni izmenjeniZaposleni) {
		Zaposleni postojeciZaposleni = findById(id);
		Long salonId = izmenjeniZaposleni.getSalon().getId();
		Salon salon = salonRepository.findById(salonId)
				.orElseThrow(() -> new ResourceNotFoundException("Salon nije pronadjen"));
		
		postojeciZaposleni.setIme(izmenjeniZaposleni.getIme());
		postojeciZaposleni.setPrezime(izmenjeniZaposleni.getPrezime());
		postojeciZaposleni.setPozicija(izmenjeniZaposleni.getPozicija());
		postojeciZaposleni.setTelefon(izmenjeniZaposleni.getTelefon());
		postojeciZaposleni.setEmail(izmenjeniZaposleni.getEmail());
		postojeciZaposleni.setSalon(salon);
		
		return zaposleniRepository.save(postojeciZaposleni);
		
	}
	
	public void deleteById(Long id){
		Zaposleni zaposleni = findById(id);
		zaposleniRepository.delete(zaposleni);
	}
	
}
