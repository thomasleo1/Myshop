package View.Listener;

import Business.ProdottoBusiness;
import Business.ProdottoCompositoBusiness;
import Business.ServizioBusiness;
import View.CatalogoTableModel;
import View.ServizioTableModel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabellaListener implements ActionListener {

    private JTable tabellaProdotti;
    private JTable tabellaServizi;
    public static final String CANCELLA_PRODOTTO = "cancella prodotto";
    public static final String CANCELLA_SERVIZIO = "cancella servizio";
    private CatalogoTableModel model;
    private ServizioTableModel modelServizio;

    public TabellaListener(CatalogoTableModel model, ServizioTableModel modelServizio, JTable tabellaProdotti, JTable tabellaServizi) {
        this.tabellaProdotti = tabellaProdotti;
        this.tabellaServizi = tabellaServizi ;
        this.model = model;
        this.modelServizio = modelServizio;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();
        int selectedProdotto = tabellaProdotti.getSelectedRow();
        int selectedServizio = tabellaServizi.getSelectedRow();

        if (CANCELLA_PRODOTTO.equals(action)) {
            if (selectedProdotto == -1) {
                JOptionPane.showMessageDialog(null, "Seleziona una riga da eliminare.", "Errore", JOptionPane.ERROR_MESSAGE);
            } else {
                int id = (Integer) model.getValueAt(selectedProdotto, 0);
                String nome = (String) model.getValueAt(selectedProdotto, 1);
                if (nome.contains(";")) {
                    model.removeRow(selectedProdotto);
                    ProdottoCompositoBusiness prodottoCompositoBusiness = new ProdottoCompositoBusiness();
                    prodottoCompositoBusiness.removeProdottoComposito(id);
                } else {
                    model.removeRow(selectedProdotto);
                    ProdottoBusiness prodottoBusiness = new ProdottoBusiness();
                    prodottoBusiness.removeProdotto(id);
                }
            }
        } else if (CANCELLA_SERVIZIO.equals(action)) {
            if (selectedServizio == -1) {
                JOptionPane.showMessageDialog(null, "Seleziona una riga da eliminare.", "Errore", JOptionPane.ERROR_MESSAGE);
            } else {
                int id = (Integer) modelServizio.getValueAt(selectedServizio, 0);
                modelServizio.removeRow(selectedServizio);
                ServizioBusiness servizioBusiness = new ServizioBusiness();
                servizioBusiness.removeServizio(id);
            }
        }
    }
}
