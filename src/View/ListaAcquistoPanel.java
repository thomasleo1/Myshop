package View;

import Business.ListaAcquistoBusiness;
import Model.*;
import View.Listener.ClienteListener;
import View.Listener.MouseListener;
import View.ViewModel.RigaLista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListaAcquistoPanel extends JPanel {

    private int idCliente;
    public ListaAcquistoPanel(FinestraIniziale frame, int idCliente) {

        this.idCliente = idCliente;

        setLayout(new BorderLayout());
        List<RigaLista> righe = new ArrayList<>();
        inserisciListe(righe);

        ListaAcquistoTableModel tableModel = new ListaAcquistoTableModel(righe);
        JTable tabella = new JTable(tableModel);
        tabella.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabella.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabella.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabella.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabella.getColumnModel().getColumn(4).setPreferredWidth(400);
        tabella.setRowHeight(85);

        MouseListener mouseListener = new MouseListener(tabella,frame, true);
        tabella.addMouseListener(mouseListener);

        JScrollPane scrollPane = new JScrollPane(tabella);
        add(scrollPane, BorderLayout.CENTER);

        JPanel pulsantiAzioneTabella = new JPanel();
        pulsantiAzioneTabella.setLayout(new FlowLayout());
        JButton creaLista = new JButton("Crea una nuova lista");
        ClienteListener creaListaListener = new ClienteListener(frame,tabella ,tableModel);
        creaLista.addActionListener(creaListaListener);
        creaLista.setActionCommand(ClienteListener.CREA_LISTA);
        pulsantiAzioneTabella.add(creaLista);

        JButton servizio = new JButton("Aggiungi servizio ad una lista");
        ClienteListener servizioListener = new ClienteListener(frame,tabella, tableModel);
        servizio.addActionListener(servizioListener);
        servizio.setActionCommand(ClienteListener.AGGIUNGI_SERVIZIO_A_LISTA);
        pulsantiAzioneTabella.add(servizio);

        JButton paga = new JButton("Paga");
        ClienteListener pagaListener = new ClienteListener(frame,tabella, tableModel);
        paga.addActionListener(pagaListener);
        paga.setActionCommand(ClienteListener.PAGA_LISTA);
        pulsantiAzioneTabella.add(paga);
        add(pulsantiAzioneTabella, BorderLayout.SOUTH);



    }

    private void inserisciListe(List<RigaLista> righe) {
        ListaAcquistoBusiness listaAcquistoBusiness = new ListaAcquistoBusiness();
        List<ListaAcquisto> liste = listaAcquistoBusiness.getListeByIdCliente(idCliente);

        for (int i = 0; i < liste.size(); i++) {
            RigaLista riga = new RigaLista();
            riga.setIdLista(liste.get(i).getIdListaAcquisto());
            riga.setNomeLista(liste.get(i).getNome());
            riga.setDataCreazione(liste.get(i).getData());
            riga.setStatoLista(liste.get(i).getStatoLista());

            if (liste.get(i).getArticoli().size() > 0) {
                String prodotti = "";
                for (int j = 0; j < liste.get(i).getArticoli().size(); j++) {
                    prodotti += liste.get(i).getArticoli().get(j).getProdotto().getNome() + "(" + liste.get(i).getArticoli().get(j).getQuantita() + ");";
                }
                riga.setProdotti(prodotti);
            }

            if (liste.get(i).getServizi().size() > 0) {
                String servizi = "";
                for (int j = 0; j < liste.get(i).getServizi().size(); j++) {
                    servizi += liste.get(i).getServizi().get(j).getNome() + ";";
                }
                riga.setServizi(servizi);
            }
            riga.setPrezzo(liste.get(i).getPrezzo());
            righe.add(riga);

        }
    }
}
