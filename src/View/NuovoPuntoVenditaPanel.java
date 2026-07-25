package View;

import Business.*;
import Model.*;
import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import View.Listener.AmministratoreListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class NuovoPuntoVenditaPanel extends JPanel {
    private JComboBox<Magazzino> magazzinoCbx = new JComboBox<>();
    private JList<IProdotto> articoli;
    private List<IProdotto> articoliSelezionati = new ArrayList<>();
    private DefaultListModel<IProdotto> listArticoli = new DefaultListModel<>();

    public NuovoPuntoVenditaPanel(FinestraIniziale frame) {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JTextField nome = new JTextField(24);
        JTextField indirizzo = new JTextField(24);

        JLabel registrazione = new JLabel("Inserire i dati del punto vendita");
        JLabel nomeLabel = new JLabel("Nome: ");
        JLabel indirizzoLabel = new JLabel("Indirizzo: ");
        JLabel magazzinoLabel = new JLabel("Magazzino: ");
        JLabel articoliLabel = new JLabel("Articoli: ");

        addMagazzinoToDropdown();

        magazzinoCbx.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    addProdottiToList(false);
                }
            }
        });

        JButton aggiungiProdotti = new JButton("Aggiungi prodotto");
        aggiungiProdotti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProdottiSelezionati();
            }
        });

        addProdottiToList(true);


        JButton salva = new JButton("Salva");
        AmministratoreListener listener = new AmministratoreListener(nome, indirizzo, magazzinoCbx, articoliSelezionati);
        listener.setFrame(frame);
        salva.addActionListener(listener);
        salva.setActionCommand(AmministratoreListener.SALVA_PUNTO_VENDITA);

        gbc.gridy = 0;
        gbc.gridx = 2;
        add(registrazione, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        add(nomeLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 2;
        add(nome, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        add(indirizzoLabel, gbc);

        gbc.gridy = 2;
        gbc.gridx = 2;
        add(indirizzo, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        add(magazzinoLabel, gbc);

        gbc.gridy = 3;
        gbc.gridx = 2;
        add(magazzinoCbx, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        add(articoliLabel, gbc);

        gbc.gridy = 4;
        gbc.gridx = 2;
        add(articoli, gbc);

        gbc.gridy = 4;
        gbc.gridx = 3;
        add(aggiungiProdotti, gbc);

        gbc.gridy = 5;
        gbc.gridx = 2;
        add(salva, gbc);

    }


    private void addMagazzinoToDropdown() {
        MagazzinoBusiness magazzinoBusiness = new MagazzinoBusiness();

        java.util.List<Magazzino> dropdownValues =magazzinoBusiness.getMagazzini();
        for (Magazzino item : dropdownValues) {
            magazzinoCbx.addItem((item));
        }

    }

    private void addProdottiToList(boolean isFirstTime) {
        if (!isFirstTime) {
            listArticoli.removeAllElements();
        } else {
            articoli = new JList<>(listArticoli);
        }
        ProdottoBusiness prodottoBusiness = new ProdottoBusiness();

        Magazzino magazzino = (Magazzino) magazzinoCbx.getSelectedItem();

        java.util.List<Prodotto> listValues = prodottoBusiness.getProdottiByIdMagazzino(magazzino.getIdMagazzino());
        for (Prodotto item : listValues) {
            listArticoli.addElement(item);
        }
        ProdottoCompositoBusiness prodottoCompositoBusiness = new ProdottoCompositoBusiness();
        List<ProdottoComposito> prodottiCompositi = prodottoCompositoBusiness.getProdottiCompositiByIdMagazzino(magazzino.getIdMagazzino());
        for (ProdottoComposito item : prodottiCompositi) {
            listArticoli.addElement(item);
        }
    }

    private void addProdottiSelezionati() {
        int selectedIndex = articoli.getSelectedIndex();
        IProdotto selectedItem = listArticoli.getElementAt(selectedIndex);
        articoliSelezionati.add(selectedItem);
    }

}
