package com.example.kyselySovellus.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
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


@CrossOrigin
@Controller
public class KyselyController {	

		@Autowired
		private KyselyRepository kyselyRepository;
		
		@Autowired
		private KysymysRepository kysymysRepository;

		//Listaa kyselyt
		@RequestMapping("/")
		public String kyselyList(Model model) {
			model.addAttribute("kyselyt", kyselyRepository.findAll());
			return "kyselylist";
		}
	
		//Lisää kyselyn
		@RequestMapping(value = "/add")
		public String addKysely(Model model) {
			List<Kysymys> kysymykset = new ArrayList<Kysymys>();
			model.addAttribute("kysymykset", kysymykset);
			model.addAttribute("kysely", new Kysely());
			return "addkysely";
		}

		//tallentaa kyselyn
		@RequestMapping(value = "/savekysely", method = RequestMethod.POST)
		public String save(Kysely kysely, List<Kysymys> kysymykset) {
			kysely.setKysymykset(kysymykset);
			kyselyRepository.save(kysely);
			return "redirect:kyselylist";
		}

		//REST hakee kyselyn kysymykset
		@RequestMapping(value="/kyselyt/{id}/kysymykset", method = RequestMethod.GET)
		public @ResponseBody List<Kysymys> getKysymyksetRest(@PathVariable Long id) {
			Optional<Kysely> haettava = kyselyRepository.findById(id);
			List<Kysymys> kysymykset = haettava.get().getKysymykset();
			return kysymykset;
		}
}

