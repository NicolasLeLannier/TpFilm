package fr.diginamic.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author lelan
 *
 */
@Entity
@Table(name = "Genre")
public class Genre {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** nom */
	@Column(unique=true)
	private String nom;
	
	/** films */
	@ManyToMany(mappedBy = "genres")
	private List<Film> films;

	/** Constructor
	 * @param nom
	 */
	public Genre(String nom) {
		super();
		this.nom = nom;
	}

	/** Constructor
	 * 
	 */
	public Genre() {
		super();
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + nom + "]";
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
	
	
}
