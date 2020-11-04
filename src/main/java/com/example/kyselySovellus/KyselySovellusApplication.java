package com.example.kyselySovellus;


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
			
			log.info("luodaan kysely");
			Kysely ekakysely = new Kysely("Kulttuurivinkkaus-kysely Syksy 2020", "Tällä kyselyllä selvitetään tuttujen suosittelemia tärppejä, joilla voi laajentaa tajuntaansa.", null);
			kyselyrepo.save(ekakysely);
			
			log.info("luodaan toinen kysely");
			Kysely tokakysely = new Kysely("Koirakysely", "Tällä kyselyllä kysellään koirista", null);
			kyselyrepo.save(tokakysely);
			
			log.info("luodaan pari kysymystä");
			Kysymys eka = new Kysymys("teksti",true, "Mitä elokuvaa suosittelet?", ekakysely);
			Kysymys toka = new Kysymys("teksti",true, "Mitä tv-sarjaa suosittelet?", ekakysely);
			Kysymys kolmas = new Kysymys("teksti",true, "Mitä musiikkia suosittelet?", ekakysely);
			Kysymys neljas = new Kysymys("teksti",true, "Mitä koiranpentua suosittelet?", tokakysely);
			
			log.info("tallennetaan kysymykset repoon");
			kysymysrepo.save(eka);
			kysymysrepo.save(toka);
			kysymysrepo.save(kolmas);
			kysymysrepo.save(neljas);
			
		};
		
	}

}
