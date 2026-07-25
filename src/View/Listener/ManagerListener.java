package View.Listener;

import Business.Factory.Notifica;
import Business.Factory.NotificaEmail;
import Business.Factory.NotificaFactory;
import Business.OrdineBusiness;
import Business.ProdottoMagazzinoBusiness;
import Business.ServizioBusiness;
import Business.UtenteBusiness;
import Model.*;
import Model.Composite.IProdotto;
import Model.Composite.ProdottoComposito;
import View.FeedbackInputDialogPanel;
import View.FinestraIniziale;
import View.NotificaInputDialogPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagerListener implements ActionListener {

    public static final String GESTISCI_MAGAZZINO = "Gestici magazzino";
    public static final String GESTISCI_UTENTI = "Gestici utenti";
    public static final String CAMBIA_STATO = "Cambia stato utenti";
    public static final String VISUALIZZA_ORDINI = "Visualizza ordini richiesti";
    public static final String ORDINA = "Ordina";
    public static final String MANDA_NOTIFICA = "Manda notifica";
    private JTable table;
    private ArrayList<Ordine> ordini;
    private FinestraIniziale frame;
    private JTextField oggetto = new JTextField(24);
    private JTextField testo = new JTextField(24);

    public ManagerListener(FinestraIniziale frame) {
        this.frame = frame;
    }

    public ManagerListener(FinestraIniziale frame, JTable table) {
        this.frame = frame;
        this.table = table;
    }

    public ManagerListener(FinestraIniziale frame, JTable table, ArrayList<Ordine> ordini) {
        this.frame = frame;
        this.table = table;
        this.ordini = ordini;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if(GESTISCI_MAGAZZINO.equals(action)) {
            frame.mostraGestioneMagazzino();
        }
        else if (GESTISCI_UTENTI.equals(action)) {
            frame.mostraGestioneUtenti();
        }
        else if (CAMBIA_STATO.equals(action)) {
            int selectedRow = table.getSelectedRow();

            Object[] options = Utente.Stato.values();
            Object selectedValue = JOptionPane.showInputDialog(null, "Seleziona il nuovo stato dell'utente:",
                    "Cambia stato", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            UtenteBusiness utenteBusiness = new UtenteBusiness();
            int idCliente = (Integer) table.getValueAt(selectedRow, 0);
            Utente.Stato stato = (Utente.Stato) selectedValue;
            utenteBusiness.updateStato(idCliente, stato);

        }
        else if (VISUALIZZA_ORDINI.equals(action)) {
            frame.mostraOrdini();
        } else if (ORDINA.equals(action)) {
            int selected = table.getSelectedRow();
            int id = (Integer) table.getModel().getValueAt(selected, 0);
            int quantita = (Integer) table.getModel().getValueAt(selected, 2);
            for (Ordine ordine : ordini) {
                if (ordine.getProdotto().getId() == id) {
                    ProdottoMagazzinoBusiness prodottoMagazzinoBusiness = new ProdottoMagazzinoBusiness();
                    prodottoMagazzinoBusiness.updateQuantita(id, quantita, ordine.getProdotto() instanceof ProdottoComposito);

                    OrdineBusiness ordineBusiness = new OrdineBusiness();
                    ordineBusiness.removeById(ordine.getIdOrdine());
                }
            }
            JOptionPane.showMessageDialog(null,"Ordine eseguito con successo");
        }
        else if (MANDA_NOTIFICA.equals(action)) {

            int selectedRow = table.getSelectedRow();

            JOptionPane.showConfirmDialog(null, new NotificaInputDialogPanel(oggetto, testo), "Notifica",
                    JOptionPane.DEFAULT_OPTION);

            Messaggio messaggio = new Messaggio(oggetto.getText(), testo.getText());
            Cliente cliente = new Cliente();
            cliente.setEmail((String) table.getModel().getValueAt(selectedRow, 3));
            NotificaFactory notificaFactory = new NotificaFactory();
            Notifica notifica = notificaFactory.getCanaleNotifica(cliente.getNotificaPreferita());
            notifica.inviaNotifica(messaggio, cliente);

        }
    }
}
