package View;

import Business.CategoriaBusiness;
import Model.CategoriaProdotto;
import View.Listener.AmministratoreListener;

import javax.swing.*;
import java.awt.*;

public class NuovaCategoriaPanel extends JPanel {

    private JComboBox<CategoriaProdotto> categoriaCbx = new JComboBox<>();

    public NuovaCategoriaPanel(FinestraIniziale frame) {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JTextField nomeCatProdotto = new JTextField(24);
        JTextField nomeCatServizio = new JTextField(24);
        JTextField nomeSottocategoria = new JTextField(24);

        JLabel registrazione = new JLabel("Inserire il nome della categoria da aggiugnere");
        JLabel catProdotto = new JLabel("Categoria per prodotto: ");
        JLabel catServizio = new JLabel("Categoria per servizio: ");
        JLabel sottocategoriaLabel = new JLabel("Sottocategoria: ");

        addCategorieToDropdown();


        JButton salva = new JButton("Salva categoria");
        AmministratoreListener listener = new AmministratoreListener(nomeCatProdotto, nomeCatServizio, nomeSottocategoria, categoriaCbx);
        listener.setFrame(frame);
        salva.addActionListener(listener);
        salva.setActionCommand(AmministratoreListener.SALVA_CATEGORIA);


        gbc.gridy = 0;
        gbc.gridx = 2;
        add(registrazione, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        add(catProdotto, gbc);

        gbc.gridy = 1;
        gbc.gridx = 2;
        add(nomeCatProdotto, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        add(catServizio, gbc);

        gbc.gridy = 2;
        gbc.gridx = 2;
        add(nomeCatServizio, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        add(sottocategoriaLabel, gbc);

        gbc.gridy = 3;
        gbc.gridx = 2;
        add(nomeSottocategoria, gbc);

        gbc.gridy = 3;
        gbc.gridx = 3;
        add(categoriaCbx, gbc);

        gbc.gridy = 4;
        gbc.gridx = 2;
        add(salva, gbc);
    }

    private void addCategorieToDropdown() {
        CategoriaBusiness categoriaProdottoBusiness = new CategoriaBusiness();

        java.util.List<CategoriaProdotto> dropdownValues = categoriaProdottoBusiness.getCategorieProdotto();
        for (CategoriaProdotto item : dropdownValues) {
            categoriaCbx.addItem(item);
        }
    }
}
