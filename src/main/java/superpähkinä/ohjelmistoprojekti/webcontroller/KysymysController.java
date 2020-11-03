package superpähkinä.ohjelmistoprojekti.webcontroller;

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

import superpähkinä.ohjelmistoprojekti.domain.Kysely;
import superpähkinä.ohjelmistoprojekti.domain.KyselyRepository;
import superpähkinä.ohjelmistoprojekti.domain.Kysymys;
import superpähkinä.ohjelmistoprojekti.domain.KysymysRepository;

@Controller
public class KysymysController {

	@Autowired
	private KyselyRepository kyselyRepository;
	
	@Autowired
	private KysymysRepository kysymysRepository;
	
	// tallentaa kyssärin
	@RequestMapping(value = "/saveKysymys", method = RequestMethod.POST)
	public String save(Kysymys kysymys, List<Kysymys> kysymykset, Model model) {
		kysymykset.add(kysymys);
		model.addAttribute("kysymykset", kysymykset);
		return "redirect:addKysymys";
	}
	
	// Lisää kysmäri
	@RequestMapping(value = "/addKysymys")
	public String addKysymys(Model model) {
		model.addAttribute("kysymys", new Kysymys());
		return "addKysymys";
	}
}
