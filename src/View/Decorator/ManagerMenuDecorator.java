package View.Decorator;

import View.FinestraIniziale;
import View.Listener.ManagerListener;

import javax.swing.*;
import java.util.List;

public class ManagerMenuDecorator extends CustomMenuDecorator{

    private FinestraIniziale frame;

    public ManagerMenuDecorator(Menu menu, FinestraIniziale frame) {
        this.menu = menu;
        this.frame = frame;
    }

    public List<JButton> getPulsanti() {
        pulsanti.addAll(this.menu.getPulsanti());

        JButton magazzino = new JButton("Gestici magazzino");
        ManagerListener listenerMagazzino = new ManagerListener(frame);
        magazzino.addActionListener(listenerMagazzino);
        magazzino.setActionCommand(ManagerListener.GESTISCI_MAGAZZINO);
        pulsanti.add(magazzino);

        JButton utenti = new JButton("Gestici utenti");
        ManagerListener listenerUtenti = new ManagerListener(frame);
        utenti.addActionListener(listenerUtenti);
        utenti.setActionCommand(ManagerListener.GESTISCI_UTENTI);
        pulsanti.add(utenti);

        JButton ordine = new JButton("Visualizza ordini ricevuti");
        ManagerListener listenerOrdine = new ManagerListener(frame);
        ordine.addActionListener(listenerOrdine);
        ordine.setActionCommand(ManagerListener.VISUALIZZA_ORDINI);
        pulsanti.add(ordine);


        return pulsanti;
    }
}
