package com.example.kyselySovellus.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.kyselySovellus.domain.Kysely;
import com.example.kyselySovellus.domain.KyselyRepository;
import com.example.kyselySovellus.domain.Kysymys;
import com.example.kyselySovellus.domain.KysymysRepository;

@CrossOrigin
@Controller
public class KysymysController {

	@Autowired
	private KyselyRepository kyselyRepository;
	
	@Autowired
	private KysymysRepository kysymysRepository;
	
	// tallentaa kyssärin
	@RequestMapping(value = "/ohjaa", method = RequestMethod.POST)
	public String tyyppi(Model model, Kysymys kysymys) {
		if (kysymys.getTyyppi().equals("teksti")) {
			kysymysRepository.save(kysymys);
			model.addAttribute("kysely", kysymys.getKysely());
			model.addAttribute("kysymykset", kysymys.getKysely().getKysymykset());
			Kysymys uusiKysymys = new Kysymys();
			uusiKysymys.setKysely(kysymys.getKysely());
			model.addAttribute("kysymys", uusiKysymys);
			return "addkysymys";
		} else if (kysymys.getTyyppi().equals("checkbox") || kysymys.getTyyppi().equals("radionappula")){
			kysymysRepository.save(kysymys);
			model.addAttribute("kysymys", kysymys);
			return "monivalinta";
		}
		return "addkysymys";
	}
	
	@RequestMapping(value = "/monivalinta", method = RequestMethod.POST)
	public String monivalinta(@RequestParam String vaihtoehto, @RequestParam(value = "valmis" , required=false)String valmis, Model model, Kysymys kysymys) {
		if (valmis == null) {
			if (kysymys.getMonivalinta() == null) {
				List<String> lista = new ArrayList<>();
				lista.add(vaihtoehto);
				kysymys.setMonivalinta(lista);
			} else {
				List<String> lista = kysymys.getMonivalinta();
				lista.add(vaihtoehto);
				kysymys.setMonivalinta(lista);
			}
			model.addAttribute("kysymys", kysymys);
			kysymysRepository.save(kysymys);
			return "monivalinta";
		} else {
			model.addAttribute("kysely", kysymys.getKysely());
			model.addAttribute("kysymykset", kysymys.getKysely().getKysymykset());
			Kysymys uusiKysymys = new Kysymys();
			uusiKysymys.setKysely(kysymys.getKysely());
			model.addAttribute("kysymys", uusiKysymys);
			return "addkysymys";
		}
		
	}
	
	
	@RequestMapping(value = "/tyyppi", method = RequestMethod.POST)
	public String tyyppi(@RequestParam String tyyppi, Kysymys kysymys, Model model) {
		kysymys.setTyyppi(tyyppi);
		model.addAttribute("kysymys", kysymys);
		return "tekstikysymys";
	}
	
	@RequestMapping(value = "/savekysymys", method = RequestMethod.POST)
	public String save(Kysymys kysymys, Model model) {
		if (!kysymys.getTeksti().equals("")) {
			kysymysRepository.save(kysymys);
		}
		Iterable<Kysymys> all = kysymysRepository.findAll();
		List<Kysymys> kyselynKysymykset= new ArrayList<>(); 
		for(Kysymys kysymys2 : all) {
			if(kysymys2.getKysely().getKysely_id() == kysymys.getKysely().getKysely_id()) {
				kyselynKysymykset.add(kysymys2);
			}
		}
		Optional<Kysely> kyselyopt = kyselyRepository.findById(kysymys.getKysely().getKysely_id());
		Kysely kysely = kyselyopt.get();
		Kysymys kysymys3 = new Kysymys ("", kysely);
		model.addAttribute("kysymys", kysymys3);
		model.addAttribute("kysymykset", kyselynKysymykset);
		return "addkysymys";
	}
	
	//Tallentaa muokatun kysymyksen
	@RequestMapping(value = "/editkysymys", method = RequestMethod.POST)
	public String edit(Kysymys kysymys, Model model, @RequestParam(value = "i") String i) {
		Long id = Long.parseLong(i);
		Optional<Kysymys> kyselyopt2 = kysymysRepository.findById(id);
		Kysymys kysmäri = kyselyopt2.get();
		kysmäri.setTeksti(kysymys.getTeksti());
		System.out.println(kysmäri.getKysymys_id());
		kysymysRepository.save(kysmäri);
		Iterable<Kysymys> all = kysymysRepository.findAll();
		List<Kysymys> kyselynKysymykset= new ArrayList<>(); 
		for(Kysymys kysymys2 : all) {
			if(kysymys2.getKysely().getKysely_id() == kysmäri.getKysely().getKysely_id()) {
				kyselynKysymykset.add(kysymys2);
			}
		}
		Optional<Kysely> kyselyopt = kyselyRepository.findById(kysmäri.getKysely().getKysely_id());
		Kysely kysely = kyselyopt.get();
		Kysymys kysymys3 = new Kysymys ("", kysely);
		model.addAttribute("kysely", kysely);
		model.addAttribute("kysymys", kysymys3);
		model.addAttribute("kysymykset", kyselynKysymykset);
		return "addkysymys";
	}
	
