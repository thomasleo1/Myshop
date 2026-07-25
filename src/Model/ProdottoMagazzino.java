package Model;

import Model.Composite.IProdotto;

public class ProdottoMagazzino {

    private IProdotto prodotto;
    private boolean disponibilita;
    private int quantita;

    public ProdottoMagazzino(IProdotto prodotto, boolean disponibilita, int quantita) {
        this.prodotto = prodotto;
        this.disponibilita = disponibilita;
        this.quantita = quantita;
    }

    public ProdottoMagazzino() {
    }

    public IProdotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(IProdotto prodotto) {
        this.prodotto = prodotto;
    }

    public boolean isDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita() {
        this.disponibilita = this.quantita > 0;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
