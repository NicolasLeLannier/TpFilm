/**
 * 
 */
package fr.diginamic.dao.jdbc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.dao.FilmDao;
import fr.diginamic.entite.Acteur;
import fr.diginamic.entite.Film;

/**
 * @author lelan
 *
 */
public class FilmDaoJdbc implements FilmDao {

	/** em */
	private EntityManager em;

	/** Constructor
	 * @param em
	 */
	public FilmDaoJdbc(EntityManager em) {
		this.em = em;
	}

	/**
	 *
	 */
	@Override
	public List<Film> getFilmByActeur(Acteur acteur) {
		TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f JOIN f.acteur a WHERE a = :acteur", Film.class);
		query.setParameter("acteur", acteur);
		List<Film> listeFilms = query.getResultList();
		
		return listeFilms;
	}

	/**
	 *
	 */
	public Film getFilmByName(String nom) {		
		TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.nom = :nom", Film.class);
		query.setParameter("nom", nom);
		List<Film> film = query.getResultList();

		if (film.isEmpty()) {
			return null;
		} else {
			return film.get(0);
		}

	}

	/**
	 *
	 */
	@Override
	public List<Film> getFilmByYear(String debut, String fin) {
		TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.annee BETWEEN :debut AND :fin", Film.class);
		query.setParameter("debut", debut);
		query.setParameter("fin", fin);
		List<Film> listeFilms = query.getResultList();

		return listeFilms;
	}

	/**
	 *
	 */
	@Override
	public List<Film> getFilmByActeurCommun(Acteur acteur1, Acteur acteur2) {
		String jpqlQuery = "SELECT r1.film " + "FROM Role r1 "
				+ "JOIN Role r2 ON r1.film = r2.film "
				+ "WHERE r1.acteur = :acteur1 AND r2.acteur = :acteur2";

		return em.createQuery(jpqlQuery, Film.class)
				.setParameter("acteur1", acteur1)
				.setParameter("acteur2", acteur2).getResultList();
	}

	/**
	 *
	 */
	@Override
	public List<Film> getFilmByAnneeActeur(int debut, int fin, Acteur acteur) {
		try {
			String jpql = "SELECT DISTINCT f FROM Film f "
					+ "JOIN f.acteur a "
					+ "WHERE f.annee BETWEEN :startYear AND :endYear "
					+ "AND a.idImdb = :idImdb";

			return em.createQuery(jpql, Film.class)
					.setParameter("startYear", String.valueOf(debut))
					.setParameter("endYear", String.valueOf(fin))
					.setParameter("idImdb", acteur.getIdImdb()).getResultList();
		} finally {
			em.close();
		}
	}
}
