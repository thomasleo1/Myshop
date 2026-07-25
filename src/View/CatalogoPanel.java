package View;

import Business.*;
import Business.Strategy.*;
import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import Model.ProdottoMagazzino;
import Model.PuntoVendita;
import Model.Servizio;
import Model.Utente;
import View.Listener.MouseListener;
import View.ViewModel.RigaCatalogo;
import View.ViewModel.RigaServizio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogoPanel extends JPanel {

    private JScrollPane scrollPane;
    private JScrollPane scrollPaneServizi;
    private CatalogoTableModel tableModel;
    private ServizioTableModel tableServizio;
    private JTable tabellaProdotti;
    private JTable tabellaServizi;
    private int idPuntoVendita;
    private ArrayList<IProdotto> prodottiNonDisponibili = new ArrayList<>();

    public CatalogoPanel(FinestraIniziale frame, int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;

        List<RigaCatalogo> righe = new ArrayList<>();
        if (idPuntoVendita == 0) {
            inserisciProdotti(righe);
        } else {
            inserisciProdottiByPuntoVendita(righe, idPuntoVendita);
        }


        tableModel = new CatalogoTableModel(righe);
        tabellaProdotti = new JTable(tableModel);
        tabellaProdotti.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabellaProdotti.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabellaProdotti.getColumnModel().getColumn(2).setPreferredWidth(400);
        tabellaProdotti.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabellaProdotti.getColumnModel().getColumn(4).setPreferredWidth(200);
        tabellaProdotti.getColumnModel().getColumn(5).setPreferredWidth(200);
        tabellaProdotti.getColumnModel().getColumn(6).setPreferredWidth(300);
        tabellaProdotti.getColumnModel().getColumn(7).setPreferredWidth(350);
        tabellaProdotti.getColumnModel().getColumn(8).setPreferredWidth(300);
        tabellaProdotti.getColumnModel().getColumn(9).setPreferredWidth(100);
        tabellaProdotti.setRowHeight(85);

        MouseListener mouseListenerProdotto = new MouseListener(tabellaProdotti,frame, true);
        tabellaProdotti.addMouseListener(mouseListenerProdotto);

        scrollPane = new JScrollPane(tabellaProdotti);


        List<RigaServizio> righeServizi = new ArrayList<>();
        inserisciServizi(righeServizi);
        tableServizio = new ServizioTableModel(righeServizi);
        tabellaServizi = new JTable(tableServizio);
        tabellaServizi.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabellaServizi.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabellaServizi.getColumnModel().getColumn(2).setPreferredWidth(400);
        tabellaServizi.getColumnModel().getColumn(3).setPreferredWidth(250);
        tabellaServizi.getColumnModel().getColumn(4).setPreferredWidth(150);
        tabellaServizi.getColumnModel().getColumn(5).setPreferredWidth(250);
        tabellaServizi.getColumnModel().getColumn(6).setPreferredWidth(250);
        tabellaServizi.getColumnModel().getColumn(7).setPreferredWidth(50);
        tabellaServizi.setRowHeight(100);

        MouseListener mouseListenerServizio = new MouseListener(tabellaServizi, frame, false);
        tabellaServizi.addMouseListener(mouseListenerServizio);

        scrollPaneServizi = new JScrollPane(tabellaServizi);

        this.scrollPaneVisibility(true, true);

    }

    private void inserisciProdottiByPuntoVendita(List<RigaCatalogo> righe, int idPuntoVendita) {
        ProdottoBusiness prodottoBusiness = new ProdottoBusiness();
        ProdottoCompositoBusiness prodottoCompositoBusiness = new ProdottoCompositoBusiness();
        PuntoVenditaBusiness puntoVenditaBusiness = new PuntoVenditaBusiness();
        PuntoVendita puntoVendita = puntoVenditaBusiness.getPuntoVenditaById(idPuntoVendita);
        List<ProdottoComposito> prodottiCompositi = prodottoCompositoBusiness.getProdottiCompositiByIdMagazzino(puntoVendita.getMagazzino().getIdMagazzino());
        List<Prodotto> prodotti = prodottoBusiness.getProdottiByIdMagazzino(puntoVendita.getMagazzino().getIdMagazzino());
        popolaRighe(righe, prodotti, prodottiCompositi);
    }

    public void inserisciProdotti(List<RigaCatalogo> righe) {
        ProdottoBusiness prodottoBusiness = new ProdottoBusiness();
        ProdottoCompositoBusiness prodottoCompositoBusiness = new ProdottoCompositoBusiness();
        List<ProdottoComposito> prodottiCompositi = prodottoCompositoBusiness.getProdottiCompositi();
        List<Prodotto> prodotti = prodottoBusiness.getProdotti();

        popolaRighe(righe, prodotti, prodottiCompositi);
    }

    public void inserisciProdottiNonDisponibili() {
        ProdottoBusiness prodottoBusiness = new ProdottoBusiness();
        ProdottoCompositoBusiness prodottoCompositoBusiness = new ProdottoCompositoBusiness();
        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        List<Prodotto> prodotti = prodottoBusiness.getProdottiNonDisponibiliByIdUtente(utente.getIdUtente());
        List<ProdottoComposito> prodottiCompositi = prodottoCompositoBusiness.getProdottiNonDisponibiliByIdUtente(utente.getIdUtente());
        popolaRigheProdottiNonDisponibili(prodotti, prodottiCompositi);

        this.scrollPaneVisibility(true, false);
    }

    public void popolaRigheProdottiNonDisponibili(List<Prodotto> prodotti, List<ProdottoComposito> prodottiCompositi) {
        List<RigaCatalogo> righe = new ArrayList<>();
        for (Prodotto prodotto : prodotti) {
            RigaCatalogo riga = new RigaCatalogo();
            riga.setIdProdotto(prodotto.getId());
            riga.setNomeProdotto(prodotto.getNome());
            riga.setDescrizione(prodotto.getDescrizione());
            riga.setPrezzo(prodotto.getPrezzo());
            riga.setNomeProduttore(prodotto.getProduttore().getNome());
            riga.setNomeCategoria(prodotto.getCategoria().getNome());
            riga.setNomeSottocategoria(prodotto.getSottocategoria().getNome());
            riga.setCollocazione(prodotto.getCollocazione().getScaffale(), prodotto.getCollocazione().getCorsia());
            if (prodotto.getRecensione() != null) {
                riga.setRecensione(prodotto.getRecensione().getFeedback(), prodotto.getRecensione().getTesto());
            }
            riga.setImmagine(prodotto.getImmagine());
            riga.setIsComposito(false);
            righe.add(riga);
            prodottiNonDisponibili.add(prodotto);
        }

        for (ProdottoComposito prodottoComposito : prodottiCompositi) {
            RigaCatalogo riga = new RigaCatalogo();
            riga.setIdProdotto(prodottoComposito.getId());
            riga.setNomeProdotto(prodottoComposito.getNome());
            riga.setDescrizione(prodottoComposito.getDescrizione());

            String sottoprodotti = "";
            for (int j = 0; j < prodottoComposito.getSottoprodotti().size(); j++) {
                sottoprodotti += prodottoComposito.getSottoprodotti().get(j).getNome() + ";";
            }
            riga.setNomeSottoprodotti(sottoprodotti);
            riga.setPrezzo(prodottoComposito.getPrezzo());
            riga.setNomeProduttore(prodottoComposito.getProduttore().getNome());
            riga.setNomeCategoria(prodottoComposito.getCategoria().getNome());
            riga.setCollocazione(prodottoComposito.getCollocazione().getScaffale(), prodottoComposito.getCollocazione().getCorsia());
            if (prodottoComposito.getRecensione() != null) {
                riga.setRecensione(prodottoComposito.getRecensione().getFeedback(), prodottoComposito.getRecensione().getTesto());
            }
            riga.setImmagine(prodottoComposito.getImmagine());
            riga.setIsComposito(true);
            righe.add(riga);
            prodottiNonDisponibili.add(prodottoComposito);
        }
        tableModel.setRighe(righe);
    }

    public ArrayList<IProdotto> getProdottiNonDisponibili() {
        return this.prodottiNonDisponibili;
    }

    private void popolaRighe(List<RigaCatalogo> righe, List<Prodotto> prodotti, List<ProdottoComposito> prodottiCompositi) {
        for (int i = 0; i < prodotti.size(); i++) {
            RigaCatalogo riga = new RigaCatalogo();
            riga.setIdProdotto(prodotti.get(i).getId());
            riga.setNomeProdotto(prodotti.get(i).getNome());
            riga.setDescrizione(prodotti.get(i).getDescrizione());
            riga.setPrezzo(prodotti.get(i).getPrezzo());
            riga.setNomeProduttore(prodotti.get(i).getProduttore().getNome());
            riga.setNomeCategoria(prodotti.get(i).getCategoria().getNome());
            riga.setNomeSottocategoria(prodotti.get(i).getSottocategoria().getNome());
            riga.setCollocazione(prodotti.get(i).getCollocazione().getScaffale(), prodotti.get(i).getCollocazione().getCorsia());
            if (prodotti.get(i).getRecensione() != null) {
                riga.setRecensione(prodotti.get(i).getRecensione().getFeedback(), prodotti.get(i).getRecensione().getTesto());
            }
            riga.setImmagine(prodotti.get(i).getImmagine());
            riga.setIsComposito(false);
            righe.add(riga);
        }

        for (int i = 0; i < prodottiCompositi.size(); i++) {
            RigaCatalogo riga = new RigaCatalogo();
            riga.setIdProdotto(prodottiCompositi.get(i).getId());
            riga.setNomeProdotto(prodottiCompositi.get(i).getNome());
            riga.setDescrizione(prodottiCompositi.get(i).getDescrizione());

            String sottoprodotti = "";
            for (int j = 0; j < prodottiCompositi.get(i).getSottoprodotti().size(); j++) {
                sottoprodotti += prodottiCompositi.get(i).getSottoprodotti().get(j).getNome() + ";";
            }
            riga.setNomeSottoprodotti(sottoprodotti);
            riga.setPrezzo(prodottiCompositi.get(i).getPrezzo());
            riga.setNomeProduttore(prodottiCompositi.get(i).getProduttore().getNome());
            riga.setNomeCategoria(prodottiCompositi.get(i).getCategoria().getNome());
            riga.setCollocazione(prodottiCompositi.get(i).getCollocazione().getScaffale(), prodotti.get(i).getCollocazione().getCorsia());
            if (prodottiCompositi.get(i).getRecensione() != null) {
                riga.setRecensione(prodottiCompositi.get(i).getRecensione().getFeedback(), prodottiCompositi.get(i).getRecensione().getTesto());
            }
            riga.setImmagine(prodottiCompositi.get(i).getImmagine());
            riga.setIsComposito(true);
            righe.add(riga);
        }
    }

    public void inserisciServizi(List<RigaServizio> righe) {
        ServizioBusiness servizioBusiness = new ServizioBusiness();
        List<Servizio> servizi = servizioBusiness.getServizi();

        for (int i = 0; i < servizi.size(); i++) {
            RigaServizio riga = new RigaServizio();
            riga.setIdServizio(servizi.get(i).getId());
            riga.setNomeServizio(servizi.get(i).getNome());
            riga.setDescrizione(servizi.get(i).getDescrizione());
            riga.setPrezzo(servizi.get(i).getPrezzo());
            riga.setNomeProduttore(servizi.get(i).getProduttore().getNome());
            riga.setNomeCategoria(servizi.get(i).getCategoria().getNome());
            if (servizi.get(i).getRecensione() != null) {
                riga.setRecensione(servizi.get(i).getRecensione().getFeedback(), servizi.get(i).getRecensione().getTesto());
            }
            righe.add(riga);
        }
    }

    public void aggiornaTabellaProdotti(List<RigaCatalogo> righe, CatalogoTableModel tableModel) {
        tableModel.setRighe(righe);
    }

    public void aggiornaTabellaServizi(List<RigaServizio> righe, ServizioTableModel tableModel) {
        tableModel.setRighe(righe);
    }

    public void scrollPaneVisibility(boolean isProdotto, boolean isDisponibile) {
        this.removeAll();
        this.setLayout(new BorderLayout());
        PulsantiCatalogo pulsantiCatalogo = new PulsantiCatalogo(this, tableModel, tableServizio, tabellaProdotti, tabellaServizi, isProdotto, isDisponibile);
        this.add(pulsantiCatalogo, BorderLayout.NORTH);
        if (isProdotto) {
            this.add(scrollPane, BorderLayout.CENTER);
        }
        else {
            this.add(scrollPaneServizi, BorderLayout.CENTER);
        }
        this.repaint();
        this.validate();
    }

    public void ordinaProdotti(List<RigaCatalogo> righeAttuali, CatalogoTableModel tableModel, IOrdinaProdotti strategy) {

        List<RigaCatalogo> righe = new ArrayList<>();
        ProdottoBusiness prodottoBusiness = new ProdottoBusiness();
        ProdottoCompositoBusiness prodottoCompositoBusiness = new ProdottoCompositoBusiness();
        List<IProdotto> prodotti = new ArrayList<>();
        for (RigaCatalogo riga : righeAttuali) {
            if (riga.getNomeSottoprodotti() == null) {
                prodotti.add(prodottoBusiness.getProdottoByName(riga.getNomeProdotto()));
            } else {
                prodotti.add(prodottoCompositoBusiness.getProdottoCompositoByName(riga.getNomeProdotto()));
            }

        }

        OrdinaProdotti ordinamentoProdotti = new OrdinaProdotti(prodotti);
        ordinamentoProdotti.setOrdinaProdotti(strategy);
        ordinamentoProdotti.ordina();

        List<IProdotto> prodottiOrdinati = ordinamentoProdotti.getProdotti();
        for (int i = 0; i < prodottiOrdinati.size(); i++) {
            RigaCatalogo riga = new RigaCatalogo();
            riga.setIdProdotto(prodottiOrdinati.get(i).getId());
            riga.setNomeProdotto(prodottiOrdinati.get(i).getNome());
            riga.setDescrizione(prodottiOrdinati.get(i).getDescrizione());
            String sottoprodotti = "";
            if (prodottiOrdinati.get(i).getSottoprodotti() != null) {
                for (int j = 0; j < prodottiOrdinati.get(i).getSottoprodotti().size(); j++) {
                    sottoprodotti += prodottiOrdinati.get(i).getSottoprodotti().get(j).getNome() + "; ";
                }

                riga.setNomeSottoprodotti(sottoprodotti);
            }
            riga.setPrezzo(prodottiOrdinati.get(i).getPrezzo());
            riga.setNomeProduttore(prodottiOrdinati.get(i).getProduttore().getNome());
            riga.setNomeCategoria(prodottiOrdinati.get(i).getCategoria().getNome());
            if (riga.getNomeSottoprodotti() == null) {
                riga.setNomeSottocategoria(prodottiOrdinati.get(i).getSottocategoria().getNome());
            }
            riga.setCollocazione(prodottiOrdinati.get(i).getCollocazione().getScaffale(), prodottiOrdinati.get(i).getCollocazione().getCorsia());
            if (prodottiOrdinati.get(i).getRecensione() != null) {
                riga.setRecensione(prodottiOrdinati.get(i).getRecensione().getFeedback(), prodottiOrdinati.get(i).getRecensione().getTesto());
            }
            riga.setImmagine(prodottiOrdinati.get(i).getImmagine());
            righe.add(riga);
        }
        tableModel.setRighe(righe);
    }


    public void ordinaServizi(List<RigaServizio> righeAttuali, ServizioTableModel tableModel, IOrdinaServizi strategy) {

        List<RigaServizio> righe = new ArrayList<>();
        ServizioBusiness servizioBusiness = new ServizioBusiness();
        List<Servizio> servizi = new ArrayList<>();
        for (RigaServizio riga : righeAttuali) {
            servizi.add(servizioBusiness.getServizio(riga.getNomeServizio()));
        }

        OrdinaServizi ordinamentoServizi = new OrdinaServizi(servizi);
        ordinamentoServizi.setOrdinaServizi(strategy);
        ordinamentoServizi.ordina();

        List<Servizio> serviziOrdinati = ordinamentoServizi.getServizi();
        for (int i = 0; i < serviziOrdinati.size(); i++) {
            RigaServizio riga = new RigaServizio();
            riga.setIdServizio(servizi.get(i).getId());
            riga.setNomeServizio(servizi.get(i).getNome());
            riga.setDescrizione(servizi.get(i).getDescrizione());
            riga.setPrezzo(servizi.get(i).getPrezzo());
            riga.setNomeProduttore(servizi.get(i).getProduttore().getNome());
            riga.setNomeCategoria(servizi.get(i).getCategoria().getNome());
            if (servizi.get(i).getRecensione() != null) {
                riga.setRecensione(servizi.get(i).getRecensione().getFeedback(), servizi.get(i).getRecensione().getTesto());
            }
            righe.add(riga);
        }
        tableModel.setRighe(righe);
    }
}