	//Valitsee muokattavan kysymyksen
	@RequestMapping(value = "/aloitamuokkaus/{id}")
	public String aloitaMuokkaus(@PathVariable("id") Long kysymys_id, Model model) {
		Optional<Kysymys> kyselyopt2 = kysymysRepository.findById(kysymys_id);
		Kysymys kysmäri = kyselyopt2.get();
		Iterable<Kysymys> all = kysymysRepository.findAll();
		List<Kysymys> kyselynKysymykset= new ArrayList<>(); 
		for(Kysymys kysymys2 : all) {
			if(kysymys2.getKysely().getKysely_id() == kysmäri.getKysely().getKysely_id()) {
				kyselynKysymykset.add(kysymys2);
			}
		}
		Optional<Kysely> kyselyopt = kyselyRepository.findById(kysmäri.getKysely().getKysely_id());
		Kysely kysely = kyselyopt.get();
		model.addAttribute("i", kysymys_id);
		model.addAttribute("kysely", kysely);
		model.addAttribute("kysymys", kysmäri);
		model.addAttribute("kysymykset", kyselynKysymykset);
		return "addkysymys";
	}
	
	//Poista monivalintavaihtoehto kysymyksestä
	@RequestMapping(value = "/deletemonivalinta/{id}", method = RequestMethod.POST)
	public String poistaMonivalinta(@PathVariable("id") Long kysymys_id, Model model, @RequestParam(value = "vaihtoehto")String vaihtoehto) {
		Optional<Kysymys> kyselyopt2 = kysymysRepository.findById(kysymys_id);
		Kysymys kysmäri = kyselyopt2.get();
		List<String> lista = kysmäri.getMonivalinta();
		int a = 0; 
		while (a < lista.size()) {
			if (lista.get(a).equals(vaihtoehto)) {
				lista.remove(a);
			}
			a++;
		}
		kysmäri.setMonivalinta(lista);
		kysymysRepository.save(kysmäri);
		Iterable<Kysymys> all = kysymysRepository.findAll();
		List<Kysymys> kyselynKysymykset= new ArrayList<>(); 
		for(Kysymys kysymys : all) {
			if(kysymys.getKysely().getKysely_id() == kysmäri.getKysely().getKysely_id()) {
				kyselynKysymykset.add(kysymys);
			}
		}
		Optional<Kysely> kyselyopt = kyselyRepository.findById(kysmäri.getKysely().getKysely_id());
		Kysely kysely = kyselyopt.get();
		model.addAttribute("i", kysymys_id);
		model.addAttribute("kysely", kysely);
		model.addAttribute("kysymys", kysmäri);
		model.addAttribute("kysymykset", kyselynKysymykset);
		
		
		return "addkysymys";
	}
	
	//Lisää monivalintavaihtoehto kysymykseen
		@RequestMapping(value = "/lisaamonivalinta/{id}", method = RequestMethod.POST)
		public String lisaaMonivalinta(@PathVariable("id") Long kysymys_id, Model model, @RequestParam(value = "vaihtoehto")String vaihtoehto) {
			Optional<Kysymys> kyselyopt2 = kysymysRepository.findById(kysymys_id);
			Kysymys kysmäri = kyselyopt2.get();
			List<String> lista = kysmäri.getMonivalinta();
			lista.add(vaihtoehto);
			kysmäri.setMonivalinta(lista);
			kysymysRepository.save(kysmäri);
			Iterable<Kysymys> all = kysymysRepository.findAll();
			List<Kysymys> kyselynKysymykset= new ArrayList<>(); 
			for(Kysymys kysymys2 : all) {
				if(kysymys2.getKysely().getKysely_id() == kysmäri.getKysely().getKysely_id()) {
					kyselynKysymykset.add(kysymys2);
				}
			}
			Optional<Kysely> kyselyopt = kyselyRepository.findById(kysmäri.getKysely().getKysely_id());
			Kysely kysely = kyselyopt.get();
			model.addAttribute("i", kysymys_id);
			model.addAttribute("kysely", kysely);
			model.addAttribute("kysymys", kysmäri);
			model.addAttribute("kysymykset", kyselynKysymykset);
			
			
			return "addkysymys";
		}
			
	
	
	// Lisää kysmäri
	@RequestMapping(value = "/addkysymys")
	public String addKysymys(Model model, Long kysely_id) {
		model.addAttribute("kysymys", new Kysymys());
		return "addkysymys";
	}
	
	@RequestMapping(value = "/deleteKyssari/{id}", method = RequestMethod.GET)
	public String deleteKysymys(@PathVariable("id") Long kysymys_id, Model model) {
		Optional<Kysymys> kysymysopt = kysymysRepository.findById(kysymys_id);
		Kysymys kysymys = kysymysopt.get();
		Kysely kysely = kysymys.getKysely();
		kysymysRepository.deleteById(kysymys_id);
		return "redirect:/edit/" + kysely.getKysely_id();
	}
}
