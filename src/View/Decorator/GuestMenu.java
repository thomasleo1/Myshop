package View.Decorator;

import Business.SessionManager;
import Model.Amministratore;
import Model.Manager;
import Model.Utente;
import View.FinestraIniziale;
import View.Listener.CatalogoListener;
import View.Listener.LogoutListener;
import View.Listener.RecensioneListener;

import javax.swing.*;

public class GuestMenu extends Menu {

    public GuestMenu(FinestraIniziale finestra) {

        JButton logout = new JButton("Logout");
        LogoutListener logoutListener = new LogoutListener(finestra);
        logout.addActionListener(logoutListener);
        pulsanti.add(logout);

        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        if (!(utente instanceof Manager)) {
            JButton sfogliaCatalogo = new JButton("Sfoglia catalogo");
            CatalogoListener listener = new CatalogoListener();
            listener.setFinestra(finestra);
            sfogliaCatalogo.addActionListener(listener);
            pulsanti.add(sfogliaCatalogo);
        }
        if (!(utente instanceof Amministratore)) {
            JButton vediFeedback = new JButton("Visualizza feedback lasciati");
            RecensioneListener vediFeedBackListener = new RecensioneListener(finestra);
            vediFeedback.addActionListener(vediFeedBackListener);
            vediFeedback.setActionCommand(RecensioneListener.VEDI_FEEDBACK_LASCIATI);
            pulsanti.add(vediFeedback);
        }

    }

}
