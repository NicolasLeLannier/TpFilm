/**
 * 
 */
package fr.diginamic.essaie;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.dao.ActeurDao;
import fr.diginamic.dao.FilmDao;
import fr.diginamic.dao.jdbc.ActeurDaoJdbc;
import fr.diginamic.dao.jdbc.FilmDaoJdbc;
import fr.diginamic.entite.Acteur;
import fr.diginamic.entite.Film;

/**
 * @author lelan
 *
 */
public class ApplicationMenu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = entityManagerFactory.createEntityManager();
		FilmDao filmDao = new FilmDaoJdbc(em);
		ActeurDao acteurDao = new ActeurDaoJdbc(em);
		
		Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Affichage de la filmographie d'un acteur donné");
            System.out.println("2. Affichage du casting d'un film donné");
            System.out.println("3. Affichage des films sortis entre 2 années données");
            System.out.println("4. Affichage des films communs à 2 acteurs/actrices donnés");
            System.out.println("5. Affichage des acteurs communs à 2 films donnés");
            System.out.println("6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
            System.out.println("7. Fin de l'application");

            System.out.print("Veuillez choisir une option (1-7): ");
            
            while (!scanner.hasNextInt()) {
				System.out.println("Veuillez entrer un nombre.");
				scanner.next();
			}
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Logique pour l'affichage de la filmographie d'un acteur
                	
                	System.out.println("-------------------------------------------------------------");
                	System.out.print("Veuillez donner le nom d'un acteur : ");
                	
                	String nomActeur = scanner.nextLine();
                	
    				Acteur acteur = acteurDao.getActeurByIdentite(nomActeur);

    				List<Film> listFilmByActeur = filmDao.getFilmByActeur(acteur);

    				System.out.println("La filmographie de " + acteur.getIdentite() + " :");
    				for (Film films : listFilmByActeur) {
    					System.out.println(films.getNom());
    				}

    				System.out.println("-------------------------------------------------------------");
                    break;
                    
                case 2:
                    // Logique pour l'affichage du casting d'un film
                	
                	System.out.println("-------------------------------------------------------------");
                	System.out.print("Veuillez donner le nom du film : ");
                	
    				String nomFilm = scanner.nextLine();
    				
    				Film film = filmDao.getFilmByName(nomFilm);

    				List<Acteur> listActeurByFilm = acteurDao.getActeurByFilm(film);

    				System.out.println("Le casting d’un film donné : " + film.getNom() + " :");
    				for (Acteur acteurs : listActeurByFilm) {
    					System.out.println(acteurs.getIdentite());
    				}
    				
    				System.out.println("-------------------------------------------------------------");
                    break;
                    
                case 3:
                    // Logique pour l'affichage des films entre 2 années données
                	
                	System.out.println("-------------------------------------------------------------");
    				System.out.println("Veuillez donner 2 dates :");
    				
    				System.out.print("Année de début : ");
    				String anneDebut = scanner.nextLine();
    				
    				System.out.print("Année de fin : ");
    				String anneFin = scanner.nextLine();

    				List<Film> filmByAnnee = filmDao.getFilmByYear(anneDebut, anneFin);

    				for (Film films : filmByAnnee) {
    					System.out.println(films.getNom());
    				}
                	
    				System.out.println("-------------------------------------------------------------");
                    break;
                    
                case 4:
                    // Logique pour l'affichage des films communs à 2 acteurs/actrices
                	
                	System.out.println("-------------------------------------------------------------");
                	System.out.println("Veuillez donner 2 acteurs :");
                	
    				System.out.print("Nom de l'acteur 1 : ");
    				String nomActeur1 = scanner.nextLine();
    				
    				System.out.print("Nom de l'acteur 2 : ");
    				String nomActeur2 = scanner.nextLine();
    				
    				Acteur acteur1 = acteurDao.getActeurByIdentite(nomActeur1);
    				Acteur acteur2 = acteurDao.getActeurByIdentite(nomActeur2);

    				List<Film> listFilmByActeurCommun = filmDao.getFilmByActeurCommun(acteur1, acteur2);

    				System.out.println("La filmographie commune de l'acteur : " + acteur1.getIdentite() + " et de l'acteur : " + acteur2.getIdentite() + " est :");
    				for (Film films : listFilmByActeurCommun) {
    					System.out.println(films.getNom());
    				}
                	
    				System.out.println("-------------------------------------------------------------");
                    break;
                    
                case 5:
                    // Logique pour l'affichage des acteurs communs à 2 films
                	
                	System.out.println("-------------------------------------------------------------");
                	System.out.println("Veuillez donner 2 films :");
                	
    				System.out.print("Nom du film 1 : ");
    				String nomFilm1 = scanner.nextLine();
    				
    				System.out.print("Nom du film 2 : ");
    				String nomFilm2 = scanner.nextLine();
    				
    				Film film1 = filmDao.getFilmByName(nomFilm1);
    				Film film2 = filmDao.getFilmByName(nomFilm2);

    				List<Acteur> listActeurCommunByFilm = acteurDao.getActeurCommunByFilm(film1, film2);

    				System.out.println("Les acteurs commun au film : " + film1.getNom() + " et du film : " + film2.getNom() + " est :");
    				for (Acteur acteurs : listActeurCommunByFilm) {
    					System.out.println(acteurs.getIdentite());
    				}
                	
    				System.out.println("-------------------------------------------------------------");
                    break;
                    
                case 6:
                    // Logique pour l'affichage des films entre 2 années avec un acteur/actrice donné
                	
                	System.out.println("-------------------------------------------------------------");
                	System.out.println("Veuillez donner 2 années et 1 acteur :");
                	
    				System.out.print("Année de début : ");
    				int anneDebutFilm1 = scanner.nextInt();
    				
    				System.out.print("Année de fin : ");
    				int anneFinFilm2 = scanner.nextInt();
    				
    				Scanner scannerFilmByActeur = new Scanner(System.in);
    				System.out.print("Acteur : ");
    				String annefilmByActeur = scannerFilmByActeur.nextLine();
    				
    				Acteur acteurByFilm = acteurDao.getActeurByIdentite(annefilmByActeur);

    				List<Film> listFilmByAnneeActeur = filmDao.getFilmByAnneeActeur(anneDebutFilm1, anneFinFilm2,
    						acteurByFilm);

    				System.out.println("Les films sortis entre l'année : " + anneDebutFilm1 + " et " + anneFinFilm2
    						+ " avec l'acteur suivant : " + acteurByFilm.getIdentite() + " sont :");
    				for (Film films : listFilmByAnneeActeur) {
    					System.out.println(films.getNom());
    				}
    				
    				System.out.println("-------------------------------------------------------------");
                    break;
                    
                case 7:
                    System.out.println("Fin de l'application. Au revoir !");
                    break;
                    
                default:
                    System.out.println("Option invalide. Veuillez choisir une option entre 1 et 7.");
                    break;
            }

        } while (choice != 7);

        // Fermer le scanner à la fin
        scanner.close();
        em.close();
		entityManagerFactory.close();
	}

}
