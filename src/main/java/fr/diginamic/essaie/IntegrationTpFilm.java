package fr.diginamic.essaie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.entite.Acteur;
import fr.diginamic.entite.Film;
import fr.diginamic.entite.Genre;
import fr.diginamic.entite.Langue;
import fr.diginamic.entite.Pays;
import fr.diginamic.entite.Realisateur;
import fr.diginamic.service.LecteurCsv;

public class IntegrationTpFilm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cinema");
//		EntityManager em = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = em.getTransaction();
//		
//		transaction.begin();
//		transaction.commit();
		
		
		String fichierActeur = "acteurs.csv";
		String fichierCastingPrincipal = "castingPrincipal.csv";
		String fichierFilmRealisateur = "film_realisateur.csv";
		String fichierFilm = "films.csv";
		String fichierPays = "pays.csv";
		String fichierRealisateur = "realisateurs.csv";
		String fichierRole = "roles.csv";
		
		LecteurCsv lecteurCsv = new LecteurCsv();
		
		
		List<Pays> arrayPays = lecteurCsv.parsePays(fichierPays);
		List<Acteur> arrayActeur = lecteurCsv.parseActeur(fichierActeur);
		List<Realisateur> arrayRealisateur = lecteurCsv.parseRealisateur(fichierRealisateur);
//		List<Genre> arrayGenre = lecteurCsv.parseGenre(fichierFilm);
		List<Film> arrayFilm = lecteurCsv.parseFilm(fichierFilm, fichierPays);
//		List<Langue> arrayLangue = lecteurCsv.parseLangue(fichierFilm);
		System.out.println(arrayFilm);
		

//		String[] tokens = afficherFichier.split("\\|", -1);
	}

}
