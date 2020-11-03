package com.example.kyselySovellus;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.example.kyselySovellus.domain.Kysely;
import com.example.kyselySovellus.domain.KyselyRepository;
import com.example.kyselySovellus.domain.Kysymys;
import com.example.kyselySovellus.domain.KysymysRepository;

@SpringBootApplication
public class KyselySovellusApplication extends SpringBootServletInitializer {
	private static final Logger log = LoggerFactory.getLogger(KyselySovellusApplication.class);

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(KyselySovellusApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KyselySovellusApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(KyselyRepository kyselyrepo, KysymysRepository kysymysrepo) {
		return(args)-> {
			
			log.info("saving questions");
			//String tyyppi, Boolean vaadittu, String kysymys, Ky
			/*kysymysrepo.save(new Kysymys("teksti",true, "Mitä elokuvaa suosittelet?", new Kysely()));
			kysymysrepo.save(new Kysymys("teksti",true, "Mitä tv-sarjaa suosittelet?", new Kysely()));
			kysymysrepo.save(new Kysymys("teksti",true, "Mitä musiikkia suosittelet?", new Kysely()));
			*/
			/* luodaan ensin tyhjä kysely
			 * luodaan kysymys kerrallaan, kysymyksellä on viittaus kyselyyn
			 */
			List<Kysymys> kysymykset=new ArrayList<>();
			kysymykset.add(new Kysymys("teksti",true, "Mitä elokuvaa suosittelet?"));
			kysymykset.add(new Kysymys("teksti",true, "Mitä tv-sarjaa suosittelet?"));
			kysymykset.add(new Kysymys("teksti",true, "Mitä musiikkia suosittelet?"));
		
			log.info("saving kysely");
			kyselyrepo.save(new Kysely("Kulttuurivinkkaus-kysely Syksy 2020", "Tällä kyselyllä selvitetään tuttujen suosittelemia tärppejä, joilla voi laajentaa tajuntaansa.", kysymykset));
			
		};
		
	}

}
