# Al Baraka - Système de Gestion Bancaire 🏦

Al Baraka est une application Java en ligne de commande (CLI) conçue pour simuler les opérations de base d'un système bancaire. L'application permet la gestion des clients, des comptes bancaires (Courant et Épargne), ainsi que l'exécution et le suivi des transactions.

## Fonctionnalités Principales

*   **Gestion des Clients** : Ajouter de nouveaux clients, afficher la liste des clients et rechercher un client spécifique.
*   **Gestion des Comptes** : Création et gestion de Comptes Courants (avec découvert autorisé) et de Comptes Épargne (avec taux d'intérêt).
*   **Opérations Bancaires** : Effectuer des dépôts, des retraits et consulter l'historique des transactions.
*   **Architecture Structurée** : L'application suit un modèle en couches avec des Entités (Entity), des Objets d'Accès aux Données (DAO) et des Services (Service).

## Technologies Utilisées

*   **Langage** : Java (JDK 17+)
*   **Base de données** : PostgreSQL
*   **Accès aux données** : JDBC (Java Database Connectivity)

## Prérequis

Avant de lancer le projet, assurez-vous d'avoir installé les outils suivants :
*   [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) (version 17 ou supérieure)
*   [PostgreSQL](https://www.postgresql.org/download/)
*   Un IDE comme IntelliJ IDEA, Eclipse, ou VS Code.

## Installation et Configuration

1. **Cloner le dépôt :**
   ```bash
   git clone https://github.com/zakariadachi/Al-Baraka.git
   cd Al-Baraka
   ```

2. **Configuration de la base de données :**
   * Exécutez le script SQL `database.sql` dans votre environnement PostgreSQL pour créer les tables nécessaires.
   * L'application s'attend à trouver une base de données nommée `Al Baraka`.

3. **Configuration des identifiants (Important) :**
   * Créez un fichier nommé `config.properties` à la racine du projet (au même niveau que le dossier `src` et ce fichier `README.md`).
   * Ajoutez-y vos informations de connexion à PostgreSQL avec le format suivant :
     ```properties
     db.url=jdbc:postgresql://localhost:5432/Al Baraka
     db.user=votre_utilisateur_postgres
     db.password=votre_mot_de_passe
     ```
   * *Note : Ce fichier est ignoré par Git pour des raisons de sécurité.*

4. **Lancement de l'application :**
   * Compilez le projet et exécutez la classe principale `com.albaraka.Main`.
   * Suivez les instructions affichées dans la console pour naviguer dans les menus.

## Structure du Projet

```text
src/main/java/com/albaraka/
├── Main.java              # Point d'entrée et menu interactif
├── entity/                # Classes représentant les modèles de données
├── dao/                   # Logique d'accès et d'interaction avec la BD
└── service/               # Logique métier et intermédiaire avec le DAO
```
