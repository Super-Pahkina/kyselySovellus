package com.example.kyselySovellus.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.kyselySovellus.domain.Vastaus;
import com.example.kyselySovellus.domain.VastausRepository;

@CrossOrigin
@Controller
public class VastausController {
	
	@Autowired
	private KyselyRepository kyselyRepository;

	@Autowired
	private KysymysRepository kysymysRepository;
	
	@Autowired
	private VastausRepository vastausRepository;
	
	@RequestMapping(value = "/savevastaus", method = RequestMethod.POST)
	public String save(Model model, ArrayList<Vastaus> vastaukset) {
		for (Vastaus vastaus : vastaukset) {
			vastausRepository.save(vastaus);
		}
		return "redirect:/";
	}
}
