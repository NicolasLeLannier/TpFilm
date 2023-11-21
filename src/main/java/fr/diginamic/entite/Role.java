package fr.diginamic.entite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** personnage */
	private String personnage;
	
	/** acteur */
	@ManyToOne
	@JoinColumn(name="ID_ACTEUR", referencedColumnName = "ID")
	private Acteur acteur;
	
	/** film */
	@ManyToOne
	@JoinColumn(name="ID_FILM", referencedColumnName = "ID")
	private Film film;

	
	/** Constructor
	 * 
	 */
	public Role() {
		super();
	}

	/** Constructor
	 * @param personnage
	 */
	public Role(String personnage) {
		super();
		this.personnage = personnage;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Role [id=" + id + ", personnage=" + personnage + ", acteur=" + acteur + ", film=" + film + "]";
	}

	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Getter
	 * @return the personnage
	 */
	public String getPersonnage() {
		return personnage;
	}

	/** Setter
	 * @param personnage the personnage to set
	 */
	public void setPersonnage(String personnage) {
		this.personnage = personnage;
	}

	/** Getter
	 * @return the acteur
	 */
	public Acteur getActeur() {
		return acteur;
	}

	/** Setter
	 * @param acteur the acteur to set
	 */
	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}

	/** Getter
	 * @return the film
	 */
	public Film getFilm() {
		return film;
	}

	/** Setter
	 * @param film the film to set
	 */
	public void setFilm(Film film) {
		this.film = film;
	}
	
	
}
