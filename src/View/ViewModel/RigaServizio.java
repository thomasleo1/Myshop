package View.ViewModel;

import Model.Recensione;

public class RigaServizio {

    private int idServizio;
    private String nomeServizio;
    private String descrizione;
    private String nomeProduttore;
    private Float prezzo;
    private String nomeCategoria;
    private String recensione;
    private Boolean selezionato;

    public int getIdServizio() {
        return idServizio;
    }

    public void setIdServizio(int idServizio) {
        this.idServizio = idServizio;
    }

    public String getNomeServizio() {
        return nomeServizio;
    }

    public void setNomeServizio(String nomeServizio) {
        this.nomeServizio = nomeServizio;
    }

    public String getNomeProduttore() {
        return nomeProduttore;
    }

    public void setNomeProduttore(String nomeProduttore) {
        this.nomeProduttore = nomeProduttore;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getRecensione() {
        return recensione;
    }

    public void setRecensione(Recensione.Feedback feedback, String testo) {
        this.recensione = feedback + ", " + testo;
    }
    public Boolean getSelezionato() {
        return selezionato;
    }

    public void setSelezionato(Boolean selezionato) {
        this.selezionato = selezionato;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
