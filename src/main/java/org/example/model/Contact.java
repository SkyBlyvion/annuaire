package org.example.model;

import org.json.simple.JSONObject;

public class Contact {

    // propriété de l'objet
    private String nom;
    private String prenom;
    private String tel;
    private String email;

    // Constructeur vide
    public Contact() {}

    // Constructeur avec paramètres
    public Contact(String nom, String prenom, String tel, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
    }

    // getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /*
    * methode de conversion en json
    * @return
    * */
    public JSONObject toJson() {
        JSONObject contactJson = new JSONObject();
        contactJson.put("nom", nom);
        contactJson.put("prenom", prenom);
        contactJson.put("tel", tel);
        contactJson.put("email", email);
        return contactJson;
    }
}
