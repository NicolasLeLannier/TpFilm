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
import javax.persistence.Table;

/**
 * @author lelan
 *
 */
@Entity
@Table(name = "Realisateur")
public class Realisateur {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** id_imdb */
	@Column(name = "id_imdb", unique=true)
	private String idImdb;

	/** identite */
	private String identite;
	
	/** date_naissance */
	@Column(name = "date_naissance")
	private LocalDate dateNaissance;
	
	/** url */
	private String url;
	
	/** films */
	@ManyToMany(mappedBy = "realisateur")
	private List<Film> films = new ArrayList<>();
	
	/** acteur */
	@ManyToOne
	@JoinColumn(name = "ID_LIEU_NAISSANCE")
	private LieuNaissance lieuNaissance;

	/** Constructor
	 * @param id_imdb
	 * @param identite
	 * @param date_naissance
	 * @param url
	 */
	public Realisateur(String id_imdb, String identite, LocalDate date_naissance, String url) {
		super();
		this.idImdb = id_imdb;
		this.identite = identite;
		this.dateNaissance = date_naissance;
		this.url = url;
	}

	/** Constructor
	 * 
	 */
	public Realisateur() {
		super();
	}
	
	/**
	 *
	 */
	@Override
	public String toString() {
		return "Realisateur [id=" + id + ", idImdb=" + idImdb + ", identite=" + identite + ", dateNaissance="
				+ dateNaissance + ", url=" + url + ", films=" + films + ", lieuNaissance=" + lieuNaissance + "]";
	}

	/**
	 * @param listRealisateur
	 * @param idmb
	 * @return
	 */
	public static Realisateur getRealisateurByImdb(List<Realisateur> listRealisateur, String idmb) {
		for(Realisateur realisateurs : listRealisateur) {
			if(realisateurs.getIdImdb().equals(idmb)) {
				return realisateurs;
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
	 * @return the id_imdb
	 */
	public String getIdImdb() {
		return idImdb;
	}

	/** Setter
	 * @param id_imdb the id_imdb to set
	 */
	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
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
	 * @return the date_naissance
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	/** Setter
	 * @param date_naissance the date_naissance to set
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
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
