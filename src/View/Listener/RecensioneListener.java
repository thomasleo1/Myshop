package View.Listener;

import Business.RecensioneBusiness;
import Business.SessionManager;
import Model.Recensione;
import Model.Utente;
import View.FeedbackInputDialogPanel;
import View.FinestraIniziale;
import View.ProdottiInputDialogPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RecensioneListener implements ActionListener {
    public static final String AGGIUNGI_FEEDBACK = "Aggiungi feedback";
    public static final String MOSTRA_PRODOTTI_ACQUISTATI = "Mostra prodotti acquistati";
    public static final String VEDI_FEEDBACK_LASCIATI = "Vedi feedback lasciati";
    public static final String SET_VISUALIZZATO = "Segna feedback visualizzato";
    public static final String RISPONDI = "Rispondi ad un feedback";
    private JComboBox<Recensione.Feedback> feedbackCbx = new JComboBox<>();
    private JTextField commento = new JTextField(24);
    private JTable table;
    private int idCliente;
    private FinestraIniziale frame;



    public RecensioneListener(FinestraIniziale frame) {
        this.frame = frame;
    }

    public RecensioneListener(FinestraIniziale frame, JTable table, int idCliente) {
        this.frame = frame;
        this.table = table;
        this.idCliente = idCliente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        if (MOSTRA_PRODOTTI_ACQUISTATI.equals(action)) {
            frame.mostraRecensionePanel();

        }
        else if (AGGIUNGI_FEEDBACK.equals(action)) {

            int selectedRow = table.getSelectedRow();

            JOptionPane.showConfirmDialog(null, new FeedbackInputDialogPanel(feedbackCbx, commento), "Aggiungi",
                    JOptionPane.DEFAULT_OPTION);

            int id = (Integer) table.getValueAt(selectedRow, 0);
            String nome = (String) table.getValueAt(selectedRow, 1);

            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dataFormatted = currentDateTime.format(formatter);
            Date data = java.sql.Date.valueOf(dataFormatted);
            RecensioneBusiness recensioneBusiness = new RecensioneBusiness();
            recensioneBusiness.addRecensione(id, nome, data, idCliente, commento.getText(), (Recensione.Feedback) feedbackCbx.getSelectedItem());

        } else if (VEDI_FEEDBACK_LASCIATI.equals(action)) {
            frame.mostraRecensioniFatte();
        }
        else if (SET_VISUALIZZATO.equals(action)) {
            int selectedRow = table.getSelectedRow();
            int idRecensione = (Integer) table.getValueAt(selectedRow, 0);
            RecensioneBusiness recensioneBusiness = new RecensioneBusiness();
            recensioneBusiness.updateVisualizzato(idRecensione);

        }
        else if (RISPONDI.equals(action)) {
            int selectedRow = table.getSelectedRow();
            int idRecensione = (Integer) table.getValueAt(selectedRow, 0);

            String risposta = JOptionPane.showInputDialog(null, "Inserisci la risposta al commento:");

            RecensioneBusiness recensioneBusiness = new RecensioneBusiness();
            recensioneBusiness.updateRisposta(idRecensione, risposta);


        }
    }
}