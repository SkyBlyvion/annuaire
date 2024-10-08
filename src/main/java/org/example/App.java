package org.example;

import org.example.model.Contact;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Hello world!!!!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        app.demarre();
        Scanner scanner = new Scanner(System.in);
        String choix = "";
        while (choix != "3") {
            choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    app.readJson();
                    break;
                case "2":
                    // Declaration des scanners
                    Scanner scNom = new Scanner(System.in);
                    Scanner scPrenom = new Scanner(System.in);
                    Scanner scTel = new Scanner(System.in);
                    Scanner scEmail = new Scanner(System.in);

                    // ux de récup des valeurs
                    System.out.println("Nom :");
                    String nom = scNom.nextLine();

                    System.out.println("Prenom :");
                    String prenom = scPrenom.nextLine();

                    System.out.println("Tel :");
                    String tel = scTel.nextLine();

                    System.out.println("Email :");
                    String email = scEmail.nextLine();

                    // appel de la fonction
                    app.writeJson(nom, prenom, tel, email);
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide");
                    break;

            }
        }
    }

    public void demarre() {
        System.out.println( "l'app est démarrée" );
        System.out.println("Que voulez-vous faire");
        System.out.println("1 - Lire un fichier json");
        System.out.println("2 - écrire dans le fichier json");
        System.out.println("3 - quitter");
        System.out.println("Votre choix");
    }

    public JSONArray getJsonArray() {
        // Parseur json issu de la lib json-simple
        JSONParser parser = new JSONParser();

        // il faut créer un try catch car FileReader throws FileNotFoundException
        try {
            FileReader reader = new FileReader(
                    getClass().getResource("/files/json/myJson.json").getPath()
            );
            // Parsing du fichier
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            return jsonArray;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JSONArray(); // retourne un objet empty
    }

    // récupération des données du json
    public void readJson() {
        JSONArray jsonArray = getJsonArray();
        jsonArray.forEach( contact -> {
            JSONObject jsonObj = (JSONObject) contact;
            Contact personne = new Contact(
                    (String) jsonObj.get("nom"),
                    (String) jsonObj.get("prenom"),
                    (String) jsonObj.get("tel"),
                    (String) jsonObj.get("email")
            );
            System.out.println(personne.toString());
        });
    }

    public void writeJson(String nom, String prenom, String tel, String email) {
        Contact personne = new Contact(nom, prenom, tel, email);
        try {
            JSONObject contactJson = personne.toJson();
            JSONArray jsonArray = getJsonArray();
            jsonArray.add(contactJson);
            FileWriter fwriter = new FileWriter(
                    getClass().getResource("/files/json/myJson.json").getPath()
            );
            fwriter.write(jsonArray.toJSONString());
            fwriter.flush();
            fwriter.close();
            System.out.println("contact enregistré");
        } catch (Exception e) {
            System.out.println("erreur => " + e.getMessage());
        }
    }
}
