package superpähkinä.ohjelmistoprojekti.webcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import superpähkinä.ohjelmistoprojekti.domain.Kysely;
import superpähkinä.ohjelmistoprojekti.domain.KyselyRepository;
import superpähkinä.ohjelmistoprojekti.domain.Kysymys;
import superpähkinä.ohjelmistoprojekti.domain.KysymysRepository;


@CrossOrigin
@Controller
public class KyselyController {	

		@Autowired
		private KyselyRepository kyselyRepository;
		
		@Autowired
		private KysymysRepository kysymysRepository;

		//Listaa kyselyt
		@RequestMapping("/kyselylist")
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
		public String save(Kysely kysely) {
			kyselyRepository.save(kysely);
			return "redirect:kyselylist";
		}
		//tallentaa kyssärin
		@RequestMapping(value = "/savekysymys", method = RequestMethod.POST)
		public String save(Kysymys kysymys) {
			kysymysRepository.save(kysymys);
			return "redirect:addkysely";
	}
}

