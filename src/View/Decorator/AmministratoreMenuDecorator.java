package View.Decorator;

import View.FinestraIniziale;
import View.Listener.AmministratoreListener;

import javax.swing.*;
import java.util.List;

public class AmministratoreMenuDecorator extends CustomMenuDecorator{

    private FinestraIniziale frame;

    public AmministratoreMenuDecorator(Menu menu, FinestraIniziale frame) {
        this.menu = menu;
        this.frame = frame;
    }

    @Override
    public List<JButton> getPulsanti() {
        pulsanti.addAll(this.menu.getPulsanti());

        JButton prodotto = new JButton("Aggiungi Prodotto");
        AmministratoreListener listenerProdotto = new AmministratoreListener();
        listenerProdotto.setFrame(frame);
        prodotto.addActionListener(listenerProdotto);
        prodotto.setActionCommand(AmministratoreListener.AGGIUNGI_PRODOTTO);
        pulsanti.add(prodotto);

        JButton servizio = new JButton("Aggiungi Servizio");
        AmministratoreListener listenerServizio = new AmministratoreListener();
        listenerServizio.setFrame(frame);
        servizio.addActionListener(listenerServizio);
        servizio.setActionCommand(AmministratoreListener.AGGIUNGI_SERVIZIO);
        pulsanti.add(servizio);

        JButton prodottoComposito = new JButton("Aggiungi Prodotto Composito");
        AmministratoreListener listenerPComposito = new AmministratoreListener();
        listenerPComposito.setFrame(frame);
        prodottoComposito.addActionListener(listenerPComposito);
        prodottoComposito.setActionCommand(AmministratoreListener.AGGIUNGI_PRODOTTO_COMPOSITO);
        pulsanti.add(prodottoComposito);

        JButton categoria = new JButton("Aggiungi Categoria");
        AmministratoreListener listenerCategoria = new AmministratoreListener();
        listenerCategoria.setFrame(frame);
        categoria.addActionListener(listenerCategoria);
        categoria.setActionCommand(AmministratoreListener.AGGIUNGI_CATEGORIA);
        pulsanti.add(categoria);

        JButton produttore = new JButton("Aggiungi Produttore");
        AmministratoreListener listenerProduttore = new AmministratoreListener();
        listenerProduttore.setFrame(frame);
        produttore.addActionListener(listenerProduttore);
        produttore.setActionCommand(AmministratoreListener.AGGIUNGI_PRODUTTORE);
        pulsanti.add(produttore);

        JButton manager = new JButton("Aggiungi Manager");
        AmministratoreListener listenerManager = new AmministratoreListener();
        listenerManager.setFrame(frame);
        manager.addActionListener(listenerManager);
        manager.setActionCommand(AmministratoreListener.AGGIUNGI_MANAGER);
        pulsanti.add(manager);

        JButton puntoVendita = new JButton("Aggiungi Punto Vendita");
        AmministratoreListener listenerPuntoVendita = new AmministratoreListener();
        listenerPuntoVendita.setFrame(frame);
        puntoVendita.addActionListener(listenerManager);
        puntoVendita.setActionCommand(AmministratoreListener.AGGIUNGI_PUNTO_VENDITA);
        pulsanti.add(puntoVendita);

        return pulsanti;
    }
}
