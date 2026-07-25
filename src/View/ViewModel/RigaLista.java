package View.ViewModel;

import Model.ListaAcquisto;

import java.util.Date;

public class RigaLista {

    private int idLista;
    private String nomeLista;
    private Date dataCreazione;
    private ListaAcquisto.StatoLista statoLista;
    private Float prezzo;
    private String prodotti;
    private String servizi;
    private Boolean selezionato;

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public ListaAcquisto.StatoLista getStatoLista() {
        return statoLista;
    }

    public void setStatoLista(ListaAcquisto.StatoLista statoLista) {
        this.statoLista = statoLista;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public String getProdotti() {
        if (prodotti == null) {
            return "";
        } else {
            return prodotti;
        }
    }

    public void setProdotti(String prodotti) {
        this.prodotti = prodotti;
    }

    public String getServizi() {
        if (servizi == null) {
            return "";
        } else {
            return servizi;
        }
    }

    public void setServizi(String servizi) {
        this.servizi = servizi;
    }

    public Boolean getSelezionato() {
        return selezionato;
    }

    public void setSelezionato(Boolean selezionato) {
        this.selezionato = selezionato;
    }
}
