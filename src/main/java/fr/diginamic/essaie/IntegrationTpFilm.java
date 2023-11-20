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
import fr.diginamic.entite.LieuNaissance;
import fr.diginamic.entite.Pays;
import fr.diginamic.entite.Realisateur;
import fr.diginamic.entite.Role;
import fr.diginamic.service.LecteurCsv;

public class IntegrationTpFilm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String fichierActeur = "acteurs.csv";
		String fichierCastingPrincipal = "castingPrincipal.csv";
		String fichierFilmRealisateur = "film_realisateurs.csv";
		String fichierFilm = "films.csv";
		String fichierPays = "pays.csv";
		String fichierRealisateur = "realisateurs.csv";
		String fichierRole = "roles.csv";
		
		LecteurCsv lecteurCsv = new LecteurCsv();
		
		
		List<Pays> arrayPays = lecteurCsv.parsePays(fichierPays);
		List<Genre> arrayGenre = lecteurCsv.parseGenre(fichierFilm);
		List<Langue> arrayLangue = lecteurCsv.parseLangue(fichierFilm);
		List<LieuNaissance> arrayLieuNaissance = lecteurCsv.parseLieuNaissance(fichierRealisateur, fichierActeur);
		List<Acteur> arrayActeur = lecteurCsv.parseActeur(fichierActeur, arrayLieuNaissance);
		List<Realisateur> arrayRealisateur = lecteurCsv.parseRealisateur(fichierRealisateur, arrayLieuNaissance);
		List<Film> arrayFilm = lecteurCsv.parseFilm(fichierFilm, arrayPays, arrayLangue, arrayGenre);
		List<Role> arrayRole = lecteurCsv.parseRole(fichierRole, arrayActeur, arrayFilm);
		
		
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		for (Pays pays : arrayPays) {
			em.persist(pays);
		}
		
		for (Genre genres : arrayGenre) {
			em.persist(genres);
		}
		
		for (Langue langues : arrayLangue) {
			em.persist(langues);
		}
		
		for (LieuNaissance lieuNaissance : arrayLieuNaissance) {
			em.persist(lieuNaissance);
		}
		
		for (Acteur acteurs : arrayActeur) {
			em.persist(acteurs);
		}
		
		for (Realisateur realisateurs : arrayRealisateur) {
			em.persist(realisateurs);
		}
		
		for (Film films : arrayFilm) {
			em.persist(films);
		}
		
		for (Role roles : arrayRole) {
			em.persist(roles);
		}
		
		lecteurCsv.parseFilmRealisateur(fichierFilmRealisateur, arrayRealisateur, arrayFilm);
		lecteurCsv.parseCastingPrincipal(fichierCastingPrincipal, arrayActeur, arrayFilm);
		
		transaction.commit();
		

	}

}
