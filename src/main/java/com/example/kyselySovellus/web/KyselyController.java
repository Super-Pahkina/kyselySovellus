package com.example.kyselySovellus.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Iterator;

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
			model.addAttribute("kysely", new Kysely());
			return "luokysely";
		}

		//tallentaa kyselyn
		@RequestMapping(value = "/savekysely", method = RequestMethod.POST)
		public String save(Model model, Kysely kysely) {
			kyselyRepository.save(kysely);
			Kysymys kysymys = new Kysymys("testi", kysely);
			kysymysRepository.save(kysymys);
			model.addAttribute(kysely);
			model.addAttribute("kysymys", new Kysymys());
			return "redirect:/";
		}

		//REST hakee kaikki kyselyt
		@RequestMapping(value="/kyselyt")
		public @ResponseBody List<Kysely> getAllKyselyt(){
			return (List<Kysely>) kyselyRepository.findAll();
		}
		
		//REST hakee yhden kyselyn
		@RequestMapping(value="/kyselyt/{id}")
		public @ResponseBody Optional<Kysely> findKysely(@PathVariable Long id){
			return kyselyRepository.findById(id);
		}
		
		@RequestMapping(value = "/edit/{id}")
		public String editKysely(@PathVariable("id") Long kysely_id, Model model) {
			Iterable<Kysymys> all = kysymysRepository.findAll();
			List<Kysymys> kyselynKysymykset= new ArrayList<>(); 
			for(Kysymys kysymys : all) {
				if(kysymys.getKysely().getKysely_id() == kysely_id) {
					kyselynKysymykset.add(kysymys);
				}
			}
			Optional<Kysely> kyselyopt = kyselyRepository.findById(kysely_id);
			Kysely kysely = kyselyopt.get();
			Kysymys kysymys = new Kysymys ("", kysely);
			System.out.println(kysymys.getKysymys_id());
			System.out.println(kysymys.getTeksti());
			System.out.println(kysymys.getKysely());
			model.addAttribute("kysymys", kysymys);
			model.addAttribute("kysymykset", kyselynKysymykset);
			return "addkysely";
		}
		
		//REST hakee kaikki kysymykset
		@RequestMapping(value="/kysymykset")
		public @ResponseBody List<Kysymys> getAllKysymykset(){
			return (List<Kysymys>) kysymysRepository.findAll();
		}
		
		//REST tietyn kyselyn kysymykset
		@RequestMapping(value ="/kysymykset/{kyselyid}")
		public @ResponseBody List<Kysymys> getKyselynKysymykset(@PathVariable Long kyselyid){
			Iterable<Kysymys> all = kysymysRepository.findAll();
			List<Kysymys> kyselynKysymykset= new ArrayList<>(); 
			for(Kysymys kysymys : all) {
				if(kysymys.getKysely().getKysely_id() == kyselyid) {
					kyselynKysymykset.add(kysymys);
				}
			}
			return kyselynKysymykset;
		}
		
		
	
}

