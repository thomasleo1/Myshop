package Model;

public class Utente {

    public enum Stato {ATTIVO, DISABILITATO, CANCELLATO}

    private int idUtente;
    private String nome;
    private String cognome;
    private int eta;
    private String residenza;
    private String professione;
    private String email;
    private String telefono;
    private String username;
    private String password;
    private String tipo;
    private Stato stato;

    public Utente() {
        idUtente = 0;
        nome = "";
        cognome = "";
        username = "";
        password = "";
        email = "";
        tipo = "";
    }

    public Utente(String user, String pwd, String name, String surname, String mail) {
        this.username = user;
        this.password = pwd;
        this.nome = name;
        this.cognome = surname;
        this.email = mail;
    }

    public Utente(String nome, String cognome, int eta, String telefono, String email, String residenza, String professione, String username, String password, String tipo, Stato stato) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.residenza = residenza;
        this.professione = professione;
        this.telefono = telefono;
        this.email = email;
        this.username = username;
        this.password = password;
        this.tipo = tipo;
        this.stato = stato;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getProfessione() {
        return professione;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

}
