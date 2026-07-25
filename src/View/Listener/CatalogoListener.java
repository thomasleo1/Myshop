package View.Listener;

import Business.SessionManager;
import Business.UtenteBusiness;
import Model.Cliente;
import Model.Utente;
import View.FinestraIniziale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CatalogoListener implements ActionListener {

    private FinestraIniziale finestra;

    public CatalogoListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        if (utente instanceof Cliente) {
            UtenteBusiness utenteBusiness = UtenteBusiness.getInstance();
            Cliente cliente = utenteBusiness.getCliente(utente.getIdUtente());
            finestra.mostraCatalogo(cliente.getPuntoVendita().getIdPuntoVendita());
        } else {
            finestra.mostraCatalogo(0);
        }
        HashMap<String, Object> sessione = SessionManager.getSession();

        if (!sessione.containsKey(SessionManager.LOGGED_USER)) {
            finestra.mostraLogin();
        }

    }

    public void setFinestra(FinestraIniziale finestra) {
        this.finestra = finestra;
    }
}
