package View;

import Business.*;
import Model.*;
import Model.Composite.Prodotto;
import View.Listener.AmministratoreListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NuovoProdottoCompositoPanel extends JPanel {
    private JComboBox<Produttore> produttoreCbx = new JComboBox<>();
    private JComboBox<Magazzino> magazzinoCbx = new JComboBox<>();
    private JComboBox<Collocazione> collocazioneCbx = new JComboBox<>();
    private JComboBox<CategoriaProdotto> categoriaCbx = new JComboBox<>();
    private JComboBox<PuntoVendita> puntoVenditaCbx = new JComboBox<>();
    private JFrame imageSelectionFrame = new JFrame();
    private JButton scegliImmagine = new JButton("Scegli immagine");
    private File selectedFile;
    private JList<Prodotto> articoli;
    private List<Prodotto> articoliSelezionati = new ArrayList<>();
    private DefaultListModel<Prodotto> listArticoli = new DefaultListModel<>();
    public NuovoProdottoCompositoPanel(FinestraIniziale frame) {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JTextField nome = new JTextField(24);
        JTextField descrizione = new JTextField(24);

        addProduttoriToDropdown();
        addMagazzinoToDropdown();
        collocazioneCbx.setEnabled(false);
        magazzinoCbx.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Magazzino selectedItem = (Magazzino) magazzinoCbx.getSelectedItem();
                    addCollocazioniToDropdown(selectedItem.getIdMagazzino());
                    collocazioneCbx.setEnabled(true);
                    addProdottiToList(false);
                }
            }
        });
        addCategorieToDropdown();
        addProdottiToList(true);
        addPuntiVenditaToDropdown();

        imageSelectionFrame.setSize(400,400);
        imageSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel registrazione = new JLabel("Inserire i dati per aggiungere un prodotto composito");
        JLabel nomeLabel = new JLabel("Nome: ");
        JLabel descrizioneLabel = new JLabel("Descrizione: ");
        JLabel produttoreLabel = new JLabel("Produttore: ");
        JLabel magazzinoLabel = new JLabel("Magazzino: ");
        JLabel collocazioneLabel = new JLabel("Collocazione: ");
        JLabel categoriaLabel = new JLabel("Categoria: ");
        JLabel articoliLabel = new JLabel("Prodotti: ");
        JLabel puntoVenditaLabel = new JLabel("Punto Vendita: ");
        JButton aggiungiProdotti = new JButton("Aggiungi prodotto");
        aggiungiProdotti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProdottiSelezionati();
            }
        });
        JButton salva = new JButton("Salva prodotto");
        AmministratoreListener listener = new AmministratoreListener(nome, descrizione, produttoreCbx, collocazioneCbx, categoriaCbx, puntoVenditaCbx, selectedFile, imageSelectionFrame, articoliSelezionati);
        listener.setFrame(frame);
        salva.addActionListener(listener);
        salva.setActionCommand(AmministratoreListener.SALVA_PRODOTTO_COMPOSITO);

        scegliImmagine.addActionListener(listener);
        scegliImmagine.setActionCommand(AmministratoreListener.IMMAGINE);


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
        add(descrizioneLabel, gbc);

        gbc.gridy = 2;
        gbc.gridx = 2;
        add(descrizione, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        add(produttoreLabel, gbc);

        gbc.gridy = 3;
        gbc.gridx = 2;
        add(produttoreCbx, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        add(magazzinoLabel, gbc);

        gbc.gridy = 4;
        gbc.gridx = 2;
        add(magazzinoCbx, gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        add(collocazioneLabel, gbc);

        gbc.gridy = 5;
        gbc.gridx = 2;
        add(collocazioneCbx, gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        add(categoriaLabel, gbc);

        gbc.gridy = 6;
        gbc.gridx = 2;
        add(categoriaCbx, gbc);

        gbc.gridy = 7;
        gbc.gridx = 1;
        add(articoliLabel, gbc);

        gbc.gridy = 7;
        gbc.gridx = 2;
        add(articoli, gbc);

        gbc.gridy = 7;
        gbc.gridx = 3;
        add(aggiungiProdotti, gbc);

        gbc.gridy = 8;
        gbc.gridx = 1;
        add(puntoVenditaLabel, gbc);

        gbc.gridy = 8;
        gbc.gridx = 2;
        add(puntoVenditaCbx, gbc);

        gbc.gridy = 9;
        gbc.gridx = 2;
        add(scegliImmagine,gbc);

        gbc.gridy = 10;
        gbc.gridx = 2;
        add(salva, gbc);

    }

    private void addProduttoriToDropdown() {
        ProduttoreBusiness produttoreBusiness = new ProduttoreBusiness();

        java.util.List<Produttore> dropdownValues = produttoreBusiness.getProduttori();
        for (Produttore item : dropdownValues) {
            produttoreCbx.addItem(item);
        }
    }

    private void addMagazzinoToDropdown() {
        MagazzinoBusiness magazzinoBusiness = new MagazzinoBusiness();
        List<Magazzino> dropdownValues = magazzinoBusiness.getMagazzini();

        for (Magazzino item : dropdownValues) {
            magazzinoCbx.addItem(item);
        }
    }

    private void addCollocazioniToDropdown(int idMagazzino) {
        collocazioneCbx.removeAllItems();
        CollocazioneBusiness collocazioneBusiness = new CollocazioneBusiness();

        java.util.List<Collocazione> dropdownValues = collocazioneBusiness.getCollocazioni(idMagazzino);
        for (Collocazione item : dropdownValues) {
            collocazioneCbx.addItem((item));
        }
    }

    private void addCategorieToDropdown() {
        CategoriaBusiness categoriaProdottoBusiness = new CategoriaBusiness();

        java.util.List<CategoriaProdotto> dropdownValues = categoriaProdottoBusiness.getCategorieProdotto();
        for (CategoriaProdotto item : dropdownValues) {
            categoriaCbx.addItem(item);
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

    }

    private void addProdottiSelezionati() {
        int selectedIndex = articoli.getSelectedIndex();
        Prodotto selectedItem = listArticoli.getElementAt(selectedIndex);
        articoliSelezionati.add(selectedItem);

    }

    private void addPuntiVenditaToDropdown() {
        PuntoVenditaBusiness puntoVenditaBusiness = new PuntoVenditaBusiness();

        java.util.List<PuntoVendita> dropdownValues = puntoVenditaBusiness.getPuntiVendita();
        for (PuntoVendita item : dropdownValues) {
            puntoVenditaCbx.addItem(item);
        }
    }
}
