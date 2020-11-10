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
		return "addkysely";
	}
	
	// Lisää kysmäri
	@RequestMapping(value = "/addkysymys")
	public String addKysymys(Model model) {
		model.addAttribute("kysymys", new Kysymys());
		return "addkysymys";
	}
}
