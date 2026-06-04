package rs.ac.singidunum.autosalon.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.autosalon.model.Oprema;
import rs.ac.singidunum.autosalon.service.OpremaService;

@RestController
@RequestMapping("/api/oprema")
public class OpremaController {

	private final OpremaService opremaService;
	
	public OpremaController(OpremaService opremaService) {
		this.opremaService = opremaService;
	}
	
	@GetMapping
	public List<Oprema> findAll(){
		return opremaService.findAll();
	}
	
	@GetMapping("/{id}")
	public Oprema findById(@PathVariable Long id) {
		return opremaService.findById(id);
	}
	
	@PostMapping
	public Oprema create(@RequestBody Oprema oprema) {
		return opremaService.save(oprema);
	}
	
	@PutMapping("/{id}")
	public Oprema update(@PathVariable Long id, @RequestBody Oprema oprema) {
		return opremaService.update(id, oprema);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		opremaService.deleteById(id);
	}
	
}
