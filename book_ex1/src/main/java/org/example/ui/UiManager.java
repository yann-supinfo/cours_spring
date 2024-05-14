package org.example.ui;

import org.example.application.controller.BookAdminController;
import org.example.application.controller.BookController;
import org.example.application.controller.BookMemberController;
import org.example.domain.Book;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UiManager {

    private Scanner sc = null;
    private String mode = "welcome";
    private Boolean running = true;
    private BookController bookController = null;
    private BookMemberController bookMemberController = null;
    private BookAdminController bookAdminController = null;


    private String profil;



    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }



    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public UiManager() {
        this.sc = new Scanner(System.in);
        this.bookController = new BookController();
        this.bookMemberController = new BookMemberController();
        this.bookAdminController = new BookAdminController();
        setProfil("standard");
    }


    public void handleProfilSelection() {
        System.out.println("Choisissez votre profil : ");
        System.out.println("- standard ");
        System.out.println("- member ");
        System.out.println("- admin ");
        setProfil(sc.next());
        setMode("welcome");
    }

    public void handleDisplayBook() {
        System.out.println("------- Liste des livres ----------");
        List<Book> books = null;
        switch (profil) {
            case "standard":
                books =  bookController.getBooks();
                break;
            case "member":
                books =   bookMemberController.getBooks();
                break;
            case "admin":
                books =  bookAdminController.getBooks();
                break;
        }
        for (int i = 0; i < books.size(); i++) {
            books.get(i).displayLessInformation(i);
        }
        if (0 == books.size()) {
            System.out.println("Il n'y a aucun livre actuellement..");
        }
        System.out.println("-----------------------------------");
        setMode("welcome");
    }
    public void handleCreateBook() {
        System.out.println("Ajouter un livre à la bibliothèque");
        System.out.println("Nom du livre: ");
        String name = sc.next();
        System.out.println("Auteur: ");
        String author = sc.next();
        System.out.println("Nombre de page: ");
        int pageNumber = Integer.valueOf(sc.next());
        System.out.println("Categorie du livre: ");
        String type = sc.next();
        switch (profil) {
            case "standard":
                bookController.addBook(name, author, pageNumber, type);
                break;
            case "member":
                bookMemberController.addBook(name, author, pageNumber, type);
                break;
            case "admin":
                bookAdminController.addBook(name, author, pageNumber, type);
                break;
        }
        setMode("welcome");
    }
    public void handleBorrowBook() {
        System.out.println("Index du livre à emprunter: ");
        int index = Integer.valueOf(sc.next());
        switch (profil) {
            case "standard":
                bookController.borrowBook(index);
                break;
            case "member":
                bookMemberController.borrowBook(index);
                break;
            case "admin":
                bookAdminController.borrowBook(index);
                break;
        }
        setMode("welcome");
    }

    public void handleWelcomeInput() {

        UiMessage.displayWelcomeMessage();
        String userInput = sc.next();

        switch (userInput) {
            case "1":
                setMode("add");
                break;
            case "2":
                setMode("display");
                break;
            case "3":
                setMode("borrow");
                break;
            case "4":
                setMode("profil");
                break;
            case "quit":
                setRunning(false);
                break;
            default:
                System.out.println("Enter valid input");

        }


    }

    public void run() {

        System.out.println("Bienvenue à la bibliothèque");



        while (true) {

            UiMessage.displayProfil(profil);
            switch (mode) {
                case "welcome":
                    handleWelcomeInput();
                    break;
                case "display":
                    handleDisplayBook();
                    break;
                case "add":
                    handleCreateBook();
                    break;
                case "borrow":
                    handleBorrowBook();
                    break;
                case "profil":
                    handleProfilSelection();
                    break;
            }



            if (getRunning() == false) {
                break;
            }
        }
        System.out.println("Au revoir");
    }

}
