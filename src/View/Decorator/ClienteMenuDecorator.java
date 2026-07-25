package View.Decorator;

import View.FinestraIniziale;
import View.Listener.ClienteListener;
import View.Listener.RecensioneListener;

import javax.swing.*;
import java.util.List;

public class ClienteMenuDecorator extends CustomMenuDecorator {

    private FinestraIniziale frame;

    public ClienteMenuDecorator(Menu menu, FinestraIniziale frame) {
        this.menu = menu;
        this.frame = frame;
    }

    @Override
    public List<JButton> getPulsanti() {

        pulsanti.addAll(this.menu.getPulsanti());

        JButton liste = new JButton("Le mie liste");
        ClienteListener listener = new ClienteListener(frame);
        liste.addActionListener(listener);
        liste.setActionCommand(ClienteListener.MIE_LISTE);
        pulsanti.add(liste);

        JButton feedback = new JButton("Lascia feedback");
        RecensioneListener recensioneListener = new RecensioneListener(frame);
        feedback.addActionListener(recensioneListener);
        feedback.setActionCommand(RecensioneListener.MOSTRA_PRODOTTI_ACQUISTATI);
        pulsanti.add(feedback);


        return pulsanti;
    }
}
