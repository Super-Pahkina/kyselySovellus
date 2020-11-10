package com.example.kyselySovellus.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.kyselySovellus.domain.Kysely;
import com.example.kyselySovellus.domain.KyselyRepository;
import com.example.kyselySovellus.domain.Kysymys;
import com.example.kyselySovellus.domain.KysymysRepository;
import com.example.kyselySovellus.domain.Vastaus;
import com.example.kyselySovellus.domain.VastausRepository;

@CrossOrigin
@Controller
public class RESTController {
	
	@Autowired
	private KyselyRepository kyselyRepository;

	@Autowired
	private KysymysRepository kysymysRepository;
	
	@Autowired
	private VastausRepository vastausRepository;
	
	@RequestMapping("/resthome")
	public String resthome(Model model) {
		//model.addAttribute("kyselyt", kyselyRepository.findAll());
		return "resthome";
	}
	
	
	// REST hakee kaikki kyselyt
	@RequestMapping(value = "/kyselyt")
	public @ResponseBody List<Kysely> getAllKyselyt() {
		return (List<Kysely>) kyselyRepository.findAll();
	}

	// REST hakee yhden kyselyn
	@RequestMapping(value = "/kyselyt/{id}")
	public @ResponseBody Optional<Kysely> findKysely(@PathVariable Long id) {
		return kyselyRepository.findById(id);
	}

	// REST hakee kaikki kysymykset
	@RequestMapping(value = "/kysymykset")
	public @ResponseBody List<Kysymys> getAllKysymykset() {
		return (List<Kysymys>) kysymysRepository.findAll();
	}
	
	// REST hakee kaikki vastaukset tiettyyn kyselyyn
		@RequestMapping(value = "/kysely/{id}/vastaukset")
		public @ResponseBody List<Vastaus> getAllVastaukset(@PathVariable Long kyselyid) {
			Iterable<Vastaus> all = vastausRepository.findAll();
			List<Vastaus> kyselynVastaukset = new ArrayList<>();
			for (Vastaus vastaus : all) {
				if (vastaus.getKysymys().getKysely().getKysely_id() == kyselyid) {
					kyselynVastaukset.add(vastaus);
				}
			}
			return kyselynVastaukset;
		}

	// REST tietyn kyselyn kysymykset
	@RequestMapping(value = "kyselyt/{kyselyid}/kysymykset")
	public @ResponseBody List<Kysymys> getKyselynKysymykset(@PathVariable Long kyselyid) {
		Iterable<Kysymys> all = kysymysRepository.findAll();
		List<Kysymys> kyselynKysymykset = new ArrayList<>();
		for (Kysymys kysymys : all) {
			if (kysymys.getKysely().getKysely_id() == kyselyid) {
				kyselynKysymykset.add(kysymys);
			}
		}
		return kyselynKysymykset;
	}

}
