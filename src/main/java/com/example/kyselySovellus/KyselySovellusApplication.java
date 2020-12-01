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
			
			Kysely tokakysely = new Kysely("Työharjoittelukysely", "Tällä kyselyllä selvitetään opiskelijoiden kokemuksia työharjoittelusta", null);
			kyselyrepo.save(tokakysely);
			
			log.info("luodaan pari kysymystä");
			Kysymys eka2 = new Kysymys("Mikä työharjoittelussa oli mielestäsi hyvää?", "teksti",true, tokakysely);
			Kysymys toka2 = new Kysymys("Mitä voisi tehdä paremmin?", "teksti",true, tokakysely);
			Kysymys kolmas2 = new Kysymys("Osallistuitko aktiivisesti?", "teksti",true,  tokakysely);
			Kysymys nelkku2 = new Kysymys("Minkä arvosanan 1-5 antaisit työharjoittelulle, miksi?", "teksti",true, tokakysely);
			Kysymys viides2 = new Kysymys("Minkä arvosanan 1-5 antaisit itsellesi, miksi?", "teksti",true, tokakysely);
			
			log.info("tallennetaan kysymykset repoon");
			kysymysrepo.save(eka2);
			kysymysrepo.save(toka2);
			kysymysrepo.save(kolmas2);
			kysymysrepo.save(nelkku2);
			kysymysrepo.save(viides2);
			
			log.info("luodaan pari vastausta kyselyyn");
			Vastaus v16 = new Vastaus("Hyvä palkka", eka);
			Vastaus v26 = new Vastaus("Olla ajoissa töissä", toka);
			Vastaus v36 = new Vastaus("Keitin kahvia", kolmas);
			Vastaus v46 = new Vastaus("4, mikään ei voi olla täydellinen", nelkku);
			Vastaus v56 = new Vastaus("5, olin tosi taitava kaikessa", viides);
			
			Vastaus v17 = new Vastaus("Kivat työkaverit", eka);
			Vastaus v27 = new Vastaus("En keksi mitään", toka);
			Vastaus v37 = new Vastaus("Join kahvia", kolmas);
			Vastaus v47 = new Vastaus("4, ei valittamista", nelkku);
			Vastaus v57 = new Vastaus("3, en ansaitse parempaa", viides);
			
			log.info("tallennetaan vastaukset repoon");
			vastausrepo.save(v16);
			vastausrepo.save(v26);
			vastausrepo.save(v36);
			vastausrepo.save(v46);
			vastausrepo.save(v56);
			
			vastausrepo.save(v17);
			vastausrepo.save(v27);
			vastausrepo.save(v37);
			vastausrepo.save(v47);
			vastausrepo.save(v57);
		};
		
	}

}
