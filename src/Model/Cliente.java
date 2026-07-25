package Model;

import Business.Factory.NotificaFactory;

import java.util.List;

public class Cliente extends Utente {

    private int idCliente;
    private List<ListaAcquisto> listaAcquisto;
    private PuntoVendita puntoVendita;
    private NotificaFactory.TipoNotifica notificaPreferita;

    public Cliente() {
    }

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<ListaAcquisto> getListaAcquisto() {
        return listaAcquisto;
    }

    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.puntoVendita = new PuntoVendita(idPuntoVendita);
    }

    public void setListaAcquisto(List<ListaAcquisto> listaAcquisto) {
        this.listaAcquisto = listaAcquisto;
    }


    public NotificaFactory.TipoNotifica getNotificaPreferita() {
        return notificaPreferita;
    }

    public void setNotificaPreferita(NotificaFactory.TipoNotifica notificaPreferita) {
        this.notificaPreferita = notificaPreferita;
    }
}
