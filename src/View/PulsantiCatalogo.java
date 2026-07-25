package View;

import Business.SessionManager;
import Model.Amministratore;
import Model.Cliente;
import Model.Utente;
import View.Listener.ClienteListener;
import View.Listener.MenuListener;
import View.Listener.TabellaListener;
import View.ViewModel.RigaCatalogo;
import View.ViewModel.RigaServizio;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PulsantiCatalogo extends JMenuBar {

    public PulsantiCatalogo(CatalogoPanel panel, CatalogoTableModel tableModel, ServizioTableModel tableServizio, JTable tabellaProdotti, JTable tabellaServizi, boolean isProdotto, boolean isProdottoDisponibile) {

        JFrame frame = new JFrame();
        frame.add(this);

        JMenu filtra = new JMenu("Filtra");
        this.add(filtra);

        JMenu ordina = new JMenu("Ordina");
        this.add(ordina);

        JMenu articoli = new JMenu("Articoli");
        this.add(articoli);

        JMenu menuCategorie = new JMenu(("Categorie"));
        JMenuItem menuPrezzo = new JMenuItem("Prezzo");
        JMenuItem menuNome = new JMenuItem("Nome");
        filtra.add(menuCategorie);
        ordina.add(menuPrezzo);
        ordina.add(menuNome);

        Set<String> categorieDistinte = new HashSet<>();
        if(isProdotto)
        {
            List<RigaCatalogo> righe = tableModel.getRighe();
            for (RigaCatalogo riga : righe) {
                categorieDistinte.add(riga.getNomeCategoria());
            }
        }
        else
        {
            List<RigaServizio> righeServizi = tableServizio.getRighe();
            for (RigaServizio riga : righeServizi) {
                categorieDistinte.add(riga.getNomeCategoria());
            }
        }

        MenuListener menuListener = new MenuListener(panel, tableModel, tableServizio, !isProdotto);

        for (String categoria : categorieDistinte) {
            JMenuItem menuItem = new JMenuItem(categoria);
            menuItem.addActionListener(menuListener);
            menuItem.setActionCommand(categoria);
            menuCategorie.add(menuItem);
        }

        menuPrezzo.addActionListener(menuListener);
        menuPrezzo.setActionCommand(MenuListener.PREZZO);

        menuNome.addActionListener(menuListener);
        menuNome.setActionCommand(MenuListener.NOME);

        JMenuItem menuProdotti = new JMenuItem("Prodotti");
        JMenuItem menuServizi = new JMenuItem("Servizi");
        articoli.add(menuProdotti);
        articoli.add(menuServizi);

        menuServizi.addActionListener(menuListener);
        menuServizi.setActionCommand(MenuListener.SERVIZIO);

        menuProdotti.addActionListener(menuListener);
        menuProdotti.setActionCommand(MenuListener.PRODOTTO);


        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        if (utente instanceof Amministratore) {

            JPanel pulsantiAzioneTabella = new JPanel();
            pulsantiAzioneTabella.setLayout(new FlowLayout());
            JButton cancellaRiga = new JButton("Cancella Riga");
            cancellaRiga.addActionListener(new TabellaListener(tableModel, tableServizio, tabellaProdotti, tabellaServizi));
            if (isProdotto) {
                cancellaRiga.setActionCommand(TabellaListener.CANCELLA_PRODOTTO);
            } else {
                cancellaRiga.setActionCommand(TabellaListener.CANCELLA_SERVIZIO);
            }
            pulsantiAzioneTabella.add(cancellaRiga);
            panel.add(pulsantiAzioneTabella, BorderLayout.SOUTH);
        } else if (utente instanceof Cliente) {
            JPanel pulsantiAzioneTabella = new JPanel();
            pulsantiAzioneTabella.setLayout(new FlowLayout());
            if (isProdottoDisponibile) {
                JButton aggiungiALista = new JButton("Aggiungi prodotti ad una Lista");
                aggiungiALista.addActionListener(new ClienteListener(tabellaProdotti, tableModel, tabellaServizi, tableServizio));
                aggiungiALista.setActionCommand(ClienteListener.AGGIUNGI_PRODOTTO_A_LISTA);
                pulsantiAzioneTabella.add(aggiungiALista);

                JButton prodottiNonDisponibili = new JButton("Visualizza prodotti non disponibili");
                prodottiNonDisponibili.addActionListener(new ClienteListener(panel, tabellaProdotti, tableModel));
                prodottiNonDisponibili.setActionCommand(ClienteListener.VISUALIZZA_PRODOTTI_NON_DISPONIBILI);
                pulsantiAzioneTabella.add(prodottiNonDisponibili);
            } else {
                JButton ordinaProdotto = new JButton("Ordina prodotto");
                ordinaProdotto.addActionListener(new ClienteListener(panel, tabellaProdotti, tableModel));
                ordinaProdotto.setActionCommand(ClienteListener.ORDINA_PRODOTTO);
                pulsantiAzioneTabella.add(ordinaProdotto);
            }
            panel.add(pulsantiAzioneTabella, BorderLayout.SOUTH);

        }
    }

}
