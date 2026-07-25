package View;

import Business.ListaAcquistoBusiness;
import Business.ProdottoMagazzinoBusiness;
import Model.ListaAcquisto;
import Model.ProdottoMagazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.Date;

public class ProdottiInputDialogPanel extends JPanel {

    private JComboBox<ListaAcquisto> listaAcquistoCbx;
    private ListaAcquistoBusiness listaAcquistoBusiness = new ListaAcquistoBusiness();
    private int idCliente;
    private int idProdotto;
    private String nomeProdotto;
    private JTextField quantita;
    public ProdottiInputDialogPanel(int idCliente, int idProdotto, String nomeProdotto, JComboBox<ListaAcquisto> listaAcquistoCbx, JTextField quantita) {

        this.idCliente = idCliente;
        this.idProdotto = idProdotto;
        this.nomeProdotto = nomeProdotto;
        this.listaAcquistoCbx = listaAcquistoCbx;
        this.quantita = quantita;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        ProdottoMagazzino prodottoMagazzino = getProdotto(nomeProdotto,idProdotto);
        JLabel quantitaLabel = new JLabel("Quantità(MAX: " + prodottoMagazzino.getQuantita() +"): ");
        JLabel listeLabel = new JLabel("Liste: ");

        LocalDateTime currentDateTime = LocalDateTime.now();
        Date data = java.sql.Timestamp.valueOf(currentDateTime);

        listaAcquistoCbx.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    ListaAcquisto selectedItem = (ListaAcquisto) listaAcquistoCbx.getSelectedItem();
                    if (selectedItem.getNome().equals("Crea Lista")) {
                        String nomeLista = JOptionPane.showInputDialog(null, "Inserisci il nome della nuova lista:");
                        if (nomeLista != null && !nomeLista.isEmpty()) {
                            listaAcquistoBusiness.addLista(new ListaAcquisto(nomeLista, data), idCliente);
                            addListeAcquistoToDropdown();
                        } else {
                            JOptionPane.showMessageDialog(null, "Inserimento della nuova lista annullato");
                        }
                    } else {
                        ((ListaAcquisto) listaAcquistoCbx.getSelectedItem()).addArticolo(prodottoMagazzino);
                    }
                }
            }
        });

        quantita.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!(Character.isDigit(e.getKeyChar()) && Integer.parseInt(quantita.getText()) > 0 && Integer.parseInt(quantita.getText()) <= prodottoMagazzino.getQuantita())) {
                    JOptionPane.showMessageDialog(null, "Inserire valore valido");
                }
            }

        });


        addListeAcquistoToDropdown();

        gbc.gridy = 0;
        gbc.gridx = 1;
        add(listeLabel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 2;
        add(listaAcquistoCbx, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        add(quantitaLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 2;
        add(quantita, gbc);

    }

    private void addListeAcquistoToDropdown() {
        listaAcquistoCbx.removeAllItems();
        java.util.List<ListaAcquisto> dropdownValues = listaAcquistoBusiness.getListeByIdCliente(idCliente);
        for (ListaAcquisto item : dropdownValues) {
            listaAcquistoCbx.addItem(item);
        }
        listaAcquistoCbx.addItem(new ListaAcquisto("Crea Lista"));
    }

    private ProdottoMagazzino getProdotto(String nomeProdotto, int idProdotto) {
        ProdottoMagazzinoBusiness prodottoMagazzinoBusiness = new ProdottoMagazzinoBusiness();
        return prodottoMagazzinoBusiness.getProdottoByName(nomeProdotto, idProdotto);

    }
}
