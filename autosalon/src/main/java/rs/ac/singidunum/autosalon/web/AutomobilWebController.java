package rs.ac.singidunum.autosalon.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import rs.ac.singidunum.autosalon.model.Automobil;
import rs.ac.singidunum.autosalon.model.StatusAutomobila;
import rs.ac.singidunum.autosalon.service.AutomobilService;
import rs.ac.singidunum.autosalon.service.OpremaService;
import rs.ac.singidunum.autosalon.service.SalonService;

@Controller
public class AutomobilWebController {
	
	private final SalonService salonService;
	private final AutomobilService automobilService;
	private final OpremaService opremaService;
	
	public AutomobilWebController(AutomobilService automobilService, SalonService salonService,
			OpremaService opremaService) {
		this.automobilService = automobilService;
		this.salonService = salonService;
		this.opremaService = opremaService;
	}
	
	@GetMapping("/automobili")
	public String prikaziAutomobile(Model model) {
		model.addAttribute("automobili", automobilService.findAll());
		model.addAttribute("saloni", salonService.findAll());
		model.addAttribute("statusi", StatusAutomobila.values());
		return "automobili";
	}
	
	@GetMapping("/automobili/dostupni")
	public String prikaziDostupneAutomobile(Model model) {
		model.addAttribute("automobili", automobilService.findDostupniAutomobili());
		return "automobili";
	}
	
	@GetMapping("/automobili/pretraga")
	public String prikaziPretraguPoMarki() {
		return "automobili-pretraga";
	}
	
	@GetMapping("/automobili/pretraga/rezultat")
	public String pretraziPoMarki(@RequestParam String marka, Model model) {
	    model.addAttribute("automobili", automobilService.findByMarka(marka));
	    return "automobili";
	}
	
	@GetMapping("/automobili/izmeni/{id}")
	public String prikaziFormuZaIzmenu(@PathVariable Long id, Model model) {
	    model.addAttribute("automobil", automobilService.findById(id));
	    model.addAttribute("saloni", salonService.findAll());
	    model.addAttribute("statusi", StatusAutomobila.values());
	    model.addAttribute("opremaLista", opremaService.findAll());
	    return "automobil-form";
	}
	
	@GetMapping("/automobili/novi")
	public String prikaziFormuZaDodavanje(Model model) {
		model.addAttribute("automobil", new Automobil());
		model.addAttribute("saloni", salonService.findAll());
		model.addAttribute("statusi", StatusAutomobila.values());
		model.addAttribute("opremaLista", opremaService.findAll());
		
		return "automobil-form";
	}
	
	@PostMapping("/automobili")
	public String sacuvajAutomobil(
	        @ModelAttribute Automobil automobil,
	        @RequestParam(required = false) List<Long> opremaIds) {

	    automobilService.postaviOpremu(automobil, opremaIds);

	    if (automobil.getId() != null) {
	        automobilService.update(automobil.getId(), automobil);
	    } else {
	        automobilService.save(automobil);
	    }

	    return "redirect:/automobili";
	}
	
	@PostMapping("/automobili/obrisi/{id}")
	public String obrisiAutomobil(@PathVariable Long id) {
	    automobilService.deleteById(id);
	    return "redirect:/automobili";
	}
}
