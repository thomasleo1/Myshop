package View.Listener;

import Business.*;
import Business.Bridge.DocumentoListaAcquisto;
import Business.Bridge.PdfBoxAPI;
import Model.Composite.IProdotto;
import Model.Composite.ProdottoComposito;
import Model.ListaAcquisto;
import Model.ProdottoMagazzino;
import Model.Servizio;
import Model.Utente;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteListener implements ActionListener {


    private FinestraIniziale frame;
    private JTable tabellaProdotti;
    private JTable tabellaServizi;
    private JTable tabellaLista;
    private CatalogoTableModel model;
    private ServizioTableModel modelServizio;
    private ListaAcquistoTableModel modelLista;
    private JComboBox<ListaAcquisto> listaAcquistoCbx = new JComboBox<>();
    private JTextField quantita = new JTextField(5);
    public static final String MIE_LISTE = "Le mie liste";
    public static final String CREA_LISTA = "Crea lista";
    public static final String AGGIUNGI_PRODOTTO_A_LISTA = "Aggiungi prodotto a lista";
    public static final String AGGIUNGI_SERVIZIO_A_LISTA = "Aggiungi servizio a lista";
    public static final String VISUALIZZA_PRODOTTI_NON_DISPONIBILI = "Visualizza prodotti non disponibili";
    public static final String ORDINA_PRODOTTO = "Ordina prodotto" ;
    public static final String PAGA_LISTA ="Paga lista" ;
    private CatalogoPanel panel;

    public ClienteListener(FinestraIniziale frame, JTable table, ListaAcquistoTableModel listaAcquistoTableModel) {
        this.frame = frame;
        this.tabellaLista = table;
        this.modelLista = listaAcquistoTableModel;
    }

    public ClienteListener(JTable tabellaProdotti, CatalogoTableModel model, JTable tabellaServizi, ServizioTableModel modelServizio) {
        this.tabellaProdotti = tabellaProdotti;
        this.tabellaServizi = tabellaServizi;
        this.model = model;
        this.modelServizio = modelServizio;
    }

    public ClienteListener(CatalogoPanel panel, JTable tabellaProdotti, CatalogoTableModel model) {
        this.panel = panel;
        this.tabellaProdotti = tabellaProdotti;
        this.model = model;
    }

    public ClienteListener(FinestraIniziale frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        LocalDateTime currentDateTime = LocalDateTime.now();
        Date data = java.sql.Timestamp.valueOf(currentDateTime);

        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        if (MIE_LISTE.equals(action)) {
            frame.mostraListe(utente.getIdUtente());

        } else if (CREA_LISTA.equals(action)) {
            ListaAcquistoBusiness listaAcquistoBusiness = new ListaAcquistoBusiness();
            String nomeLista = JOptionPane.showInputDialog(null, "Inserisci il nome della nuova lista:");
            if (nomeLista != null && !nomeLista.isEmpty()) {
                listaAcquistoBusiness.addLista(new ListaAcquisto(nomeLista, data), utente.getIdUtente());

            } else {
                JOptionPane.showMessageDialog(null, "Inserimento della nuova lista annullato");
            }

        } else if (AGGIUNGI_PRODOTTO_A_LISTA.equals(action)) {
            int selectedRow = tabellaProdotti.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Seleziona una riga da aggiungere ad una lista.", "Errore", JOptionPane.ERROR_MESSAGE);
            }


            ListaAcquistoBusiness listaAcquistoBusiness = new ListaAcquistoBusiness();
            ArrayList<ListaAcquisto> liste = listaAcquistoBusiness.getListeByIdCliente(utente.getIdUtente());

            if (liste.isEmpty()) {
                int option = JOptionPane.showConfirmDialog(null, "Nessuna lista disponibile. Vuoi crearne una nuova?", "Creare nuova lista?", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    String nomeLista = JOptionPane.showInputDialog(null, "Inserisci il nome della nuova lista:");
                    if (nomeLista != null && !nomeLista.isEmpty()) {
                        listaAcquistoBusiness.addLista(new ListaAcquisto(nomeLista, data), utente.getIdUtente());
                    } else {
                        JOptionPane.showMessageDialog(null, "Inserimento della nuova lista annullato");
                    }
                }
            } else {
                int idProdotto = (Integer) model.getValueAt(selectedRow, 0);
                String nomeProdotto = (String) model.getValueAt(selectedRow, 1);
                JOptionPane.showConfirmDialog(null, new ProdottiInputDialogPanel(utente.getIdUtente(), idProdotto, nomeProdotto, listaAcquistoCbx, quantita), "Aggiungi",
                         JOptionPane.DEFAULT_OPTION);
                listaAcquistoBusiness.addProdottoToList((ListaAcquisto) listaAcquistoCbx.getSelectedItem(), Integer.parseInt(quantita.getText()), idProdotto);
            }

        } else if (AGGIUNGI_SERVIZIO_A_LISTA.equals(action)) {
            int selectedRow = tabellaLista.getSelectedRow();

            ServizioBusiness servizioBusiness = new ServizioBusiness();
            Object[] options = (servizioBusiness.getServizi().toArray(new Servizio[0]));
            Object selectedValue = JOptionPane.showInputDialog(null, "Seleziona il servizio che desideri aggiugere:",
                    "Aggiungi servizio", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            Servizio servizio = (Servizio) selectedValue;
            int idListaAcquisto = (Integer) modelLista.getValueAt(selectedRow,0);

            ListaAcquistoBusiness listaAcquistoBusiness = new ListaAcquistoBusiness();
            listaAcquistoBusiness.addServizioToList(servizio.getId(), idListaAcquisto);


        } else if (VISUALIZZA_PRODOTTI_NON_DISPONIBILI.equals(action)) {
            panel.inserisciProdottiNonDisponibili();
        }
        else if(ORDINA_PRODOTTO.equals(action)) {
            int selectedRow = tabellaProdotti.getSelectedRow();
            int id = (Integer) model.getValueAt(selectedRow, 0);
            int quantita = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleziona la quantità da ordinare: "));
            OrdineBusiness ordineBusiness = new OrdineBusiness();
            List<IProdotto> prodottiNonDisponibili = panel.getProdottiNonDisponibili();
            for (IProdotto prodotto : prodottiNonDisponibili) {
                if (prodotto.getId() == id) {
                    ordineBusiness.addOrdine(prodotto, quantita);
                }
            }
        }
        else if (PAGA_LISTA.equals(action)) {

            int selectedRow = tabellaLista.getSelectedRow();
            int idListaAcquisto = (Integer) modelLista.getValueAt(selectedRow,0);

            ListaAcquistoBusiness listaAcquistoBusiness = new ListaAcquistoBusiness();

            ListaAcquisto listaAcquisto = listaAcquistoBusiness.getListaById(idListaAcquisto);

            ProdottoMagazzinoBusiness prodottoMagazzinoBusiness = new ProdottoMagazzinoBusiness();
            List<ProdottoMagazzino> prodottiMagazzino = prodottoMagazzinoBusiness.getProdottiMagazzinoByCliente(utente.getIdUtente());
            List<ProdottoMagazzino> prodottiMagazzinoLista = listaAcquisto.getArticoli();

            for (ProdottoMagazzino prodottoMagazzino: prodottiMagazzino) {
                int quantita = prodottoMagazzino.getQuantita();
                for (ProdottoMagazzino prodottoMagazzinoLista : prodottiMagazzinoLista) {
                    if (prodottoMagazzino.getProdotto().getId() == prodottoMagazzinoLista.getProdotto().getId()) {
                        quantita = quantita - prodottoMagazzinoLista.getQuantita();
                    }
                }
                prodottoMagazzinoBusiness.updateQuantita(prodottoMagazzino.getProdotto().getId(), quantita, prodottoMagazzino.getProdotto() instanceof ProdottoComposito);
            }

            listaAcquisto.setStatoLista(ListaAcquisto.StatoLista.PAGATA);
            listaAcquistoBusiness.update(listaAcquisto);
            DocumentoListaAcquisto documentoListaAcquisto = new DocumentoListaAcquisto(listaAcquisto, new PdfBoxAPI());
            documentoListaAcquisto.invia(utente.getEmail());
            JOptionPane.showMessageDialog(null, "Lista pagata correttamente.\nLe arriverà la fattura per email.\nLa ringraziamo per l'acquisto!");
        }
    }
}
