package fr.diginamic.essaie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.service.LecteurCsvFilm;

public class IntegrationTpFilm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cinema");
//		EntityManager em = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = em.getTransaction();
//		
//		transaction.begin();
//		transaction.commit();
		
		
		String fichierFilm = "film.csv";
		
		LecteurCsvFilm lecteurCsv = new LecteurCsvFilm();
		lecteurCsv.lectureFichier(fichierFilm);
		
		System.out.println(lecteurCsv.lectureFichier(fichierFilm));

	}

}
