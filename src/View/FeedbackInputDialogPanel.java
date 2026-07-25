package View;

import Model.ProdottoMagazzino;
import Model.Recensione;

import javax.swing.*;
import java.awt.*;

public class FeedbackInputDialogPanel extends JPanel {

    private JComboBox<Recensione.Feedback> feedbackCbx;
    private JTextField commento;
    public FeedbackInputDialogPanel(JComboBox<Recensione.Feedback> feedbackCbx,  JTextField commento) {

        this.feedbackCbx = feedbackCbx;
        this.commento = commento;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JLabel feedbackLabel = new JLabel("Feedback: ");
        JLabel commentoLabel = new JLabel("Commento: ");

        addFeedbackToDropdown();

        gbc.gridy = 0;
        gbc.gridx = 1;
        add(feedbackLabel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 2;
        add(feedbackCbx, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        add(commentoLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 2;
        add(commento, gbc);
    }

    private void addFeedbackToDropdown() {
        this.feedbackCbx.removeAllItems();
        this.feedbackCbx.addItem(Recensione.Feedback.OTTIMO);
        this.feedbackCbx.addItem(Recensione.Feedback.BUONO);
        this.feedbackCbx.addItem(Recensione.Feedback.DISCRETO);
        this.feedbackCbx.addItem(Recensione.Feedback.MEDIOCRE);
        this.feedbackCbx.addItem(Recensione.Feedback.SCARSO);
    }


}
