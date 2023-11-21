/**
 * 
 */
package fr.diginamic.dao.jdbc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.dao.ActeurDao;
import fr.diginamic.entite.Acteur;
import fr.diginamic.entite.Film;

/**
 * @author lelan
 *
 */
public class ActeurDaoJdbc implements ActeurDao {

	/** em */
	private EntityManager em;


	/** Constructor
	 * @param em
	 */
	public ActeurDaoJdbc(EntityManager em) {
		this.em = em;
	}

	/**
	 *
	 */
	@Override
	public Acteur getActeurByIdentite(String identite) {

		TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.identite = :identite", Acteur.class);
		query.setParameter("identite", identite);
		List<Acteur> acteur = query.getResultList();

		if (acteur.isEmpty()) {
			return null;
		} else {
			return acteur.get(0);
		}
	}

	/**
	 *
	 */
	@Override
	public List<Acteur> getActeurByFilm(Film film) {
		TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a JOIN a.films f WHERE f = :film", Acteur.class);
		query.setParameter("film", film);
		List<Acteur> listeActeurs = query.getResultList();
		
		return listeActeurs;
	}

	/**
	 *
	 */
	@Override
	public List<Acteur> getActeurCommunByFilm(Film film1, Film film2) {
		String jpqlQuery = "SELECT r1.acteur " + "FROM Role r1 "
				+ "JOIN Role r2 ON r1.acteur = r2.acteur "
				+ "WHERE r1.film = :film1 AND r2.film = :film2";

		return em.createQuery(jpqlQuery, Acteur.class)
				.setParameter("film1", film1).setParameter("film2", film2)
				.getResultList();
	}
}
