package View;

import Business.OrdineBusiness;
import Business.SessionManager;
import Model.Composite.IProdotto;
import Model.Ordine;
import Model.Utente;
import View.Listener.ManagerListener;
import View.ViewModel.RigaOrdine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class OrdinePanel extends JPanel {

    private ArrayList<Ordine> ordiniDaEseguire = new ArrayList<>();
    private Utente utente;
    public OrdinePanel(FinestraIniziale frame) {

        this.utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        this.setLayout(new BorderLayout());
        List<RigaOrdine> righe = new ArrayList<>();
        inserisciOrdini(righe);

        OrdineTableModel model = new OrdineTableModel(righe);
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.setRowHeight(85);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton ordina = new JButton("Ordina");
        ManagerListener listener = new ManagerListener(frame, table, ordiniDaEseguire);
        ordina.addActionListener(listener);
        ordina.setActionCommand(ManagerListener.ORDINA);
        add(ordina, BorderLayout.SOUTH);
    }

    private void inserisciOrdini(List<RigaOrdine> righe) {
        OrdineBusiness ordineBusiness = new OrdineBusiness();
        ArrayList<Ordine> ordini = ordineBusiness.getOrdiniByManagerId(utente.getIdUtente());
        for (Ordine ordine : ordini) {
            RigaOrdine rigaOrdine = new RigaOrdine();
            rigaOrdine.setIdProdotto(ordine.getProdotto().getId());
            rigaOrdine.setNomeProdotto(ordine.getProdotto().getNome());
            rigaOrdine.setQuantita(ordine.getQuantita());
            righe.add(rigaOrdine);
            ordiniDaEseguire.add(ordine);
        }
    }
}
