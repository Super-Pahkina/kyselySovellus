package superpähkinä.ohjelmistoprojekti.webcontroller;

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
		public String bookList(Model model) {
			model.addAttribute("kyselyt", kyselyRepository.findAll());
			return "kyselylist";
		}
	
		//Lisää kyselyn
		@RequestMapping(value = "/add")
		public String addKysely(Model model) {
			model.addAttribute("kysely", new Kysely());
			model.addAttribute("kysymys", kysymysRepository.findAll());
			return "addkysely";
		}

		//tallentaa kyselyn
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(Kysely kysely) {
			kyselyRepository.save(kysely);
			return "redirect:kyselylist";
		}	
	}


