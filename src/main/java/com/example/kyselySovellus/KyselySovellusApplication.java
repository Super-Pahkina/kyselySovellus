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
			
			log.info("luodaan pari vastausta kyselyyn");
			Vastaus v1 = new Vastaus("Ryhmätyöt", eka);
			Vastaus v2 = new Vastaus("Kaikki oli ok", toka);
			Vastaus v3 = new Vastaus("Olin läsnä tunneilla", kolmas);
			Vastaus v4 = new Vastaus("4, mikään ei voi olla täydellinen", nelkku);
			Vastaus v5 = new Vastaus("5, paitsi minä", viides);
			
			Vastaus v11 = new Vastaus("Mielenkiintoinen aihe", eka);
			Vastaus v22 = new Vastaus("En keksi mitään", toka);
			Vastaus v33 = new Vastaus("Lintsasin jonkin verran", kolmas);
			Vastaus v44 = new Vastaus("4, ei valittamista", nelkku);
			Vastaus v55 = new Vastaus("3, en ansaitse parempaa", viides);
			
			log.info("tallennetaan vastaukset repoon");
			vastausrepo.save(v1);
			vastausrepo.save(v2);
			vastausrepo.save(v3);
			vastausrepo.save(v4);
			vastausrepo.save(v5);
			
			vastausrepo.save(v11);
			vastausrepo.save(v22);
			vastausrepo.save(v33);
			vastausrepo.save(v44);
			vastausrepo.save(v55);
			
		};
		
	}

}
