package fr.diginamic.entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author lelan
 *
 */
@Entity
@Table(name = "Film")
public class Film {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** id_imdb */
	@Column(unique=true)
	private String idImdb;
	
	/** nom */
	@Column(unique=true)
	private String nom;
	
	/** annee */
	private String annee;
	
	/** rating */
	private String rating;
	
	/** url */
	private String url;
	
	/** lieu_tournage */
	private String lieu_tournage;
	
	/** resume */
	private String resume;
	
	/** langue */
	@ManyToOne
	@JoinColumn(name = "ID_LANGUE")
	private Langue langue;
	
	/** pays */
	@ManyToOne
	@JoinColumn(name = "ID_PAYS")
	private Pays pays;
	
	/** genres */
	@ManyToMany
	@JoinTable(name = "FILM_GENRE",joinColumns = @JoinColumn(name = "ID_FILM", referencedColumnName = "ID"), 
								inverseJoinColumns = @JoinColumn(name = "ID_GENRE", referencedColumnName = "ID"))
	private List<Genre> genres = new ArrayList<>();
	
	/** realisateur */
	@ManyToMany
	@JoinTable(name = "FILM_REALISATEUR",joinColumns = @JoinColumn(name = "ID_FILM", referencedColumnName = "ID"), 
								inverseJoinColumns = @JoinColumn(name = "ID_REALISATEUR", referencedColumnName = "ID"))
	private List<Realisateur> realisateur = new ArrayList<>();
	
	/** listeFilm */
	@OneToMany(mappedBy="film")
	private List<Role> listeRole = new ArrayList<>();
	
	/** acteur */
	@ManyToMany
	@JoinTable(name = "CASTING_PRINCIPAL",joinColumns = @JoinColumn(name = "ID_FILM", referencedColumnName = "ID"), 
								inverseJoinColumns = @JoinColumn(name = "ID_ACTEUR", referencedColumnName = "ID"))
	private List<Acteur> acteur = new ArrayList<>();

	/** Constructor
	 * @param id_imdb
	 * @param nom
	 * @param annee
	 * @param rating
	 * @param url
	 * @param lieu_tournage
	 * @param resume
	 */
	public Film(String idImdb, String nom, String annee, String rating, String url, String lieu_tournage, String resume) {
		super();
		this.idImdb = idImdb;
		this.nom = nom;
		this.annee = annee;
		this.rating = rating;
		this.url = url;
		this.lieu_tournage = lieu_tournage;
		this.resume = resume;
	}

	/** Constructor
	 * 
	 */
	public Film() {
		super();
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", idImdb=" + idImdb + ", nom=" + nom + ", annee=" + annee + ", rating=" + rating
				+ ", url=" + url + ", lieu_tournage=" + lieu_tournage + ", resume=" + resume + ", langue=" + langue
				+ ", pays=" + pays + ", genres=" + genres + "]";
	}
	
	
	
	/**
	 * @param listFilm
	 * @param nom
	 * @return
	 */
	public static Film getFilmByImdb(List<Film> listFilm, String idmb) {
		Film film = null;
		for(Film f : listFilm) {
			if(f.getIdImdb().equals(idmb)) {
				film = f;;
				break;
			}
		}
		return film;
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
	 * @return the annee
	 */
	public String getAnnee() {
		return annee;
	}

	/** Setter
	 * @param annee the annee to set
	 */
	public void setAnnee(String annee) {
		this.annee = annee;
	}

	/** Getter
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/** Setter
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
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
	 * @return the lieu_tournage
	 */
	public String getLieu_tournage() {
		return lieu_tournage;
	}

	/** Setter
	 * @param lieu_tournage the lieu_tournage to set
	 */
	public void setLieu_tournage(String lieu_tournage) {
		this.lieu_tournage = lieu_tournage;
	}

	/** Getter
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/** Setter
	 * @param resume the resume to set
	 */
	public void setResume(String resume) {
		this.resume = resume;
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
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/** Setter
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	/** Getter
	 * @return the listeFilm
	 */
	public List<Role> getListeRole() {
		return listeRole;
	}

	/** Setter
	 * @param listeFilm the listeFilm to set
	 */
	public void setListeRole(List<Role> listeRole) {
		this.listeRole = listeRole;
	}

	/** Getter
	 * @return the langue
	 */
	public Langue getLangue() {
		return langue;
	}

	/** Setter
	 * @param langue the langue to set
	 */
	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	/** Getter
	 * @return the pays
	 */
	public Pays getPays() {
		return pays;
	}

	/** Setter
	 * @param pays the pays to set
	 */
	public void setPays(Pays pays) {
		this.pays = pays;
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
