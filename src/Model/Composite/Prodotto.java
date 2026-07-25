package Model.Composite;

import Model.ICategoria;
import Model.CategoriaProdotto;
import Model.Collocazione;
import Model.Produttore;
import Model.Recensione;

import java.util.List;

public class Prodotto implements IProdotto{

    private int idProdotto;
    private String nome;
    private String descrizione;
    private Float prezzo;
    private Produttore produttore;
    private Collocazione collocazione;
    private ICategoria categoria;
    private ICategoria sottocategoria;
    private Recensione recensione;
    private String immagine;

    public Prodotto() {
    }

    public Prodotto(String nome) {
        this.nome = nome;
    }

    public Prodotto(String nome, String descrizione, Float prezzo, Produttore produttore, Collocazione collocazione,ICategoria categoria, ICategoria sottocategoria, Recensione recensione, String immagine) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.produttore = produttore;
        this.collocazione = collocazione;
        this.categoria = categoria;
        this.sottocategoria = sottocategoria;
        this.recensione = recensione;
        this.immagine = immagine;
    }


    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    @Override
    public Float getPrezzo() {
        return prezzo;
    }

    @Override
    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public int getId() {
        return idProdotto;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(int idProduttore, String nome, String sitoWeb, String citta, String nazione) {
        this.produttore = new Produttore(idProduttore, nome, sitoWeb, citta, nazione);
    }

    @Override
    public Collocazione getCollocazione() {
        return collocazione;
    }

    public void setCollocazione(int idCollocazione, int corsia, int scaffale) {
        this.collocazione = new Collocazione(idCollocazione, corsia, scaffale);
    }

    @Override
    public ICategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(int idCategoria, String nome) {
        this.categoria = new CategoriaProdotto(idCategoria, nome);
    }

    @Override
    public ICategoria getSottocategoria() {
        return sottocategoria;
    }

    public void setSottocategoria(int idCategoria, String nome) {
        this.sottocategoria = new CategoriaProdotto(idCategoria, nome);
    }

    @Override
    public Recensione getRecensione() {
        return recensione;
    }

    public void setRecensione(int idRecensione, String testo, Recensione.Feedback feedback) {

        this.recensione = new Recensione(idRecensione,testo,feedback);
    }

    @Override
    public String getImmagine() {
        return immagine;
    }

    @Override
    public List<Prodotto> getSottoprodotti() {
        return null;
    }


    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    @Override
    public String toString() {
        return nome;
    }


}
