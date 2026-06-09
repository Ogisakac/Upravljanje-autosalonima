package rs.ac.singidunum.autosalon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rs.ac.singidunum.autosalon.model.Kupac;
import rs.ac.singidunum.autosalon.service.KupacService;

@Controller
public class KupacWebController {
	
	private final KupacService kupacService;
	
	public KupacWebController(KupacService kupacService) {
		this.kupacService = kupacService;
	}
	
	@GetMapping("/kupci")
	public String prikaziKupce(Model model) {
	    model.addAttribute("kupci", kupacService.findAll());
	    return "kupci";
	}
	
	@GetMapping("/kupci/novi")
	public String prikaziFormu(Model model) {
	    model.addAttribute("kupac", new Kupac());
	    return "kupac-form";
	}
	
	@PostMapping("/kupci")
	public String sacuvajKupca(@ModelAttribute Kupac kupac) {
	    if (kupac.getId() != null) {
	        kupacService.update(kupac.getId(), kupac);
	    } else {
	        kupacService.save(kupac);
	    }

	    return "redirect:/kupci";
	}
	
	@GetMapping("/kupci/izmeni/{id}")
	public String izmeniKupca(@PathVariable Long id, Model model) {
	    model.addAttribute("kupac", kupacService.findById(id));
	    return "kupac-form";
	}
	
	@PostMapping("/kupci/obrisi/{id}")
	public String obrisiKupca(@PathVariable Long id) {
	    kupacService.deleteById(id);
	    return "redirect:/kupci";
	}
}
