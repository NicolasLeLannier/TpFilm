/**
 * 
 */
package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.entite.Acteur;
import fr.diginamic.entite.Film;

/**
 * @author lelan
 *
 */
public interface FilmDao {

	/**
	 * @param acteur
	 * @return
	 */
	List<Film> getFilmByActeur(Acteur acteur);

	/**
	 * @param nom
	 * @return
	 */
	Film getFilmByName(String nom);

	/**
	 * @param debut
	 * @param fin
	 * @return
	 */
	List<Film> getFilmByYear(String debut, String fin);
	
	/**
	 * @param acteur1
	 * @param acteur2
	 * @return
	 */
	List<Film> getFilmByActeurCommun(Acteur acteur1, Acteur acteur2);

	/**
	 * @param debut
	 * @param fin
	 * @param acteur
	 * @return
	 */
	List<Film> getFilmByAnneeActeur(int debut, int fin, Acteur acteur);
}
