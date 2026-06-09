package rs.ac.singidunum.autosalon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rs.ac.singidunum.autosalon.model.Oprema;
import rs.ac.singidunum.autosalon.service.OpremaService;

@Controller
public class OpremaWebController {

	private final OpremaService opremaService;
	
	public OpremaWebController(OpremaService opremaService) {
		this.opremaService = opremaService;
	}
	
	@GetMapping("/oprema")
	public String prikaziOpremu(Model model) {
		model.addAttribute("oprema", opremaService.findAll());
		return "oprema";
	}
	
	@GetMapping("/oprema/nova")
	public String prikaziFormu(Model model) {
		model.addAttribute("opremaObjekat", new Oprema());
		return "oprema-form";
	}
	
	@GetMapping("/oprema/izmeni/{id}")
	public String prikaziFormuZaIzmenu(@PathVariable Long id, Model model) {
		model.addAttribute("opremaObjekat", opremaService.findById(id));
		return "oprema-form";
	}
	
	@PostMapping("/oprema")
    public String sacuvajOpremu(@ModelAttribute("opremaObjekat") Oprema oprema) {
        if (oprema.getId() != null) {
            opremaService.update(oprema.getId(), oprema);
        } else {
            opremaService.save(oprema);
        }

        return "redirect:/oprema";
    }
	
	@PostMapping("/oprema/obrisi/{id}")
	public String obrisiOpremu(@PathVariable Long id) {
		opremaService.deleteById(id);
		return "redirect:/oprema";
	}
}
