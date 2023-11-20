/**
 * 
 */
package fr.diginamic.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import antlr.StringUtils;
import fr.diginamic.entite.Acteur;
import fr.diginamic.entite.Film;
import fr.diginamic.entite.Genre;
import fr.diginamic.entite.Langue;
import fr.diginamic.entite.LieuNaissance;
import fr.diginamic.entite.Pays;
import fr.diginamic.entite.Realisateur;
import fr.diginamic.entite.Role;

/**
 * @author lelan
 *
 */
public class LecteurCsv {

	List<LieuNaissance> arrayLieuNaissanceActeur = new ArrayList<>();
	List<LieuNaissance> arrayLieuNaissanceRealisateur = new ArrayList<>();
	List<Genre> arrayGenre = new ArrayList<>();
	List<Langue> arrayLangue = new ArrayList<>();
	List<Pays> arrayPays = new ArrayList<>();
	List<Acteur> arrayActeur = new ArrayList<>();
	List<Film> arrayFilm = new ArrayList<>();
	List<Realisateur> arrayRealisateur = new ArrayList<>();
	

	// ------------------------------------------------------- PARSE PAYS ------------------------------------
	public List<Pays> parsePays(String filePays) {

		List<Pays> arrayPays = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filePays);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + filePays);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");

				String nom = tokens[0];
				String url = tokens[1];

				Pays actuelPays = new Pays(nom, url);

				arrayPays.add(actuelPays);
			}

		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayPays;
	}

	
	// ------------------------------------------------------- PARSE ACTEUR ------------------------------------
	public List<Acteur> parseActeur(String fileActeur, List<LieuNaissance> listeLieuNaissanceMAJ) {

		List<Acteur> arrayActeur = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fileActeur);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fileActeur);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";", -1);

				String idImdb = tokens[0];
				String identite = tokens[1];

				LocalDate dateNaissance = null;
				if (!tokens[2].isEmpty()) {
					if (tokens[2].contains("/")) {

//						System.out.println(tokens[2]);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						dateNaissance = LocalDate.parse(tokens[2], formatter);

//						System.out.println("La chaîne n'est pas au format de date désiré.");
					}
				}

				String ligneLieuNaissance = tokens[3];
				ligneLieuNaissance = ligneLieuNaissance.replaceAll("é", "e");
				ligneLieuNaissance = ligneLieuNaissance.replaceAll("è", "e");
				ligneLieuNaissance = ligneLieuNaissance.replaceAll("Î", "I");
				ligneLieuNaissance = ligneLieuNaissance.trim();
				if(!ligneLieuNaissance.isEmpty()) {
					ligneLieuNaissance = ligneLieuNaissance.substring(0, 1).toUpperCase() + ligneLieuNaissance.substring(1);
				}
				
				String url = tokens[4];
				
				LieuNaissance actuelLieuNaissance = LieuNaissance.getLieuNaissanceByNom(listeLieuNaissanceMAJ,
						ligneLieuNaissance);

				Acteur actuelActeur = new Acteur(idImdb, identite, dateNaissance, url);
				actuelActeur.setLieuNaissance(actuelLieuNaissance);

				arrayActeur.add(actuelActeur);
			}

		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayActeur;
	}

	
	// ------------------------------------------------------- PARSE REALISATEUR ------------------------------------
	public List<Realisateur> parseRealisateur(String fileRealisateur, List<LieuNaissance> listeLieuNaissanceMAJ) {

		List<Realisateur> arrayRealisateur = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fileRealisateur);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fileRealisateur);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";", -1);

				String idImdb = tokens[0];
				String identite = tokens[1];

				LocalDate dateNaissance = null;
				if (!tokens[2].isEmpty()) {
					try {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy ", Locale.ENGLISH);
						dateNaissance = LocalDate.parse(tokens[2], formatter);
					} catch (Exception e) {
//						System.out.println("La chaîne n'est pas au format de date désiré.");
					}
				}

				String ligneLieuNaissance = tokens[3];
				ligneLieuNaissance = ligneLieuNaissance.replaceAll("é", "e");
				ligneLieuNaissance = ligneLieuNaissance.replaceAll("è", "e");
				ligneLieuNaissance = ligneLieuNaissance.replaceAll("Î", "I");
				ligneLieuNaissance = ligneLieuNaissance.trim();
				if(!ligneLieuNaissance.isEmpty()) {
					ligneLieuNaissance = ligneLieuNaissance.substring(0, 1).toUpperCase() + ligneLieuNaissance.substring(1);
				}
				
				String url = tokens[4];

				LieuNaissance actuelLieuNaissance = LieuNaissance.getLieuNaissanceByNom(listeLieuNaissanceMAJ,
						ligneLieuNaissance);

				Realisateur actuelRealisateur = new Realisateur(idImdb, identite, dateNaissance, url);
				actuelRealisateur.setLieuNaissance(actuelLieuNaissance);

				arrayRealisateur.add(actuelRealisateur);
			}

		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayRealisateur;
	}

	
	// ------------------------------------------------------- PARSE FILM ------------------------------------
	public List<Film> parseFilm(String fileFilm, String filePays) {

		List<Film> arrayFilms = new ArrayList<>();
		List<Genre> arrayGenre = new ArrayList<>();
		List<Langue> arrayLangue = new ArrayList<>();
		List<Pays> arrayPays = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fileFilm);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fileFilm);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				if (tokens.length > 10) {
					tokens[8] = tokens[8] + tokens[9];
					tokens[9] = tokens[10];
				}

				String pays = null;

				if (tokens.length == 9) {
					pays = "Pas de pays";
				} else {
					pays = tokens[9];
				}

				String idImdb = tokens[0];
				String nom = tokens[1];
				String annee = tokens[2];
				String rating = tokens[3];
				String url = tokens[4];
				String lieuTournage = tokens[5];
				String ligneGenre = tokens[6];
				String langue = tokens[7];
				String resume = tokens[8];

				LecteurCsv lectureCsv = new LecteurCsv();

				arrayPays = lectureCsv.parsePays(filePays);
				Pays actuelPays = Pays.getPaysByNom(arrayPays, pays);

				arrayLangue = lectureCsv.parseLangue(fileFilm);
				Langue actuelLangue = Langue.getLangueByNom(arrayLangue, langue);

				List<Genre> arrayActuelGenre = new ArrayList<>();
				arrayGenre = lectureCsv.parseGenre(fileFilm);
				String[] listGenre = ligneGenre.split(",");
				for (String genres : listGenre) {
					Genre actuelGenre = Genre.getGenreByNom(arrayGenre, genres);
					arrayActuelGenre.add(actuelGenre);
				}

				Film actuelFilm = new Film(idImdb, nom, annee, rating, url, lieuTournage, resume);
				actuelFilm.setLangue(actuelLangue);
				actuelFilm.setGenres(arrayActuelGenre);
				actuelFilm.setPays(actuelPays);

				arrayFilms.add(actuelFilm);
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayFilms;
	}

	
	// ------------------------------------------------------- PARSE GENRE ------------------------------------
	public List<Genre> parseGenre(String fileGenre) {

		List<Genre> arrayGenre = new ArrayList<>();
		List<String> stringGenre = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fileGenre);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fileGenre);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lineGenre = tokens[6];
				String[] arrayLineGenre = lineGenre.split(",");
				for (String lineGenres : arrayLineGenre) {
					if (!lineGenres.isEmpty()) {
						if (!stringGenre.contains(lineGenres)) {
							stringGenre.add(lineGenres);
							Genre newGenre = new Genre(lineGenres);
							arrayGenre.add(newGenre);
						}
					}
				}
			}

		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayGenre;
	}

	
	// ------------------------------------------------------- PARSE LANGUE ------------------------------------
	public List<Langue> parseLangue(String fileLangue) {

		List<Langue> arrayLangue = new ArrayList<>();
		List<String> stringLangue = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fileLangue);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fileLangue);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lineLangue = tokens[7];

				if (!lineLangue.isEmpty()) {
					if (!stringLangue.contains(lineLangue)) {
						stringLangue.add(lineLangue);
						Langue newLangue = new Langue(lineLangue);
						arrayLangue.add(newLangue);
					}
				}
			}

		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayLangue;
	}

	
	// ------------------------------------------------------- PARSE LIEU DE NAISSANCE ------------------------------------
	public List<LieuNaissance> parseLieuNaissance(String fileRealisateur, String fileActeur) {

		List<String> arrayStringLieuNaissance = new ArrayList<>();
		List<LieuNaissance> arrayLieuNaissance = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fileRealisateur);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fileRealisateur);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lineLieuxNaissances = tokens[3];
				lineLieuxNaissances = lineLieuxNaissances.replaceAll("é", "e");
				lineLieuxNaissances = lineLieuxNaissances.replaceAll("è", "e");
				lineLieuxNaissances = lineLieuxNaissances.replaceAll("Î", "I");
				lineLieuxNaissances = lineLieuxNaissances.trim();
				if(!lineLieuxNaissances.isEmpty()) {
					lineLieuxNaissances = lineLieuxNaissances.substring(0, 1).toUpperCase() + lineLieuxNaissances.substring(1);
				}

				LecteurCsv lectureCsv = new LecteurCsv();
				arrayStringLieuNaissance = lectureCsv.parseStringLieuNaissance(fileActeur);
				
				if (!lineLieuxNaissances.isEmpty()) {
					if (!arrayStringLieuNaissance.contains(lineLieuxNaissances)) {
						
						arrayStringLieuNaissance.add(lineLieuxNaissances);
					}
				}
			}
			
		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		
		for(String lieuNaissance : arrayStringLieuNaissance) {
			LieuNaissance newLieuNaissance = new LieuNaissance(lieuNaissance);
			arrayLieuNaissance.add(newLieuNaissance);
		}
		
		return arrayLieuNaissance;
	}
	
	
	// ------------------------------------------------------- PARSE STRING LIEU DE NAISSANCE ------------------------------------
	public List<String> parseStringLieuNaissance(String pathFileActeur) {

		List<String> stringLieuNaissance = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileActeur);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileActeur);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lieuNaissance = tokens[3];
				lieuNaissance = lieuNaissance.replaceAll("é", "e");
				lieuNaissance = lieuNaissance.replaceAll("è", "e");
				lieuNaissance = lieuNaissance.replaceAll("Î", "I");
				lieuNaissance = lieuNaissance.trim();
				if(!lieuNaissance.isEmpty()) {
					lieuNaissance = lieuNaissance.substring(0, 1).toUpperCase() + lieuNaissance.substring(1);
				}
				
				if (!lieuNaissance.isEmpty()) {
					if (!stringLieuNaissance.contains(lieuNaissance)) {
						stringLieuNaissance.add(lieuNaissance);
					}
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return stringLieuNaissance;
	}
	
	
	// ------------------------------------------------------- PARSE ROLE ------------------------------------
	public List<Role> parseRole(String fileRole, List<Acteur> listeActeurMAJ, List<Film> listeFilmMAJ) {

		List<Role> arrayRole = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fileRole);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fileRole);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";", -1);

				String idIdmbFilm = tokens[0];
				String idIdmbActeur = tokens[1];
				String personnage = tokens[2];

				Film actuelFilm = Film.getFilmByImdb(listeFilmMAJ, idIdmbFilm);
				Acteur actuelActeur = Acteur.getActeurByImdb(listeActeurMAJ, idIdmbActeur);

				Role actuelRole = new Role(personnage);
				actuelRole.setFilm(actuelFilm);
				actuelRole.setActeur(actuelActeur);

				arrayRole.add(actuelRole);
			}

		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayRole;
	}
	
	
	// ------------------------------------------------------- PARSE FILM REALISATEUR ------------------------------------
	public List<String> parseFilmRealisateur(String fileFilmRealisateur, List<Realisateur> listeRealisateurMAJ, List<Film> listeFilmMAJ) {

		List<String> arrayFilmRealisateur = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fileFilmRealisateur);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fileFilmRealisateur);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				
				String idIdbmFilm = tokens[0];
				String idIdbmRealisateur = tokens[1];

				Film actuelFilm = Film.getFilmByImdb(listeFilmMAJ, idIdbmFilm);
				Realisateur actuelRealisateur = Realisateur.getRealisateurByImdb(listeRealisateurMAJ, idIdbmRealisateur);

				actuelFilm.getRealisateur().add(actuelRealisateur);
				actuelRealisateur.getFilms().add(actuelFilm);

			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayFilmRealisateur;
	}
	
	
	
	// ------------------------------------------------------- PARSE CASTING PRINCIPAL ------------------------------------
		public List<String> parseCastingPrincipal(String fileCastingPrincipal, List<Acteur> listeActeurMAJ, List<Film> listeFilmMAJ) {

			List<String> arrayFilmActeur = new ArrayList<>();
			ClassLoader cl = getClass().getClassLoader();
			InputStream is = cl.getResourceAsStream(fileCastingPrincipal);

			if (is == null) {
				throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fileCastingPrincipal);
			}

			try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
				String line;
				lecteur.readLine();
				while ((line = lecteur.readLine()) != null) {
					String[] tokens = line.split(";");
					
					String idIdbmFilm = tokens[0];
					String idIdbmActeur = tokens[1];

					Film actuelFilm = Film.getFilmByImdb(listeFilmMAJ, idIdbmFilm);
					Acteur actuelActeur = Acteur.getActeurByImdb(listeActeurMAJ, idIdbmActeur);
					
					if(actuelFilm != null && actuelActeur != null) {
						actuelFilm.getActeur().add(actuelActeur);
						actuelActeur.getFilms().add(actuelFilm);
					}

				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
			}
			return arrayFilmActeur;
		}

}
