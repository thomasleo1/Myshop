package View.Listener;

import Business.SessionManager;
import Model.Utente;
import View.FinestraIniziale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutListener implements ActionListener {

    private FinestraIniziale frame;

    public LogoutListener(FinestraIniziale frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SessionManager.getSession().clear();
        frame.mostraFinestraIniziale();
    }
}
