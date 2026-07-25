package View;

import Business.*;
import Model.*;
import View.Listener.AmministratoreListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;

public class NuovoProdottoPanel extends JPanel {

    private JComboBox<Produttore> produttoreCbx = new JComboBox<>();
    private JComboBox<Magazzino> magazzinoCbx = new JComboBox<>();
    private JComboBox<Collocazione> collocazioneCbx = new JComboBox<>();
    private JComboBox<CategoriaProdotto> categoriaCbx = new JComboBox<>();
    private JComboBox<SottoCategoria> sottocategoriaCbx = new JComboBox<>();
    private JComboBox<PuntoVendita> puntoVenditaCbx = new JComboBox<>();
    private JFrame imageSelectionFrame = new JFrame();
    private JButton chooseButton = new JButton("Scegli immagine");
    private File selectedFile;
    public NuovoProdottoPanel(FinestraIniziale frame) {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JTextField nome = new JTextField(24);
        JTextField descrizione = new JTextField(24);
        JTextField prezzo = new JTextField(24);

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
                }
            }
        });


        addCategorieToDropdown();
        sottocategoriaCbx.setEnabled(false);
        categoriaCbx.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    CategoriaProdotto selectedItem = (CategoriaProdotto) categoriaCbx.getSelectedItem();
                    addSottoCategorieToDropdown(selectedItem.getId());
                    sottocategoriaCbx.setEnabled(true);
                }
            }
        });

        addPuntiVenditaToDropdown();

        imageSelectionFrame.setSize(400,400);
        imageSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel registrazione = new JLabel("Inserire i dati per aggiungere un prodotto");
        JLabel nomeLabel = new JLabel("Nome: ");
        JLabel descrizioneLabel = new JLabel("Descrizione: ");
        JLabel prezzoLabel = new JLabel("Prezzo: ");
        JLabel produttoreLabel = new JLabel("Produttore: ");
        JLabel magazzinoLabel = new JLabel("Magazzino: ");
        JLabel collocazioneLabel = new JLabel("Collocazione: ");
        JLabel categoriaLabel = new JLabel("Categoria: ");
        JLabel sottocategoriaLabel = new JLabel("Sottocategoria: ");
        JLabel puntoVenditaLabel = new JLabel("Punto Vendita: ");
        JButton salva = new JButton("Salva prodotto");
        AmministratoreListener listener = new AmministratoreListener(nome, descrizione, prezzo, produttoreCbx, magazzinoCbx, collocazioneCbx, categoriaCbx, sottocategoriaCbx, puntoVenditaCbx, selectedFile, imageSelectionFrame);
        listener.setFrame(frame);
        salva.addActionListener(listener);
        salva.setActionCommand(AmministratoreListener.SALVA_PRODOTTO);

        chooseButton.addActionListener(listener);
        chooseButton.setActionCommand(AmministratoreListener.IMMAGINE);


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
        add(prezzoLabel, gbc);

        gbc.gridy = 3;
        gbc.gridx = 2;
        add(prezzo, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        add(produttoreLabel, gbc);

        gbc.gridy = 4;
        gbc.gridx = 2;
        add(produttoreCbx, gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        add(magazzinoLabel, gbc);

        gbc.gridy = 5;
        gbc.gridx = 2;
        add(magazzinoCbx, gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        add(collocazioneLabel, gbc);

        gbc.gridy = 6;
        gbc.gridx = 2;
        add(collocazioneCbx, gbc);

        gbc.gridy = 7;
        gbc.gridx = 1;
        add(categoriaLabel, gbc);

        gbc.gridy = 7;
        gbc.gridx = 2;
        add(categoriaCbx, gbc);

        gbc.gridy = 8;
        gbc.gridx = 1;
        add(sottocategoriaLabel, gbc);

        gbc.gridy = 8;
        gbc.gridx = 2;
        add(sottocategoriaCbx, gbc);

        gbc.gridy = 9;
        gbc.gridx = 1;
        add(puntoVenditaLabel, gbc);

        gbc.gridy = 9;
        gbc.gridx = 2;
        add(puntoVenditaCbx, gbc);

        gbc.gridy = 10;
        gbc.gridx = 2;
        add(chooseButton,gbc);

        gbc.gridy = 11;
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
            collocazioneCbx.addItem(item);
        }
    }

    private void addCategorieToDropdown() {
        CategoriaBusiness categoriaProdottoBusiness = new CategoriaBusiness();

        java.util.List<CategoriaProdotto> dropdownValues = categoriaProdottoBusiness.getCategorieProdotto();
        for (CategoriaProdotto item : dropdownValues) {
            categoriaCbx.addItem(item);
        }
    }

    private void addSottoCategorieToDropdown(int idCategoria) {
        sottocategoriaCbx.removeAllItems();
        CategoriaBusiness categoriaProdottoBusiness = new CategoriaBusiness();

        java.util.List<SottoCategoria> dropdownValues = categoriaProdottoBusiness.getSottocategorie(idCategoria);
        for (SottoCategoria item : dropdownValues) {
            sottocategoriaCbx.addItem(item);
        }
    }

    private void addPuntiVenditaToDropdown() {
        PuntoVenditaBusiness puntoVenditaBusiness = new PuntoVenditaBusiness();

        java.util.List<PuntoVendita> dropdownValues = puntoVenditaBusiness.getPuntiVendita();
        for (PuntoVendita item : dropdownValues) {
            puntoVenditaCbx.addItem(item);
        }
    }
}
