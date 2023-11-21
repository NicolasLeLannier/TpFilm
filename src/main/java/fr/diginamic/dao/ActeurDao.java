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
public interface ActeurDao {


	/**
	 * @param identite
	 * @return
	 */
	Acteur getActeurByIdentite(String identite);

	/**
	 * @param film
	 * @return
	 */
	List<Acteur> getActeurByFilm(Film film);

	/**
	 * @param film1
	 * @param film2
	 * @return
	 */
	List<Acteur> getActeurCommunByFilm(Film film1, Film film2);
}
