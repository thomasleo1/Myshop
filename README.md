# 🛒 MyShop - Java Desktop Application & Software Architecture

[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.java.com)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Tests](https://img.shields.io/badge/JUnit-Passing-brightgreen.svg)](https://junit.org/)

**MyShop** è un'applicazione desktop in Java sviluppata per la gestione di totem multimediali all'interno di punti vendita di una grande catena di arredamento e prodotti per la casa. 

Il progetto è stato sviluppato per l'esame di **Principi di Ingegneria del Software** (Università del Salento) seguendo la metodologia di sviluppo agile **Scrum** e applicando rigorosamente i principali **Design Pattern GoF**, architettura di persistenza tramite **DAO Pattern**, e test d'unità con **JUnit**.

---

## 👥 Profilazione Utenti e Funzionalità

L'applicazione gestisce un sistema di autorizzazioni a livelli in base al profilo utente:

* **👨‍💼 Amministratore:**
  * Gestione completa del catalogo (CRUD su prodotti, prodotti compositi e servizi).
  * Gestione gerarchica di categorie e sottocategorie (Mobili, Illuminazione, Montaggio, Trasporto, ecc.).
  * Configurazione dei punti vendita fisici e assegnazione dei rispettivi Manager.
* **👔 Manager:**
  * Gestione delle giacenze di magazzino e rifornimento scorte.
  * Gestione degli ordini per articoli non disponibili.
  * Moderazione utenti e invio di comunicazioni e-mail dirette ai clienti.
  * Risposta ai feedback e alle recensioni sui prodotti del proprio punto vendita.
* **🛍️ Utente Acquirente (Cliente):**
  * Consultazione catalogo e disponibilità specifica per punto vendita.
  * Creazione e salvataggio di **Liste d'Acquisto** esportabili in **PDF** con invio automatico via e-mail.
  * Prenotazione di prodotti esauriti con recapito in negozio.
  * Possibilità di lasciare recensioni e punteggi di gradimento (1-5 stelle) sugli articoli acquistati.
* **👁️ Utente Guest:**
  * Navigazione libera del catalogo globale e registrazione a uno specifico punto vendita.

---

## 🏗️ Architettura Software & Design Patterns

Il software è stato progettato enfatizzando l'estensibilità, il riuso del codice e il disaccoppiamento tra moduli.

### Design Patterns Implementati
* **Composite:** Utilizzato per modellare la struttura dei `ProdottiCompositi` (un articolo composto da più prodotti singoli).
* **Strategy:** Implementato nei filtri della tabella catalogo per l'ordinamento dinamico degli articoli (es. per nome o prezzo).
* **Factory:** Utilizzato per la generazione e gestione delle notifiche inviate dai Manager ai Clienti.
* **Command:** Impiegato per incapsulare le transazioni e le operazioni sul Database.
* **Singleton:** Applicato alle classi DAO e al servizio `UtenteBusiness` per garantire un'unica istanza d'accesso.
* **Observer:** Utilizzato dai Listener degli eventi dell'Interfaccia Grafica (GUI).
* **Decorator:** Usato per la gestione dinamica e la visualizzazione dei pulsanti UI in base al ruolo dell'utente loggato.
* **Bridge:** Applicato nella generazione dei report PDF delle liste d'acquisto e nell'invio delle e-mail.

---

## 🗄️ Modello Dati (MySQL)

Il database relazionale MySQL include tabelle di mapping sofisticate per gestire le relazioni $N:M$:
* **Gestione Catalogo:** `prodotto`, `prodotto_composito`, `servizio`, `categoria_prodotto`, `sottocategoria`, `produttore`.
* **Ubicazione e Stock:** `magazzino`, `prodotti_magazzino` (associazione stock per negozio), `collocazione` (Corsia e Scaffale).
* **Interazioni Utenti:** `lista_acquisto`, `ordine`, `recensione`, `associazione_prodotti_lista`.

---

## 🧪 Testing & Qualità del Codice

La componente di persistenza dei dati (DAO) è coperta da unit test automatizzati realizzati con **JUnit**:
* `UtenteDAOTest`, `ProdottoDAOTest`, `ListaAcquistoDAOTest`, `PuntoVenditaDAOTest`, `MagazzinoDAOTest`, `RecensioneDAOTest`, e altri.
* Verifica delle operazioni di CRUD, ricerca, associazione tabelle e controllo credenziali.

---

## 💻 Come Eseguire il Progetto

### Requisiti Prerequisiti
* **Java Development Kit (JDK):** Versione 17 o superiore
* **Database:** MySQL Server
* **IDE consigliato:** IntelliJ IDEA / Cursor

### Procedura
1. **Clona la repository:**
   ```bash
   git clone [https://github.com/thomasleo1/Myshop.git](https://github.com/thomasleo1/Myshop.git)