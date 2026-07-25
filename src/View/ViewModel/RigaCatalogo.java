package View.ViewModel;


import Model.Composite.IProdotto;
import Model.Recensione;

import java.util.List;

public class RigaCatalogo {

    private int idProdotto;
    private String nomeProdotto;
    private String nomeSottoprodotti;
    private String descrizione;
    private String nomeProduttore;
    private Float prezzo;
    private String nomeCategoria;
    private String nomeSottocategoria;
    private String recensione;
    private String collocazione;
    private String immagine;
    private Boolean selezionato;
    private Boolean isComposito;

    public Boolean getSelezionato() {
        return selezionato;
    }

    public void setSelezionato(Boolean selezionato) {
        this.selezionato = selezionato;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
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

    public String getNomeSottocategoria() {
        return nomeSottocategoria;
    }

    public void setNomeSottocategoria(String nomeSottocategoria) {
        this.nomeSottocategoria = nomeSottocategoria;
    }

    public String getRecensione() {
        return recensione;
    }

    public void setRecensione(Recensione.Feedback feedback, String testo) {
        this.recensione = feedback + ", " + testo;
    }

    public String getCollocazione() {
        return collocazione;
    }

    public void setCollocazione(int scaffale, int corsia) {
        this.collocazione = "corsia: " + corsia + ", scaffale: " + scaffale;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getNomeSottoprodotti() {
        return nomeSottoprodotti;
    }

    public void setNomeSottoprodotti(String nomeSottoprodotti) {
        this.nomeSottoprodotti = nomeSottoprodotti;
    }


    public Boolean getIsComposito() {
        return this.isComposito;
    }

    public void setIsComposito(Boolean composito) {
        this.isComposito = composito;
    }

    public String getDescrizione() {
        if (this.descrizione == null) {
            return "";
        } else {
            return this.descrizione;
        }
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
