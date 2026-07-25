package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaAcquisto {

    public enum StatoLista {DA_PAGARE, PAGATA}

    private int idListaAcquisto;
    private String nome;
    private List<ProdottoMagazzino> articoli = new ArrayList<>();
    private List<Servizio> servizi = new ArrayList<>();
    private Date data;
    private StatoLista statoLista;
    private Float prezzo;

    public ListaAcquisto() {
    }

    public ListaAcquisto(String nome, List<ProdottoMagazzino> articoli, Date data, StatoLista statoLista) {
        this.nome = nome;
        this.articoli = articoli;
        this.data = data;
        this.statoLista = statoLista;
    }

    public ListaAcquisto(String nome, Date data) {
        this.nome = nome;
        this.data = data;
    }

    public ListaAcquisto(String nome) {
        this.nome = nome;
    }

    public int getIdListaAcquisto() {
        return idListaAcquisto;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProdottoMagazzino> getArticoli() {
        return articoli;
    }

    public void addArticolo(ProdottoMagazzino prodotto) {
        articoli.add(prodotto);
    }
    public boolean checkIfProdottoDoesNotExists(ProdottoMagazzino prodottoMagazzino) {
        for (ProdottoMagazzino prodottoMagazzino1 : this.articoli) {
            if (prodottoMagazzino.getProdotto().getId() == prodottoMagazzino1.getProdotto().getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfServizioDoesNotExists(Servizio servizio) {
        for (Servizio s1 : this.servizi) {
            if (s1.getId() == servizio.getId()) {
                return true;
            }
        }
        return false;
    }
    public void addServizo(Servizio servizio) {
        servizi.add(servizio);
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public StatoLista getStatoLista() {
        return statoLista;
    }

    public void setStatoLista(StatoLista statoLista) {
        this.statoLista = statoLista;
    }

    public void setIdListaAcquisto(int idListaAcquisto) {
        this.idListaAcquisto = idListaAcquisto;
    }

    public List<Servizio> getServizi() {
        return servizi;
    }

    public void setServizi(List<Servizio> servizi) {
        this.servizi = servizi;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return nome;
    }
}
