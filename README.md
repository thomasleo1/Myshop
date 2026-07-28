# MyShop - Java Desktop Application & Software Architecture

[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.java.com)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Tests](https://img.shields.io/badge/JUnit-Passing-brightgreen.svg)](https://junit.org/)

**MyShop** is a Java desktop application developed to manage multimedia kiosks within retail stores of a major home furnishing and decor chain.

The project was developed for the **Software Engineering Principles** course (*Università del Salento*) following the **Scrum** agile development methodology and strictly applying core **GoF Design Patterns**, a **DAO Pattern** persistence architecture, and unit testing with **JUnit**.

---

## User Roles & Features

The application features a role-based access control system according to the user profile:

* ** Administrator:**
  * Full catalog management (CRUD operations on products, composite products, and services).
  * Hierarchical management of categories and subcategories (Furniture, Lighting, Assembly, Shipping, etc.).
  * Configuration of physical store locations and manager assignments.
* ** Manager:**
  * Warehouse stock management and inventory replenishment.
  * Order management for out-of-stock items.
  * User moderation and direct email communication with customers.
  * Responding to feedback and reviews for products in their assigned store.
* ** Buyer (Customer):**
  * Catalog browsing and store-specific availability checks.
  * Creation and saving of **Shopping Lists**, exportable to **PDF** with automated email dispatch.
  * Reservations for out-of-stock products with store pickup.
  * Rating (1–5 stars) and review submission for purchased products.
* ** Guest User:**
  * Free browsing of the global catalog and registration to a specific store location.

---

## Software Architecture & Design Patterns

The software architecture emphasizes extensibility, code reusability, and loose coupling between modules.

### Implemented Design Patterns
* **Composite:** Used to model composite products (`ProdottiCompositi`), which are items made up of multiple individual products.
* **Strategy:** Applied in catalog table filters for dynamic item sorting (e.g., by name or price).
* **Factory:** Used to generate and handle notifications sent from Managers to Customers.
* **Command:** Encapsulates transactions and operations performed on the Database.
* **Singleton:** Applied to DAO classes and the `UtenteBusiness` service to ensure a single point of access.
* **Observer:** Utilized by GUI event listeners.
* **Decorator:** Handles dynamic rendering and display of UI buttons based on the logged-in user's role.
* **Bridge:** Applied in PDF report generation for shopping lists and email notifications.

---

## Data Model (MySQL)

The MySQL relational database includes mapping tables to manage $N:M$ relationships:
* **Catalog Management:** `prodotto`, `prodotto_composito`, `servizio`, `categoria_prodotto`, `sottocategoria`, `produttore`.
* **Location & Inventory:** `magazzino`, `prodotti_magazzino` (stock associated per store), `collocazione` (Aisle and Shelf).
* **User Interactions:** `lista_acquisto`, `ordine`, `recensione`, `associazione_prodotti_lista`.

---

## Testing & Code Quality

The persistence layer (DAO) is covered by automated unit tests built with **JUnit**:
* `UtenteDAOTest`, `ProdottoDAOTest`, `ListaAcquistoDAOTest`, `PuntoVenditaDAOTest`, `MagazzinoDAOTest`, `RecensioneDAOTest`, and others.
* Covers CRUD operations, search queries, table joins, and credential verification.

---

## How to Run the Project

### Prerequisites
* **Java Development Kit (JDK):** Version 17 or higher
* **Database:** MySQL Server
* **Recommended IDE:** IntelliJ IDEA / Cursor

### Setup Steps
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/thomasleo1/Myshop.git](https://github.com/thomasleo1/Myshop.git)
   
2. **MySQL Database Configuration:**
   * Open MySQL Workbench or MySQL CLI and create the database schema:
     ```sql
     CREATE DATABASE myshop;
     ```
   * Import the provided `.sql` script to generate the table structure and initial data.

3. **Credentials Configuration:**
   * Open the database configuration file (e.g., `Database.java` or `db.properties`).
   * Update the connection parameters with your local credentials:
     ```java
     String url = "jdbc:mysql://localhost:3306/myshop";
     String user = "root";
     String password = "yourpassword";
     ```

4. **Launching the Application:**
   * Open the project using your IDE (IntelliJ IDEA / Cursor).
   * Ensure the **Project SDK** is set to **JDK 17** or higher.
   * If applicable, allow the dependency manager (Maven/Gradle) to download the required packages (e.g., MySQL JDBC Driver, JUnit).
   * Locate the main class containing the `main` method (e.g., `Main.java` or `App.java`) and run it.

## Author

* **Thomas Leo**
  * **GitHub:** [@thomasleo1](https://github.com/thomasleo1)
  * **Academic Email:** [thomas.leo@studenti.unisalento.it](mailto:thomas.leo@studenti.unisalento.it)
  * **Personal Email:** [thomasleo2704@gmail.com](mailto:thomasleo2704@gmail.com)
  * **University:** Università del Salento
  * **Degree Program:** Information Engineering (*Ingegneria dell'Informazione*)
  * **Course:** Software Engineering Principles (*Principi di Ingegneria del Software*)
