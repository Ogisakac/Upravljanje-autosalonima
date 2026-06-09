package rs.ac.singidunum.autosalon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rs.ac.singidunum.autosalon.model.Salon;
import rs.ac.singidunum.autosalon.service.SalonService;

@Controller
public class SalonWebController {
	private final SalonService salonService;
	
	public SalonWebController(SalonService salonService) {
		this.salonService = salonService;
	}
	
	@GetMapping("/saloni")
    public String prikaziSalone(Model model) {
        model.addAttribute("saloni", salonService.findAll());
        return "saloni";
    }
	
	@GetMapping("/saloni/novi")
    public String prikaziFormu(Model model) {
        model.addAttribute("salon", new Salon());
        return "salon-form";
    }
	
	@GetMapping("/saloni/izmeni/{id}")
    public String prikaziFormuZaIzmenu(@PathVariable Long id, Model model) {
        model.addAttribute("salon", salonService.findById(id));
        return "salon-form";
    }
	
	@PostMapping("/saloni")
    public String sacuvajSalon(@ModelAttribute Salon salon) {
        if (salon.getId() != null) {
            salonService.update(salon.getId(), salon);
        } else {
            salonService.save(salon);
        }

        return "redirect:/saloni";
    }
	
	@PostMapping("/saloni/obrisi/{id}")
    public String obrisiSalon(@PathVariable Long id) {
        salonService.deleteById(id);
        return "redirect:/saloni";
    }
}
