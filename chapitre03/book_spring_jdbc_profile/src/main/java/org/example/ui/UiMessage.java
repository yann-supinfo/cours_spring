package org.example.ui;

public class UiMessage {

    public static void displayProfil(String profil) {
        System.out.println("Actual Profil ("+ profil +")");
    }

    public static void displayWelcomeMessage()  {
        System.out.println("1 - Ajouter un livre");
        System.out.println("2 - Afficher la liste des livres");
        System.out.println("3 - Emprunter un livre");
        System.out.println("4 - Ajouter un client");
        System.out.println("5 - Afficher la liste des clients");
        System.out.println("6 - Supprimer un client");
        System.out.println("quit - Quitter la bibliothèque\n");
    }

}
