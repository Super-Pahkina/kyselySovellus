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
import com.example.kyselySovellus.domain.Vastaus;
import com.example.kyselySovellus.domain.VastausRepository;

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
	public CommandLineRunner demo(KyselyRepository kyselyrepo, KysymysRepository kysymysrepo, VastausRepository vastausrepo) {
		return(args)-> {
			
			log.info("luodaan kysely");
			Kysely ekakysely = new Kysely("Kurssipalautekysely", "Tällä kyselyllä selvitetään opiskelijoiden kokemuksia kurssista X.", null);
			kyselyrepo.save(ekakysely);
			
			
			log.info("luodaan pari kysymystä");
			Kysymys eka = new Kysymys("Mikä kurssissa oli mielestäsi hyvää?", "teksti",true, ekakysely);
			Kysymys toka = new Kysymys("Mitä voisi tehdä paremmin?", "teksti",true, ekakysely);
			Kysymys kolmas = new Kysymys("Osallistuitko aktiivisesti?", "teksti",true,  ekakysely);
			Kysymys nelkku = new Kysymys("Minkä arvosanan 1-5 antaisit kurssille, miksi?", "teksti",true, ekakysely);
			Kysymys viides = new Kysymys("Minkä arvosanan 1-5 antaisit itsellesi, miksi?", "teksti",true, ekakysely);
			
			log.info("tallennetaan kysymykset repoon");
			kysymysrepo.save(eka);
			kysymysrepo.save(toka);
			kysymysrepo.save(kolmas);
			kysymysrepo.save(nelkku);
			kysymysrepo.save(viides);
			
			log.info("luodaan pari vastausta");
			Vastaus n1 = new Vastaus("Joo", eka);
			Vastaus n2 = new Vastaus("Sitä", toka);
			Vastaus n3 = new Vastaus("E", kolmas);
			Vastaus n4 = new Vastaus("1, siks", nelkku);
			Vastaus n5 = new Vastaus("5, just siks", viides);
			
			log.info("tallennetaan vastaukset repoon");
			vastausrepo.save(n1);
			vastausrepo.save(n2);
			vastausrepo.save(n3);
			vastausrepo.save(n4);
			vastausrepo.save(n5);
			
		};
		
	}

}
