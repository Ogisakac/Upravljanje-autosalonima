package rs.ac.singidunum.autosalon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rs.ac.singidunum.autosalon.model.Zaposleni;
import rs.ac.singidunum.autosalon.service.SalonService;
import rs.ac.singidunum.autosalon.service.ZaposleniService;

@Controller
public class ZaposleniWebController {
	private final ZaposleniService zaposleniService;
	private final SalonService salonService;
	public ZaposleniWebController(ZaposleniService zaposleniService, SalonService salonService) {
		super();
		this.zaposleniService = zaposleniService;
		this.salonService = salonService;
	}
	
	@GetMapping("/zaposleni")
    public String prikaziZaposlene(Model model) {
        model.addAttribute("zaposleni", zaposleniService.findAll());
        return "zaposleni";
    }
	
	@GetMapping("/zaposleni/novi")
    public String prikaziFormu(Model model) {
        model.addAttribute("zaposleniObjekat", new Zaposleni());
        model.addAttribute("saloni", salonService.findAll());
        return "zaposleni-form";
    }
	
	@GetMapping("/zaposleni/izmeni/{id}")
    public String prikaziFormuZaIzmenu(@PathVariable Long id, Model model) {
        model.addAttribute("zaposleniObjekat", zaposleniService.findById(id));
        model.addAttribute("saloni", salonService.findAll());
        return "zaposleni-form";
    }
	
	@PostMapping("/zaposleni")
    public String sacuvajZaposlenog(@ModelAttribute("zaposleniObjekat") Zaposleni zaposleni) {
        if (zaposleni.getId() != null) {
            zaposleniService.update(zaposleni.getId(), zaposleni);
        } else {
            zaposleniService.save(zaposleni);
        }

        return "redirect:/zaposleni";
    }
	
	@PostMapping("/zaposleni/obrisi/{id}")
    public String obrisiZaposlenog(@PathVariable Long id) {
        zaposleniService.deleteById(id);
        return "redirect:/zaposleni";
    }
}
