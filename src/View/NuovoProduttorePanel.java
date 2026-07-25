package View;

import View.Listener.AmministratoreListener;

import javax.swing.*;
import java.awt.*;


public class NuovoProduttorePanel extends JPanel {

    public NuovoProduttorePanel(FinestraIniziale frame) {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JTextField nome = new JTextField(24);
        JTextField sitoWeb = new JTextField(24);
        JTextField citta = new JTextField(24);
        JTextField nazione = new JTextField(24);

        JLabel registrazione = new JLabel("Inserire i dati per aggiungere un produttore");
        JLabel nomeLabel = new JLabel("Nome: ");
        JLabel sitoWebLabel = new JLabel("Sito: ");
        JLabel cittaLabel = new JLabel("Città: ");
        JLabel nazioneLabel = new JLabel("Nazione: ");
        JButton salva = new JButton("Salva produttore");
        AmministratoreListener listener = new AmministratoreListener(nome, sitoWeb, citta, nazione);
        listener.setFrame(frame);
        salva.addActionListener(listener);
        salva.setActionCommand(AmministratoreListener.SALVA_PRODUTTORE);

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
        add(sitoWebLabel, gbc);

        gbc.gridy = 2;
        gbc.gridx = 2;
        add(sitoWeb, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        add(cittaLabel, gbc);

        gbc.gridy = 3;
        gbc.gridx = 2;
        add(citta, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        add(nazioneLabel, gbc);

        gbc.gridy = 4;
        gbc.gridx = 2;
        add(nazione, gbc);

        gbc.gridy = 5;
        gbc.gridx = 2;
        add(salva, gbc);
    }
}
