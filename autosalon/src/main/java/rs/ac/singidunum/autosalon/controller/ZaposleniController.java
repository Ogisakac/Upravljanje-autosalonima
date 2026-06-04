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

import rs.ac.singidunum.autosalon.model.Zaposleni;
import rs.ac.singidunum.autosalon.service.ZaposleniService;

@RestController
@RequestMapping("/api/zaposleni")
public class ZaposleniController {
	private final ZaposleniService zaposleniService;
	
	public ZaposleniController(ZaposleniService zaposleniService) {
		this.zaposleniService = zaposleniService;
	}
	
	@GetMapping
	public List<Zaposleni> findAll() {
		return zaposleniService.findAll();
	}
	
	@GetMapping("/{id}")
	public Zaposleni findById(@PathVariable Long id) {
		return zaposleniService.findById(id);
	}
	
	@PostMapping
	public Zaposleni create(@RequestBody Zaposleni zaposleni) {
		return zaposleniService.save(zaposleni);
	}
	
	@PutMapping("/{id}")
	public Zaposleni update(@PathVariable Long id, @RequestBody Zaposleni zaposleni) {
		return zaposleniService.update(id, zaposleni);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		zaposleniService.deleteById(id);
	}
}
