package fr.diginamic.entite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author lelan
 *
 */
@Entity
@Table(name = "Acteur")
public class Acteur {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** id_imdb */
	@Column(name = "id_imdb",unique=true)
	private String idImdb;

	/** identite */
	private String identite;
	
	/** date_naissance */
	@Column(name = "date_naissance")
	private LocalDate dateNaissance;
	
	/** url */
	private String url;
	
	/** listeActeur */
	@OneToMany(mappedBy="acteur")
	private List<Role> listeRole = new ArrayList<>();
	
	/** acteur */
	@ManyToOne
	@JoinColumn(name = "ID_LIEU_NAISSANCE")
	private LieuNaissance lieuNaissance;
	
	/** films */
	@ManyToMany(mappedBy = "acteur")
	private List<Film> films = new ArrayList<>();

	/** Constructor
	 * @param id_imdb
	 * @param identite
	 * @param date_naissance
	 * @param url
	 */
	public Acteur(String id_imdb, String identite, LocalDate date_naissance, String url) {
		super();
		this.idImdb = id_imdb;
		this.identite = identite;
		this.dateNaissance = date_naissance;
		this.url = url;
	}

	/** Constructor
	 * 
	 */
	public Acteur() {
		super();
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Acteur [id=" + id + ", id_imdb=" + idImdb + ", identite=" + identite + ", date_naissance="
				+ dateNaissance + ", url=" + url  + ", lieuNaissanceActeur="
				+ lieuNaissance + "]";
	}
	
	/**
	 * @param listActeur
	 * @param idmb
	 * @return
	 */
	public static Acteur getActeurByImdb(List<Acteur> listActeur, String idmb) {
		for(Acteur acteurs : listActeur) {
			if(acteurs.getIdImdb().equals(idmb)) {
				return acteurs;
			}
		}
		return null;
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
	 * @return the identite
	 */
	public String getIdentite() {
		return identite;
	}

	/** Setter
	 * @param identite the identite to set
	 */
	public void setIdentite(String identite) {
		this.identite = identite;
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
	 * @return the listeActeur
	 */
	public List<Role> getListeRole() {
		return listeRole;
	}

	/** Setter
	 * @param listeActeur the listeActeur to set
	 */
	public void setListeRole(List<Role> listeRole) {
		this.listeRole = listeRole;
	}

	/** Getter
	 * @return the films
	 */
	public List<Film> getFilms() {
		return films;
	}

	/** Setter
	 * @param films the films to set
	 */
	public void setFilms(List<Film> films) {
		this.films = films;
	}

	/** Getter
	 * @return the idImdb
	 */
	public String getIdImdb() {
		return idImdb;
	}

	/** Setter
	 * @param idImdb the idImdb to set
	 */
	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}

	/** Getter
	 * @return the dateNaissance
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	/** Setter
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/** Getter
	 * @return the lieuNaissance
	 */
	public LieuNaissance getLieuNaissance() {
		return lieuNaissance;
	}

	/** Setter
	 * @param lieuNaissance the lieuNaissance to set
	 */
	public void setLieuNaissance(LieuNaissance lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	
	
}
