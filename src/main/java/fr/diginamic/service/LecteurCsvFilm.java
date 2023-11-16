/**
 * 
 */
package fr.diginamic.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.entite.Film;

/**
 * @author lelan
 *
 */
public class LecteurCsvFilm {

	
//	public List<Film> lectureFichier(String nomFichier) {
//		return null;
//		
//	}
	
//	public List<String> lectureFichier(String nomFichier) {
//
//        List<String> donnees = new ArrayList()<>();
//        ClassLoader cl = getClass().getClassLoader();
//        InputStream is = cl.getResourceAsStream(nomFichier);
//
//        if(is == null) {
//            throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + nomFichier);
//        }
//
//        try(BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
//            String ligne;
//            lecteur.readLine();
//            while((ligne = lecteur.readLine()) != null) {
//                donnees.add(ligne);
//            }
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//            throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
//        }
//        return donnees;
//    }
}
