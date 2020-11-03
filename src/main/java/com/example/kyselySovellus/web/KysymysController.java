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
	public String save(Kysymys kysymys, Kysely kysely, List<Kysymys> kysymykset, Model model) {
		kysymykset.add(kysymys);
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