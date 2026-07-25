package View.ViewModel;

import Model.Utente;

public class RigaUtente {

    private int id;
    private String nome;
    private String cognome;
    private String email;
    private Utente.Stato stato;
    private boolean selezionato;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Utente.Stato getStato() {
        return stato;
    }

    public void setStato(Utente.Stato stato) {
        this.stato = stato;
    }

    public boolean isSelezionato() {
        return selezionato;
    }

    public void setSelezionato(boolean selezionato) {
        this.selezionato = selezionato;
    }
}
