package rs.ac.singidunum.autosalon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.singidunum.autosalon.model.Oprema;
import rs.ac.singidunum.autosalon.repository.OpremaRepository;

@Service
public class OpremaService {
	private final OpremaRepository opremaRepository;
	
	public OpremaService(OpremaRepository opremaRepository) {
		this.opremaRepository = opremaRepository;
	}
	
	public List<Oprema> findAll(){
		return opremaRepository.findAll();
	}
	
	public Oprema findById(Long id) {
		return opremaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Oprema nije pronadjena"));
	}
	
	public Oprema save(Oprema oprema) {
		return opremaRepository.save(oprema);
	}
	
	public Oprema update(Long id, Oprema izmenjenaOprema) {
		Oprema postojecaOprema = findById(id);
		
		postojecaOprema.setNaziv(izmenjenaOprema.getNaziv());
		postojecaOprema.setOpis(izmenjenaOprema.getOpis());
		
		return opremaRepository.save(postojecaOprema);
	}
	
	public void deleteById(Long id) {
		Oprema oprema = findById(id);
		opremaRepository.delete(oprema);
	}
}
