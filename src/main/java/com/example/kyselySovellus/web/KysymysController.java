package com.example.kyselySovellus.web;

import java.util.ArrayList;

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

@Controller
public class KysymysController {

	@Autowired
	private KyselyRepository kyselyRepository;
	
	@Autowired
	private KysymysRepository kysymysRepository;
	
	// tallentaa kyssärin
	@RequestMapping(value = "/savekysymys", method = RequestMethod.POST)
	public String save(ArrayList<Kysymys> kysymykset, Kysymys kysymys, Kysely kysely, Model model) {
		Kysymys kysymys2 = new Kysymys(kysymys.getTeksti(), kysely);
		kysymysRepository.save(kysymys2);
		System.out.println(kysymykset.size());
		model.addAttribute("kysely", kysely);
		model.addAttribute("kysymykset", kysymykset);
		return "redirect:addkysely";
	}
	
	// Lisää kysmäri
	@RequestMapping(value = "/addkysymys")
	public String addKysymys(Model model) {
		model.addAttribute("kysymys", new Kysymys());
		return "addkysymys";
	}
}
