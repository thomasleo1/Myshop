package Model.Composite;

import Model.ICategoria;
import Model.CategoriaProdotto;
import Model.Collocazione;
import Model.Produttore;
import Model.Recensione;

import java.util.ArrayList;
import java.util.List;

public class ProdottoComposito implements IProdotto {

    private int idProdottoComposito;
    private String nome;
    private String descrizione;
    private Float prezzo;
    private Collocazione collocazione;
    private Produttore produttore;
    private ICategoria categoria;
    private Recensione recensione;
    private String immagine;
    private List<Prodotto> sottoprodotti = new ArrayList<>();

    public ProdottoComposito() {

    }

    public ProdottoComposito(String nome, String descrizione, Produttore produttore, Collocazione collocazione,ICategoria categoria, Recensione recensione, String immagine) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.produttore = produttore;
        this.collocazione = collocazione;
        this.categoria = categoria;
        this.recensione = recensione;
        this.immagine = immagine;
    }


    public void add(Prodotto prodotto) {
        sottoprodotti.add(prodotto);
    }

    public void add(List<Prodotto> prodotti) {
        sottoprodotti.addAll(prodotti);
    }


    @Override
    public int getId() {
        return idProdottoComposito;
    }


    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }


    @Override
    public Float getPrezzo() {

        prezzo = 0F;
        for (IProdotto prodotto : sottoprodotti) {
            prezzo += prodotto.getPrezzo();
        }
        prezzo = prezzo - (prezzo * 10 / 100);
        return prezzo;
    }

    @Override
    public Collocazione getCollocazione() {
        return collocazione;
    }

    public void setCollocazione(int idCollocazione, int corsia, int scaffale) {
        this.collocazione = new Collocazione(idCollocazione, corsia, scaffale);
    }
    public void setIdProdottoComposito(int idProdottoComposito) {
        this.idProdottoComposito = idProdottoComposito;
    }

    @Override
    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(int idProduttore, String nome, String sitoWeb, String citta, String nazione) {
        this.produttore = new Produttore(idProduttore, nome, sitoWeb, citta, nazione);
    }

    @Override
    public ICategoria getCategoria() {
        return categoria;
    }

    @Override
    public ICategoria getSottocategoria() {
        return null;
    }

    public void setCategoria(int idCategoria, String nome) {
        this.categoria = new CategoriaProdotto(idCategoria, nome);
    }

    @Override
    public Recensione getRecensione() {
        return recensione;
    }

    public void setRecensione(int idRecensione, String testo, Recensione.Feedback feedback) {
        this.recensione = new Recensione(idRecensione,testo, feedback);
    }


    @Override
    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }


    public List<Prodotto> getSottoprodotti() {
        return sottoprodotti;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public void setSottoprodotti(List<Prodotto> list) {
        this.sottoprodotti.clear();
        this.sottoprodotti.addAll(list);
    }
}

