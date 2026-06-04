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

import rs.ac.singidunum.autosalon.model.Prodaja;
import rs.ac.singidunum.autosalon.service.ProdajaService;

@RestController
@RequestMapping("/api/prodaje")
public class ProdajaController {
	private final ProdajaService prodajaService;
	
	public ProdajaController(ProdajaService prodajaService) {
		this.prodajaService = prodajaService;
	}
	
	@GetMapping
	public List<Prodaja> findAll() {
		return prodajaService.findAll();
	}
	
	@GetMapping("/{id}")
	public Prodaja findById(@PathVariable Long id) {
		return prodajaService.findById(id);
	}
	
	@PostMapping
	public Prodaja create(@RequestBody Prodaja prodaja) {
		return prodajaService.save(prodaja);
	}
	
	@PutMapping("/{id}")
	public Prodaja update(@PathVariable Long id, @RequestBody Prodaja prodaja) {
		return prodajaService.update(id, prodaja);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		prodajaService.deleteById(id);
	}
	
	
}
