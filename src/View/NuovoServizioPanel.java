package View;

import Business.CategoriaBusiness;
import Business.ProduttoreBusiness;
import Model.CategoriaServizio;
import Model.Produttore;
import View.Listener.AmministratoreListener;

import javax.swing.*;
import java.awt.*;


public class NuovoServizioPanel extends JPanel{

    private JComboBox<Produttore> produttoreCbx = new JComboBox<>();
    private JComboBox<CategoriaServizio> categoriaCbx = new JComboBox<>();
    public NuovoServizioPanel(FinestraIniziale frame) {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JTextField nome = new JTextField(24);
        JTextField descrizione = new JTextField(24);
        JTextField prezzo = new JTextField(24);

        addProduttoriToDropdown();
        addCategorieToDropdown();

        JLabel registrazione = new JLabel("Inserire i dati per aggiungere un servizio");
        JLabel nomeLabel = new JLabel("Nome: ");
        JLabel descrizioneLabel = new JLabel("Descrizione: ");
        JLabel prezzoLabel = new JLabel("Prezzo: ");
        JLabel produttoreLabel = new JLabel("Produttore: ");
        JLabel categoriaLabel = new JLabel("Categoria: ");
        JButton salva = new JButton("Salva servizio");
        AmministratoreListener listener = new AmministratoreListener(nome, descrizione, prezzo, produttoreCbx, categoriaCbx);
        listener.setFrame(frame);
        salva.addActionListener(listener);
        salva.setActionCommand(AmministratoreListener.SALVA_SERVIZIO);


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
        add(categoriaLabel, gbc);

        gbc.gridy = 5;
        gbc.gridx = 2;
        add(categoriaCbx, gbc);

        gbc.gridy = 6;
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

    private void addCategorieToDropdown() {
        CategoriaBusiness categoriaProdottoBusiness = new CategoriaBusiness();

        java.util.List<CategoriaServizio> dropdownValues = categoriaProdottoBusiness.getCategorieServizio();
        for (CategoriaServizio item : dropdownValues) {
            categoriaCbx.addItem(item);
        }
    }
}
