package View;

import Business.UtenteBusiness;
import Model.Cliente;
import View.Listener.ManagerListener;
import View.Listener.TabellaListener;
import View.ViewModel.RigaUtente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GestisciUtentiPanel extends JPanel {

    private int idManager;

    public GestisciUtentiPanel(FinestraIniziale frame, int idManager) {

        this.idManager = idManager;

        setLayout(new BorderLayout());
        List<RigaUtente> righe = new ArrayList<>();
        inserisciUtenti(righe);

        GestisciUtentiTableModel model = new GestisciUtentiTableModel(righe);
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(125);
        table.getColumnModel().getColumn(2).setPreferredWidth(125);
        table.getColumnModel().getColumn(3).setPreferredWidth(160);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.setRowHeight(85);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel pulsantiTabella = new JPanel();
        pulsantiTabella.setLayout(new FlowLayout());
        JButton cambiaStato = new JButton("Cambia Stato Utente");
        ManagerListener listener = new ManagerListener(frame, table);
        cambiaStato.addActionListener(listener);
        cambiaStato.setActionCommand(ManagerListener.CAMBIA_STATO);
        pulsantiTabella.add(cambiaStato, BorderLayout.SOUTH);

        JButton mandaNotifica = new JButton("Manda notifica");
        ManagerListener listenerNotifica = new ManagerListener(frame, table);
        mandaNotifica.addActionListener(listenerNotifica);
        mandaNotifica.setActionCommand(ManagerListener.MANDA_NOTIFICA);
        pulsantiTabella.add(mandaNotifica, BorderLayout.SOUTH);

        add(pulsantiTabella, BorderLayout.SOUTH);


    }

    private void inserisciUtenti(List<RigaUtente> righe) {
        UtenteBusiness utenteBusiness = new UtenteBusiness();
        List<Cliente> clienti = utenteBusiness.getClientiByIdManager(idManager);

        for (int i = 0; i < clienti.size(); i++) {
            RigaUtente riga = new RigaUtente();
            riga.setId(clienti.get(i).getIdCliente());
            riga.setNome(clienti.get(i).getNome());
            riga.setCognome(clienti.get(i).getCognome());
            riga.setEmail(clienti.get(i).getEmail());
            riga.setStato(clienti.get(i).getStato());
            righe.add(riga);
        }
    }
}
