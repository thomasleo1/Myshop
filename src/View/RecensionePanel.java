package View;

import Business.ListaAcquistoBusiness;
import Model.ListaAcquisto;
import View.Listener.RecensioneListener;
import View.ViewModel.RigaRecensione;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RecensionePanel extends JPanel {

    private int idCliente;
    public RecensionePanel(FinestraIniziale frame, int idCliente) {

        this.idCliente = idCliente;

        setLayout(new BorderLayout());
        List<RigaRecensione> righe = new ArrayList<>();
        inserisciProdottiToComment(righe);

        RecensioneTableModel tableModel = new RecensioneTableModel(righe);
        JTable tabella = new JTable(tableModel);
        tabella.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabella.getColumnModel().getColumn(1).setPreferredWidth(250);
        tabella.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabella.setRowHeight(85);

        JScrollPane scrollPane = new JScrollPane(tabella);
        add(scrollPane, BorderLayout.CENTER);

        JPanel pulsantiAzioneRecensione = new JPanel();
        JButton feedback = new JButton("Aggiungi feedback");
        RecensioneListener recensioneListener = new RecensioneListener(frame, tabella, idCliente);
        feedback.addActionListener(recensioneListener);
        feedback.setActionCommand(RecensioneListener.AGGIUNGI_FEEDBACK);
        pulsantiAzioneRecensione.add(feedback);
        add(pulsantiAzioneRecensione, BorderLayout.SOUTH);

    }

    private void inserisciProdottiToComment(List<RigaRecensione> righe) {
        ListaAcquistoBusiness listaAcquistoBusiness = new ListaAcquistoBusiness();
        ListaAcquisto listaProdottiAcquistati  = listaAcquistoBusiness.getProdottiAcquistatiByIdUtente(idCliente);

        for (int i = 0; i < listaProdottiAcquistati.getArticoli().size(); i++) {
            RigaRecensione riga = new RigaRecensione();
            riga.setId(listaProdottiAcquistati.getArticoli().get(i).getProdotto().getId());
            riga.setNome(listaProdottiAcquistati.getArticoli().get(i).getProdotto().getNome());
            righe.add(riga);
        }

        for (int i = 0; i < listaProdottiAcquistati.getServizi().size(); i++) {
            RigaRecensione riga = new RigaRecensione();
            riga.setId(listaProdottiAcquistati.getServizi().get(i).getId());
            riga.setNome(listaProdottiAcquistati.getServizi().get(i).getNome());
            righe.add(riga);
        }
    }
}
