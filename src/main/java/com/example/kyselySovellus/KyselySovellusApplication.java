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
			
			List<String> lista = new ArrayList<String>();
			String vaihtoehto1 = "1";
			String vaihtoehto2 = "2";
			String vaihtoehto3 = "3";
			String vaihtoehto4 = "4";
			String vaihtoehto5 = "5";
			lista.add(vaihtoehto1);
			lista.add(vaihtoehto2);
			lista.add(vaihtoehto3);
			lista.add(vaihtoehto4);
			lista.add(vaihtoehto5);
			
			
			log.info("luodaan pari kysymystä");
			Kysymys eka = new Kysymys("Mikä kurssissa oli mielestäsi hyvää?", "teksti",true, ekakysely);
			Kysymys toka = new Kysymys("Mitä voisi tehdä paremmin?", "teksti",true, ekakysely);
			Kysymys kolmas = new Kysymys("Osallistuitko aktiivisesti?", "teksti",true,  ekakysely);
			Kysymys nelkku = new Kysymys("Minkä arvosanan 1-5 antaisit kurssille?", "radionappula",true, ekakysely);
			Kysymys viides = new Kysymys("Minkä arvosanan 1-5 antaisit itsellesi?", "checkbox",true, ekakysely);
			nelkku.setMonivalinta(lista);
			viides.setMonivalinta(lista);
			
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
			Vastaus v4 = new Vastaus("4", nelkku);
			Vastaus v5 = new Vastaus("5", viides);
			
			Vastaus v11 = new Vastaus("Mielenkiintoinen aihe", eka);
			Vastaus v22 = new Vastaus("En keksi mitään", toka);
			Vastaus v33 = new Vastaus("Lintsasin jonkin verran", kolmas);
			Vastaus v44 = new Vastaus("4", nelkku);
			Vastaus v55 = new Vastaus("3", viides);
			
			
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
			Kysymys nelkku2 = new Kysymys("Minkä arvosanan 1-5 antaisit työharjoittelulle?", "radionappula",true, tokakysely);
			Kysymys viides2 = new Kysymys("Minkä arvosanan 1-5 antaisit itsellesi?", "checkbox",true, tokakysely);
			nelkku2.setMonivalinta(lista);
			viides2.setMonivalinta(lista);
			
			log.info("tallennetaan kysymykset repoon");
			kysymysrepo.save(eka2);
			kysymysrepo.save(toka2);
			kysymysrepo.save(kolmas2);
			kysymysrepo.save(nelkku2);
			kysymysrepo.save(viides2);
			
			log.info("luodaan pari vastausta kyselyyn");
			Vastaus v16 = new Vastaus("Hyvä palkka", eka2);
			Vastaus v26 = new Vastaus("Olla ajoissa töissä", toka2);
			Vastaus v36 = new Vastaus("Keitin kahvia", kolmas2);
			Vastaus v46 = new Vastaus("4", nelkku2);
			Vastaus v56 = new Vastaus("5", viides2);
			
			Vastaus v17 = new Vastaus("Kivat työkaverit", eka2);
			Vastaus v27 = new Vastaus("En keksi mitään", toka2);
			Vastaus v37 = new Vastaus("Join kahvia", kolmas2);
			Vastaus v47 = new Vastaus( "4", nelkku2);
			Vastaus v57 = new Vastaus("3", viides2);
			
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
			
			// **************JOULUKYSELY**ALKAA*********
			
			log.info("luodaan joulukysely");
			Kysely joulukysely = new Kysely("** Joulukysely **", "Tällä kyselyllä selvitetään kansalaisten joulutottumuksia ja tapoja", null);
			kyselyrepo.save(joulukysely);
			
			log.info("Luodaan ja tallennetaan joulukyselyn kysymykset");
			Kysymys parasta = new Kysymys("Mikä on mielestäsi parasta joulussa?", "teksti", true, joulukysely);
			kysymysrepo.save(parasta);
			
			Kysymys herkut = new Kysymys("Mitkä ovat lempi jouluherkkujasi? (voit valita useamman vaihtoehdon)", "checkbox", true, joulukysely);
			List<String> herkku = new ArrayList<String>();
			String joulutorttu = "Joulutorttu";
			String joululaatikot = "Joululaatikot";
			String kinkku = "Kinkku";
			String riisipuuro = "Riisipuuro";
			String pipari = "Pipari";
			String graavilohi = "Graavilohi";
			String suklaakonvehdit = "Suklaakonvehdit";
			String vihreatkuulat = "Vihreät kuulat";
			String keitettyperuna = "Keitetty peruna";
			String muu = "Muu";
			herkku.add(joulutorttu);
			herkku.add(joululaatikot);
			herkku.add(kinkku);
			herkku.add(riisipuuro);
			herkku.add(pipari);
			herkku.add(graavilohi);
			herkku.add(suklaakonvehdit);
			herkku.add(vihreatkuulat);
			herkku.add(keitettyperuna);
			herkku.add(muu);
			herkut.setMonivalinta(herkku);
			kysymysrepo.save(herkut);
			
			Kysymys luona = new Kysymys("Matkustatko joulunviettoon vai vietätkö joulun kotona?", "radionappula", true, joulukysely);
			List<String> vietto = new ArrayList<String>();
			String kotona = "Joulu kotona";
			String suku = "Sukulaisten luona";
			String tutut = "Muiden tuttujen luona";
			String enkerro = "En halua kertoa";
			vietto.add(kotona);
			vietto.add(suku);
			vietto.add(tutut);
			vietto.add(enkerro);
			luona.setMonivalinta(vietto);
			kysymysrepo.save(luona);
			
			Kysymys lahjatoive = new Kysymys("Mikä olisi sinun unelmiesi joululahja?", "teksti", true, joulukysely);
			kysymysrepo.save(lahjatoive);
			
			Kysymys valkoinen = new Kysymys("Kuinka tärkeänä pidät valkoista joulua? (1 = vähän -> 5 = erittäin)", "skaala", true, joulukysely);
			List<String> prio = new ArrayList<String>();
			kysymysrepo.save(valkoinen);
			
			Kysymys glogi = new Kysymys("Miten juot glögisi? (voit valita useamman vaihtoehdon)", "checkbox", true, joulukysely);
			List<String> lisuke = new ArrayList<String>();
			String rusina = "Rusinoilla";
			String manteli = "Mantelilastuilla";
			String viini = "Hehkuviinillä";
			String raaka = "Sellaisenaan";
			String muuta = "Jotakin muuta";
			String eijuo = "En juo glögiä";
			lisuke.add(rusina);
			lisuke.add(manteli);
			lisuke.add(viini);
			lisuke.add(raaka);
			lisuke.add(muuta);
			lisuke.add(eijuo);
			glogi.setMonivalinta(lisuke);
			kysymysrepo.save(glogi);
			
			// **************JOULUKYSELY**LOPPUU****************
		};
		
	}

}
