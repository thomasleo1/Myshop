package View;

import javax.swing.*;
import java.awt.*;

public class NotificaInputDialogPanel extends JPanel{

    private JTextField oggetto;
    private JTextField testo;
    public NotificaInputDialogPanel(JTextField oggetto, JTextField testo) {

        this.oggetto = oggetto;
        this.testo = testo;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JLabel oggettoLabel = new JLabel("Oggetto: ");
        JLabel testoLabel = new JLabel("Testo: ");

        gbc.gridy = 0;
        gbc.gridx = 1;
        add(oggettoLabel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 2;
        add(oggetto, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        add(testoLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 2;
        add(testo, gbc);

    }
}
