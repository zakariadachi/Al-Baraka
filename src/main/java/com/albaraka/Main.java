package com.albaraka;

import com.albaraka.entity.Client;
import com.albaraka.service.ClientService;
import com.albaraka.service.CompteService;
import com.albaraka.service.TransactionService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientService clientService = new ClientService();
        CompteService compteService = new CompteService();
        TransactionService transactionService = new TransactionService();

        boolean running = true;
        
        System.out.println("=== Bienvenue à la banque Al Baraka ===");

        while (running) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Gérer les Clients");
            System.out.println("2. Gérer les Comptes");
            System.out.println("3. Opérations bancaires");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");
            
            int choix = -1;
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                scanner.nextLine();
            }

            switch (choix) {
                case 1:
                    menuClients(scanner, clientService);
                    break;
                case 2:
                    menuComptes(scanner, compteService, clientService);
                    break;
                case 3:
                    menuOperations(scanner, compteService, transactionService);
                    break;
                case 0:
                    running = false;
                    System.out.println("Merci d'avoir utilisé la banque Al Baraka. Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
        scanner.close();
    }

    private static void menuClients(Scanner scanner, ClientService clientService) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Gestion des Clients ---");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Afficher tous les clients");
            System.out.println("3. Rechercher un client");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choix : ");
            
            int choix = scanner.hasNextInt() ? scanner.nextInt() : -1;
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nom du client : ");
                    String nom = scanner.nextLine();
                    System.out.print("Email du client : ");
                    String email = scanner.nextLine();
                    clientService.createClient(new Client(0, nom, email));
                    System.out.println("✅ Client ajouté avec succès.");
                    break;
                case 2:
                    List<Client> clients = clientService.getAllClients();
                    if (clients.isEmpty()) {
                        System.out.println("Aucun client trouvé.");
                    } else {
                        clients.forEach(c -> System.out.println(c.id() + " - " + c.nom() + " (" + c.email() + ")"));
                    }
                    break;
                case 3:
                    System.out.print("ID du client : ");
                    if (scanner.hasNextInt()) {
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        clientService.getClientById(id).ifPresentOrElse(
                            c -> System.out.println("Trouvé : " + c.nom() + " - " + c.email()),
                            () -> System.out.println("❌ Client non trouvé.")
                        );
                    } else {
                        scanner.nextLine();
                        System.out.println("❌ ID invalide.");
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("❌ Choix invalide.");
            }
        }
    }

    private static void menuComptes(Scanner scanner, CompteService compteService, ClientService clientService) {
        System.out.println("\n(Menu Gestion des Comptes en cours de construction...)");
        // Vous pourrez implémenter la création et l'affichage des comptes ici
    }

    private static void menuOperations(Scanner scanner, CompteService compteService, TransactionService transactionService) {
        System.out.println("\n(Menu Opérations Bancaires en cours de construction...)");
        // Vous pourrez implémenter les dépôts, retraits et virements ici
    }
}
