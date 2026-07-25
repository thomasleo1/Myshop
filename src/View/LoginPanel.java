package View;

import View.Listener.CatalogoListener;
import View.Listener.LoginListener;
import View.Listener.RegistrazioneListener;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private boolean catalogoVisibile = true;
    private JButton catalogo;
    public LoginPanel(FinestraIniziale frame) {

        setLayout(new FlowLayout());
        JPanel login = new JPanel();
        login.setLayout(new GridBagLayout());

        JLabel accedi = new JLabel("Effettua l'accesso!");
        accedi.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel usernameText = new JLabel("Username:");
        JLabel passwordtext = new JLabel("Password:");

        JTextField username = new JTextField(24);
        JPasswordField password = new JPasswordField(24);
        JButton loginButton = new JButton("Login");

        LoginListener loginListener = new LoginListener(username, password);
        loginListener.setFrame(frame);
        loginButton.addActionListener(loginListener);


        JLabel label = new JLabel("Non sei cliente? Registrati subito!");
        JButton registrazione = new JButton("Registrati");

        RegistrazioneListener registrazioneListener = new RegistrazioneListener();
        registrazioneListener.setFrame(frame);
        registrazione.addActionListener(registrazioneListener);
        registrazione.setActionCommand(RegistrazioneListener.REGISTRATI);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 2;
        gbc.gridy = 0;
        login.add(accedi, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        login.add(usernameText,gbc);

        gbc.gridy = 2;
        login.add(passwordtext, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        login.add(username, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        login.add(password, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        login.add(loginButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        login.add(label, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        login.add(registrazione, gbc);

        catalogo = new JButton("Sfoglia Catalogo");
        CatalogoListener catalogoListener = new CatalogoListener();
        catalogoListener.setFinestra(frame);
        catalogo.addActionListener(catalogoListener);
        catalogo.setVisible(catalogoVisibile);
        gbc.gridy = 6;
        login.add(catalogo, gbc);

        add(login);


    }

    public void setCatalogoVisibile(boolean visibile) {
        this.catalogoVisibile = visibile;
        if (catalogo != null) {
            catalogo.setVisible(visibile);
        }
    }
}
