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

import rs.ac.singidunum.autosalon.model.Kupac;
import rs.ac.singidunum.autosalon.service.KupacService;

@RestController
@RequestMapping("/api/kupci")
public class KupacController {

	private final KupacService kupacService;
	
	public KupacController(KupacService kupacService) {
		this.kupacService = kupacService;
	}
	
	@GetMapping
	public List<Kupac> findAll(){
		return kupacService.findAll();
	}
	
	@GetMapping("/{id}")
	public Kupac findById(@PathVariable Long id) {
		return kupacService.findById(id);
	}
	
	@PostMapping
	public Kupac create(@RequestBody Kupac kupac) {
		return kupacService.save(kupac);
	}
	
	@PutMapping("/{id}")
	public Kupac update(@PathVariable Long id, @RequestBody Kupac kupac) {
		return kupacService.update(id, kupac);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		kupacService.deleteById(id);
	}
}
