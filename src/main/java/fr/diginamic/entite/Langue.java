package fr.diginamic.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author lelan
 *
 */
@Entity
@Table(name = "Langue")
public class Langue {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** nom */
	@Column(unique=true)
	private String nom;
	
	/** film */
	@OneToMany(mappedBy = "langue")
	private List<Film> film;
	
	

	/** Constructor
	 * @param nom
	 */
	public Langue(String nom) {
		super();
		this.nom = nom;
	}

	/** Constructor
	 * 
	 */
	public Langue() {
		super();
	}
	
	
	
	/**
	 * @param listLangue
	 * @param nom
	 * @return
	 */
	public static Langue getLangueByNom(List<Langue> listLangue, String nom) {
		for(Langue langues : listLangue) {
			if(langues.getNom().equals(nom)) {
				return langues;
			}
		}
		return null;
	}
	

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Langue [id=" + id + ", nom=" + nom + "]";
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter
	 * @return the film
	 */
	public List<Film> getFilm() {
		return film;
	}

	/** Setter
	 * @param film the film to set
	 */
	public void setFilm(List<Film> film) {
		this.film = film;
	}
	
	
}
