/**
 * 
 */
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
@Table(name = "Pays")
public class Pays {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** nom */
	@Column(unique=true)
	private String nom;
	
	/** url */
	private String url;
	
	/** film */
	@OneToMany(mappedBy = "pays")
	private List<Film> film;

	/** Constructor
	 * @param nom
	 * @param url
	 */
	public Pays(String nom, String url) {
		super();
		this.nom = nom;
		this.url = url;
	}

	/** Constructor
	 * 
	 */
	public Pays() {
		super();
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Pays [id=" + id + ", nom=" + nom + ", url=" + url + "]";
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/** Setter
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
