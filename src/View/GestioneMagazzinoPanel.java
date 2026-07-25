package View;

import Business.ProdottoBusiness;
import Business.ProdottoCompositoBusiness;
import Business.ProdottoMagazzinoBusiness;
import Business.SessionManager;
import DAO.ProdottoMagazzinoDAO;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import Model.Manager;
import Model.ProdottoMagazzino;
import Model.Utente;
import View.Listener.MouseListener;
import View.ViewModel.RigaCatalogo;
import View.ViewModel.RigaMagazzino;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GestioneMagazzinoPanel extends JPanel{

    private List<ProdottoMagazzino> prodottiMagazzino;
    public GestioneMagazzinoPanel(FinestraIniziale frame) {

        List<RigaMagazzino> righe = new ArrayList<>();
        inserisciProdottiInMagazzino(righe);

        GestioneMagazzinoTableModel model = new GestioneMagazzinoTableModel(righe);
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.setRowHeight(85);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.SOUTH);

        MouseListener mouseListenerProdotto = new MouseListener(table, frame, prodottiMagazzino);
        table.addMouseListener(mouseListenerProdotto);


    }

    private void inserisciProdottiInMagazzino(List<RigaMagazzino> righe) {
        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        int idManager = 0;
        if (utente instanceof Manager) {
            idManager = utente.getIdUtente();
        }
        ProdottoMagazzinoBusiness prodottoMagazzinoBusiness = new ProdottoMagazzinoBusiness();
        prodottiMagazzino = prodottoMagazzinoBusiness.getProdottiByManagerId(idManager);

        for (int i = 0; i < prodottiMagazzino.size(); i++) {
            RigaMagazzino riga = new RigaMagazzino();
            riga.setIdProdotto(prodottiMagazzino.get(i).getProdotto().getId());
            riga.setNomeProdotto(prodottiMagazzino.get(i).getProdotto().getNome());
            riga.setPrezzo(prodottiMagazzino.get(i).getProdotto().getPrezzo());
            riga.setQuantita(prodottiMagazzino.get(i).getQuantita());
            riga.setDisponibilita();
            if (prodottiMagazzino.get(i).getProdotto() instanceof Prodotto) {
                riga.setComposito(false);
            } else {
                riga.setComposito(true);
            }
            righe.add(riga);
        }
    }
}
