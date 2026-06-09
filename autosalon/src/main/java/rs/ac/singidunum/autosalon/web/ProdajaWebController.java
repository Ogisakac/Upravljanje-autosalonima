package rs.ac.singidunum.autosalon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rs.ac.singidunum.autosalon.model.NacinPlacanja;
import rs.ac.singidunum.autosalon.model.Prodaja;
import rs.ac.singidunum.autosalon.service.AutomobilService;
import rs.ac.singidunum.autosalon.service.KupacService;
import rs.ac.singidunum.autosalon.service.ProdajaService;
import rs.ac.singidunum.autosalon.service.ZaposleniService;

@Controller
public class ProdajaWebController {

	private final ProdajaService prodajaService;
	private final AutomobilService automobilService;
	private final KupacService kupacService;
	private final ZaposleniService zaposleniService;
	
	public ProdajaWebController(ProdajaService prodajaService, AutomobilService automobilService,
			KupacService kupacService, ZaposleniService zaposleniService) {
		super();
		this.prodajaService = prodajaService;
		this.automobilService = automobilService;
		this.kupacService = kupacService;
		this.zaposleniService = zaposleniService;
	}
	
	@GetMapping("/prodaje")
    public String prikaziProdaje(Model model) {
        model.addAttribute("prodaje", prodajaService.findAll());
        return "prodaje";
    }
	
	@GetMapping("/prodaje/ukupan-prihod")
	public String prikaziUkupanPrihod(Model model) {
	    model.addAttribute("ukupanPrihod", prodajaService.izracunajUkupanPrihod());
	    return "ukupan-prihod";
	}

	@GetMapping("/prodaje/kupac")
	public String prikaziProdajePoKupcu(@RequestParam Long kupacId, Model model) {
	    model.addAttribute("prodaje", prodajaService.findByKupacId(kupacId));
	    return "prodaje";
	}

	@GetMapping("/prodaje/zaposleni")
	public String prikaziProdajePoZaposlenom(@RequestParam Long zaposleniId, Model model) {
	    model.addAttribute("prodaje", prodajaService.findByZaposleniId(zaposleniId));
	    return "prodaje";
	}
	
	@GetMapping("/prodaje/nova")
    public String prikaziFormu(Model model) {
        model.addAttribute("prodaja", new Prodaja());
        model.addAttribute("automobili", automobilService.findDostupniAutomobili());
        model.addAttribute("kupci", kupacService.findAll());
        model.addAttribute("zaposleni", zaposleniService.findAll());
        model.addAttribute("naciniPlacanja", NacinPlacanja.values());

        return "prodaja-form";
    }
	
	@PostMapping("/prodaje")
    public String sacuvajProdaju(@ModelAttribute Prodaja prodaja) {
        prodajaService.save(prodaja);
        return "redirect:/prodaje";
    }
	
	@PostMapping("/prodaje/obrisi/{id}")
    public String obrisiProdaju(@PathVariable Long id) {
        prodajaService.deleteById(id);
        return "redirect:/prodaje";
    }
	
}
