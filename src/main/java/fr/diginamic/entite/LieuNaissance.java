package fr.diginamic.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Lieu_Naissance")
public class LieuNaissance {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** nom */
	@Column(unique=true)
	private String nom;

	/** realisateur */
	@OneToMany(mappedBy = "idLieuNaissanceRealisateur")
	private List<Realisateur> realisateur;
	
	/** acteur */
	@OneToMany(mappedBy = "idLieuNaissanceActeur")
	private List<Acteur> acteur;
	
	/** Constructor
	 * @param nom
	 */
	public LieuNaissance(String nom) {
		super();
		this.nom = nom;
	}

	/** Constructor
	 * 
	 */
	public LieuNaissance() {
		super();
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "LieuNaissance [id=" + id + ", nom=" + nom + "]";
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
	 * @return the realisateur
	 */
	public List<Realisateur> getRealisateur() {
		return realisateur;
	}

	/** Setter
	 * @param realisateur the realisateur to set
	 */
	public void setRealisateur(List<Realisateur> realisateur) {
		this.realisateur = realisateur;
	}

	/** Getter
	 * @return the acteur
	 */
	public List<Acteur> getActeur() {
		return acteur;
	}

	/** Setter
	 * @param acteur the acteur to set
	 */
	public void setActeur(List<Acteur> acteur) {
		this.acteur = acteur;
	}
	
	
}
