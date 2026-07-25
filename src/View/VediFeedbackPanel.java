package View;

import Business.*;
import DAO.IProdottoCompositoDAO;
import Model.*;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import View.Listener.RecensioneListener;
import View.ViewModel.RigaFeedback;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VediFeedbackPanel extends JPanel {

    private Utente utente;
    public VediFeedbackPanel(FinestraIniziale frame, Utente utente) {
        this.utente = utente;

        setLayout(new BorderLayout());
        List<RigaFeedback> righe = new ArrayList<>();
        inserisciFeedbackDati(righe);

        VediFeedbackTableModel model = new VediFeedbackTableModel(righe, utente);
        JTable table = new JTable(model);
        if (utente instanceof Cliente) {
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            table.getColumnModel().getColumn(1).setPreferredWidth(75);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(200);
        } else {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
            table.getColumnModel().getColumn(2).setPreferredWidth(150);
            table.getColumnModel().getColumn(3).setPreferredWidth(75);
            table.getColumnModel().getColumn(4).setPreferredWidth(200);
            table.getColumnModel().getColumn(5).setPreferredWidth(200);
            table.getColumnModel().getColumn(6).setPreferredWidth(50);
        }
        table.setRowHeight(85);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        if (utente instanceof Manager) {
            JPanel pulsanti = new JPanel();
            pulsanti.setLayout(new FlowLayout());
            JButton visualizzato = new JButton("Segna come visualizzato");
            RecensioneListener listenerVisualizzato = new RecensioneListener(frame, table, utente.getIdUtente());
            visualizzato.addActionListener(listenerVisualizzato);
            visualizzato.setActionCommand(RecensioneListener.SET_VISUALIZZATO);
            pulsanti.add(visualizzato);

            JButton rispondi = new JButton("Rispondi");
            RecensioneListener listenerRisposta = new RecensioneListener(frame, table, utente.getIdUtente());
            rispondi.addActionListener(listenerRisposta);
            rispondi.setActionCommand(RecensioneListener.RISPONDI);
            pulsanti.add(rispondi);

            add(pulsanti, BorderLayout.SOUTH);

        }


    }

    private void inserisciFeedbackDati(List<RigaFeedback> righe) {
        if (utente instanceof Cliente) {
            inserisciFeedbackCliente(righe);
        } else {
            inserisciFeedbackManager(righe);
        }

    }

    private void inserisciFeedbackCliente(List<RigaFeedback> righe) {
        RecensioneBusiness recensioneBusiness = new RecensioneBusiness();
        ProdottoBusiness prodottoBusiness = new ProdottoBusiness();
        ProdottoCompositoBusiness prodottoCompositoBusiness = new ProdottoCompositoBusiness();
        ServizioBusiness servizioBusiness = new ServizioBusiness();
        List<Recensione> recensioni = recensioneBusiness.getRecensioniByIdcliente(utente.getIdUtente());


        for (Recensione recensione : recensioni) {
            RigaFeedback rigaFeedback = new RigaFeedback();
            List<Prodotto> prodotti = prodottoBusiness.getProdottiRecensitiByIdCliente(utente.getIdUtente());

            for (Prodotto prodotto : prodotti) {
                if (prodotto.getRecensione().getIdRecensione() == recensione.getIdRecensione()) {
                    rigaFeedback.setNomeProdotto(prodotto.getNome());
                    rigaFeedback.setCommento(recensione.getTesto());
                    rigaFeedback.setFeedback(recensione.getFeedback());
                    rigaFeedback.setRisposta(recensione.getRisposta());

                    righe.add(rigaFeedback);
                }
            }
            List<ProdottoComposito> prodottiCompositi = prodottoCompositoBusiness.getProdottiCompositiRecensitiByIdCliente(utente.getIdUtente());
            for (ProdottoComposito prodottoComposito : prodottiCompositi) {
                if (prodottoComposito.getRecensione().getIdRecensione() == recensione.getIdRecensione()) {
                    rigaFeedback.setNomeProdotto(prodottoComposito.getNome());
                    rigaFeedback.setCommento(recensione.getTesto());
                    rigaFeedback.setFeedback(recensione.getFeedback());
                    rigaFeedback.setRisposta(recensione.getRisposta());
                    righe.add(rigaFeedback);
                }
            }

            List<Servizio> servizi = servizioBusiness.getServiziRecensitiByIdCliente(utente.getIdUtente());
            for (Servizio servizio : servizi) {
                if (servizio.getRecensione().getIdRecensione() == recensione.getIdRecensione()) {
                    rigaFeedback.setNomeProdotto(servizio.getNome());
                    rigaFeedback.setCommento(recensione.getTesto());
                    rigaFeedback.setFeedback(recensione.getFeedback());
                    rigaFeedback.setRisposta(recensione.getRisposta());
                    righe.add(rigaFeedback);
                }
            }
        }
    }

    private void inserisciFeedbackManager(List<RigaFeedback> righe) {
        RecensioneBusiness recensioneBusiness = new RecensioneBusiness();
        ProdottoBusiness prodottoBusiness = new ProdottoBusiness();
        ProdottoCompositoBusiness prodottoCompositoBusiness = new ProdottoCompositoBusiness();
        ServizioBusiness servizioBusiness = new ServizioBusiness();
        UtenteBusiness utenteBusiness = new UtenteBusiness();
        List<Recensione> recensioni = recensioneBusiness.getRecensioniByIdManager(utente.getIdUtente());


        for (Recensione recensione : recensioni) {
            RigaFeedback rigaFeedback = new RigaFeedback();
            List<Prodotto> prodotti = prodottoBusiness.getProdottiRecensitiByIdManager(utente.getIdUtente());

            for (Prodotto prodotto : prodotti) {
                if (prodotto.getRecensione().getIdRecensione() == recensione.getIdRecensione()) {
                    rigaFeedback.setIdRecensione(recensione.getIdRecensione());
                    rigaFeedback.setNomeProdotto(prodotto.getNome());
                    rigaFeedback.setCommento(recensione.getTesto());
                    rigaFeedback.setFeedback(recensione.getFeedback());
                    rigaFeedback.setRisposta(recensione.getRisposta());
                    rigaFeedback.setEmailCliente(utenteBusiness.getCliente(recensione.getCliente().getIdCliente()).getEmail());

                    righe.add(rigaFeedback);
                }
            }
            List<ProdottoComposito> prodottiCompositi = prodottoCompositoBusiness.getProdottiCompositiRecensitiByIdManager(utente.getIdUtente());
            for (ProdottoComposito prodottoComposito : prodottiCompositi) {
                if (prodottoComposito.getRecensione().getIdRecensione() == recensione.getIdRecensione()) {
                    rigaFeedback.setIdRecensione(recensione.getIdRecensione());
                    rigaFeedback.setNomeProdotto(prodottoComposito.getNome());
                    rigaFeedback.setCommento(recensione.getTesto());
                    rigaFeedback.setFeedback(recensione.getFeedback());
                    rigaFeedback.setRisposta(recensione.getRisposta());
                    rigaFeedback.setEmailCliente(utenteBusiness.getCliente(recensione.getCliente().getIdCliente()).getEmail());

                    righe.add(rigaFeedback);
                }
            }

            List<Servizio> servizi = servizioBusiness.getServiziRecensitiByIdManager(utente.getIdUtente());
            for (Servizio servizio : servizi) {
                if (servizio.getRecensione().getIdRecensione() == recensione.getIdRecensione()) {
                    rigaFeedback.setIdRecensione(recensione.getIdRecensione());
                    rigaFeedback.setNomeProdotto(servizio.getNome());
                    rigaFeedback.setCommento(recensione.getTesto());
                    rigaFeedback.setFeedback(recensione.getFeedback());
                    rigaFeedback.setRisposta(recensione.getRisposta());
                    rigaFeedback.setEmailCliente(utenteBusiness.getCliente(recensione.getCliente().getIdCliente()).getEmail());
                    righe.add(rigaFeedback);
                }
            }
        }

    }



}
