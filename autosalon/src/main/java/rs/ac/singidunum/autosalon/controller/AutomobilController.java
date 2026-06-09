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

import rs.ac.singidunum.autosalon.model.Automobil;
import rs.ac.singidunum.autosalon.service.AutomobilService;

@RestController
@RequestMapping("/api/automobili")
public class AutomobilController {
	private final AutomobilService automobilService;
	
	public AutomobilController(AutomobilService automobilService) {
		this.automobilService = automobilService;
	}
	
	@GetMapping
	public List<Automobil> findAll(){
		return automobilService.findAll();
	}
	
	@GetMapping("/{id}")
	public Automobil findById(@PathVariable Long id) {
		return automobilService.findById(id);
	}
	
	@PostMapping
	public Automobil create(@RequestBody Automobil automobil) {
		return automobilService.save(automobil);
	}
	
	@PutMapping("/{id}")
	public Automobil update(@PathVariable Long id, @RequestBody Automobil automobil) {
		return automobilService.update(id, automobil);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		automobilService.deleteById(id);
	}
	
	@GetMapping("/dostupni")
	public List<Automobil> findDostupniAutomobili(){
		return automobilService.findDostupniAutomobili();
	}
	
	@GetMapping("/marka/{marka}")
	public List<Automobil> findByMarka(@PathVariable String marka){
		return automobilService.findByMarka(marka);
	}
	
}
