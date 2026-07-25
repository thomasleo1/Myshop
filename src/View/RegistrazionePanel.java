package View;

import Business.MagazzinoBusiness;
import Business.PuntoVenditaBusiness;
import Business.SessionManager;
import Model.Amministratore;
import Model.Magazzino;
import Model.PuntoVendita;
import Model.Utente;
import View.Listener.RegistrazioneListener;

import javax.swing.*;
import java.awt.*;

public class RegistrazionePanel extends JPanel {


    JComboBox<PuntoVendita> puntoVenditaCBx = new JComboBox<>();

    public RegistrazionePanel(FinestraIniziale frame) {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JTextField username = new JTextField(24);
        JPasswordField password = new JPasswordField(24);
        JTextField nome = new JTextField(24);
        JTextField cognome = new JTextField(24);
        JTextField eta = new JTextField(24);
        JTextField telefono = new JTextField(24);
        JTextField email = new JTextField(24);
        JTextField residenza = new JTextField(24);
        JTextField professione = new JTextField(24);
        JTextField salario = new JTextField(24);


        JLabel registrazione = new JLabel("Inserire i dati per completare la registrazione");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel nomeLabel = new JLabel("Nome: ");
        JLabel cognomeLabel = new JLabel("Cognome: ");
        JLabel etaLabel = new JLabel("Età: ");
        JLabel telefonoLabel = new JLabel("Telefono: ");
        JLabel emailLabel = new JLabel("Email: ");
        JLabel residenzaLabel = new JLabel("Residenza: ");
        JLabel professioneLabel = new JLabel("Professione: ");
        JLabel salarioLabel = new JLabel("Salario: ");
        JLabel puntoVenditaLabel = new JLabel("Punto vendita: ");

        addPuntiVenditaToDropdown();

        JButton button = new JButton("Completa registrazione");
        RegistrazioneListener listener = new RegistrazioneListener(nome, cognome, eta, telefono, email, residenza, professione, username, password, salario, puntoVenditaCBx);
        listener.setFrame(frame);
        button.addActionListener(listener);
        button.setActionCommand(RegistrazioneListener.COMPLETA);

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
        add(cognomeLabel, gbc);

        gbc.gridy = 2;
        gbc.gridx = 2;
        add(cognome, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        add(etaLabel, gbc);

        gbc.gridy = 3;
        gbc.gridx = 2;
        add(eta, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        add(telefonoLabel, gbc);

        gbc.gridy = 4;
        gbc.gridx = 2;
        add(telefono, gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        add(emailLabel, gbc);

        gbc.gridy = 5;
        gbc.gridx = 2;
        add(email, gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        add(residenzaLabel, gbc);

        gbc.gridy = 6;
        gbc.gridx = 2;
        add(residenza, gbc);

        gbc.gridy = 7;
        gbc.gridx = 1;
        add(professioneLabel, gbc);

        gbc.gridy = 7;
        gbc.gridx = 2;
        add(professione, gbc);

        gbc.gridy = 8;
        gbc.gridx = 1;
        add(usernameLabel, gbc);

        gbc.gridy = 8;
        gbc.gridx = 2;
        add(username, gbc);

        gbc.gridy = 9;
        gbc.gridx = 1;
        add(passwordLabel, gbc);

        gbc.gridy = 9;
        gbc.gridx = 2;
        add(password, gbc);

        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        if (utente instanceof Amministratore) {
            gbc.gridy = 10;
            gbc.gridx = 1;
            add(salarioLabel, gbc);

            gbc.gridy = 10;
            gbc.gridx = 2;
            add(salario, gbc);

        }

        gbc.gridy = 11;
        gbc.gridx = 1;
        add(puntoVenditaLabel, gbc);

        gbc.gridy = 11;
        gbc.gridx = 2;
        add(puntoVenditaCBx, gbc);


        gbc.gridy = 12;
        gbc.gridx = 2;
        add(button, gbc);

    }

    private void addPuntiVenditaToDropdown() {
        PuntoVenditaBusiness puntoVenditaBusiness = new PuntoVenditaBusiness();
        java.util.List<PuntoVendita> dropdownValues = null;
        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        if (utente instanceof Amministratore) {
            dropdownValues = puntoVenditaBusiness.getPuntiVenditaSenzaManager();
        } else {
            dropdownValues = puntoVenditaBusiness.getPuntiVendita();
        }

        for (PuntoVendita item : dropdownValues) {
            puntoVenditaCBx.addItem((item));
        }

    }

}
