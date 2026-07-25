package Model;

import Model.Composite.IProdotto;
import Model.Composite.Prodotto;

import java.util.Date;
import java.util.List;

public class Ordine {

    private int idOrdine;
    private IProdotto prodotto;
    private int quantita;

    public Ordine() {
    }

    public Ordine(int idOrdine, IProdotto prodotto, int quantita, boolean eseguito) {
        this.idOrdine = idOrdine;
        this.prodotto = prodotto;
        this.quantita = quantita;


    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public IProdotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(IProdotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
