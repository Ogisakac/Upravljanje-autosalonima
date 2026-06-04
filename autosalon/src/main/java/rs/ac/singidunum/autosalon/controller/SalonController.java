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

import rs.ac.singidunum.autosalon.model.Salon;
import rs.ac.singidunum.autosalon.service.SalonService;

@RestController
@RequestMapping("/api/saloni")
public class SalonController {
	
	private final SalonService salonService;
	
	public SalonController(SalonService salonService) {
		this.salonService = salonService;
	}
	
	@GetMapping
	public List<Salon> findAll(){
		return salonService.findAll();
	}
	
	@GetMapping("/{id}")
	public Salon findById(@PathVariable Long id) {
		return salonService.findById(id);
	}
	
	@PostMapping
	public Salon create(@RequestBody Salon salon) {
		return salonService.save(salon);
	}
	
	@PutMapping("/{id}")
	public Salon update(@PathVariable Long id, @RequestBody Salon salon) {
		return salonService.update(id, salon);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		salonService.deleteById(id);
	}
}
