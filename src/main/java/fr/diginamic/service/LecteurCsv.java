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
	public List<Acteur> parseActeur(String fileActeur) {

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
				String url = tokens[4];
//System.out.println(ligneLieuNaissance);
				
				if(arrayLieuNaissanceActeur.isEmpty()) {
					LecteurCsv lectureCsv = new LecteurCsv();
					arrayLieuNaissanceActeur = lectureCsv.parseLieuNaissance(fileActeur);
				}
				
				LieuNaissance actuelLieuNaissance = LieuNaissance.getLieuNaissanceByNom(arrayLieuNaissanceActeur, ligneLieuNaissance);
				
				Acteur actuelActeur = new Acteur(idImdb, identite, dateNaissance, url);
				actuelActeur.setLieuNaissanceActeur(actuelLieuNaissance);

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
	public List<Realisateur> parseRealisateur(String fileRealisateur) {

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
					} catch(Exception e) {
//						System.out.println("La chaîne n'est pas au format de date désiré.");
					}
				}

				String ligneLieuNaissance = tokens[3];
				
				String url = tokens[4];

				if(arrayLieuNaissanceRealisateur.isEmpty()) {
					LecteurCsv lectureCsv = new LecteurCsv();
					arrayLieuNaissanceRealisateur = lectureCsv.parseLieuNaissance(fileRealisateur);
				}

				LieuNaissance actuelLieuNaissance = LieuNaissance.getLieuNaissanceByNom(arrayLieuNaissanceRealisateur, ligneLieuNaissance);

				Realisateur actuelRealisateur = new Realisateur(idImdb, identite, dateNaissance, url);
				actuelRealisateur.setLieuNaissanceRealisateur(actuelLieuNaissance);
				
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
	public List<Film> parseFilm(String pathFileFilm, String pathFilePays) {

		List<Film> arrayFilms = new ArrayList<>();
		List<Genre> arrayGenre = new ArrayList<>();
		List<Langue> arrayLangue = new ArrayList<>();
		List<Pays> arrayPays = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(pathFileFilm);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + pathFileFilm);
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
				
				
				arrayPays = lectureCsv.parsePays(pathFilePays);
				Pays actuelPays = Pays.getPaysByNom(arrayPays, pays);

				arrayLangue = lectureCsv.parseLangue(pathFileFilm);
				Langue actuelLangue = Langue.getLangueByNom(arrayLangue, langue);

				List<Genre> arrayActuelGenre = new ArrayList<>();
				arrayGenre = lectureCsv.parseGenre(pathFileFilm);
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
		public List<LieuNaissance> parseLieuNaissance(String fileLieuNaissance) {

			List<LieuNaissance> arrayLieuNaissance = new ArrayList<>();
			List<String> stringLieuNaissance = new ArrayList<>();
			ClassLoader cl = getClass().getClassLoader();
			InputStream is = cl.getResourceAsStream(fileLieuNaissance);

			if (is == null) {
				throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fileLieuNaissance);
			}

			try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
				String line;
				lecteur.readLine();
				while ((line = lecteur.readLine()) != null) {
					String[] tokens = line.split(";");
					String lineLieuxNaissances = tokens[3];

					if (!lineLieuxNaissances.isEmpty()) {
						if (!stringLieuNaissance.contains(lineLieuxNaissances)) {
							stringLieuNaissance.add(lineLieuxNaissances);
							LieuNaissance newLieuNaissance = new LieuNaissance(lineLieuxNaissances);
							arrayLieuNaissance.add(newLieuNaissance);
						}
					}
				}

			} catch (

			IOException e) {
				System.err.println(e.getMessage());
				throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
			}
			return arrayLieuNaissance;
		}
		
		
		// ------------------------------------------------------- PARSE ROLE ------------------------------------
				public List<Role> parseRole(String fileRole) {

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
							
							if(arrayActeur.isEmpty()) {
								LecteurCsv lectureCsv = new LecteurCsv();
								arrayActeur = lectureCsv.parseActeur("acteurs.csv");
							}
							if(arrayFilm.isEmpty()) {
								LecteurCsv lectureCsv = new LecteurCsv();
								arrayFilm = lectureCsv.parseFilm("films.csv", "pays.csv");
							}

							Film actuelFilm = Film.getFilmByImdb(arrayFilm, idIdmbFilm);
							Acteur actuelActeur = Acteur.getActeurByImdb(arrayActeur, idIdmbActeur);
							
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

}
