package View.Listener;

import Business.*;
import Model.*;
import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import View.FinestraIniziale;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MouseListener implements java.awt.event.MouseListener {

    private JTable table;
    private FinestraIniziale frame;
    private boolean isProdotto;
    private List<ProdottoMagazzino> prodottiMagazzino;

    public MouseListener(JTable table, FinestraIniziale frame, boolean isProdotto) {
        this.table = table;
        this.frame = frame;
        this.isProdotto = isProdotto;
    }

    public MouseListener(JTable table, FinestraIniziale frame, List<ProdottoMagazzino> prodottiMagazzino) {
        this.table = table;
        this.frame = frame;
        this.prodottiMagazzino = prodottiMagazzino;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            int row = table.rowAtPoint(e.getPoint());
            int col = table.columnAtPoint(e.getPoint());
            Object cellValueObject = table.getValueAt(row, col);

            if (cellValueObject instanceof String) {
                String cellValue = (String) cellValueObject;
                JOptionPane.showMessageDialog(frame, cellValue, "Contenuto cella", JOptionPane.INFORMATION_MESSAGE);
            } else if (cellValueObject instanceof Float) {
                Float floatValue = (Float) cellValueObject;
                JOptionPane.showMessageDialog(frame, floatValue.toString(), "Prezzo", JOptionPane.INFORMATION_MESSAGE);
            } else if (cellValueObject instanceof ImageIcon) {
                ImageIcon imageIcon = (ImageIcon) cellValueObject;
                JLabel label = new JLabel(imageIcon);
                JOptionPane.showMessageDialog(frame, label, "Image Content", JOptionPane.PLAIN_MESSAGE);
            } else if (cellValueObject instanceof Date) {
                Date date = (Date) cellValueObject;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String data = dateFormat.format(date);
                JLabel label = new JLabel(data);
                JOptionPane.showMessageDialog(frame, label, "Data", JOptionPane.INFORMATION_MESSAGE);
            } else if (cellValueObject instanceof ListaAcquisto.StatoLista) {
                ListaAcquisto.StatoLista stato = (ListaAcquisto.StatoLista) cellValueObject;
                String value = stato.toString();
                JLabel label = new JLabel(value);
                JOptionPane.showMessageDialog(frame, label, "Stato", JOptionPane.INFORMATION_MESSAGE);
            }


        } else if (SwingUtilities.isRightMouseButton(e)) {
            Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
            int row = table.rowAtPoint(e.getPoint());
            int col = table.columnAtPoint(e.getPoint());
            Object value = table.getValueAt(row, col);
            if (utente instanceof Amministratore) {
                editCellsByAmministratore(row, col, value);
            } else if (utente instanceof Manager) {
                editCellsByManager(row, col, value);
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void modifyProdotto(int row, int col, Object value) {
        if (row >= 0 && (col == 1 || col == 2 || col == 3 || col == 6)) {
            int idProdotto = (Integer) table.getValueAt(row, 0);
            String newValue = null;
            Object selectedValue = null;
            if (col == 6) {
                MagazzinoBusiness magazzinoBusiness = new MagazzinoBusiness();
                int idMagazzino = magazzinoBusiness.getIdMagazzinoByIdProdotto(idProdotto);
                CollocazioneBusiness collocazioneBusiness = new CollocazioneBusiness();
                List<Collocazione> collocazioni = collocazioneBusiness.getCollocazioni(idMagazzino);
                Object[] options = collocazioni.toArray(new Object[0]);
                options = Arrays.copyOf(options, options.length + 1);
                selectedValue = JOptionPane.showInputDialog(null, "Seleziona la collocazione in cui inserire il prodotto:",
                        "Seleziona collocazione", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            } else {
                newValue = JOptionPane.showInputDialog(frame, "Modifica contenuto cella:", value);
            }
            if (newValue != null || selectedValue != null) {

                ProdottoBusiness prodottoBusiness = new ProdottoBusiness();
                Prodotto prodotto = prodottoBusiness.getProdottoById(idProdotto);
                switch (col) {
                    case 1:
                        table.setValueAt(newValue, row, col);
                        prodotto.setNome(newValue);
                        break;
                    case 2:
                        table.setValueAt(newValue, row, col);
                        prodotto.setDescrizione(newValue);
                        break;
                    case 3:
                        table.setValueAt(newValue, row, col);
                        prodotto.setPrezzo(Float.parseFloat(newValue));
                        break;
                    case 6:
                        Collocazione collocazione = (Collocazione) selectedValue;
                        table.setValueAt(collocazione, row, col);
                        prodotto.setCollocazione(collocazione.getIdCollocazione(), collocazione.getCorsia(), collocazione.getScaffale());
                        break;
                }
                prodottoBusiness.updateProdotto(prodotto);
            }
        }
    }

    private void modifyProdottoComposito(int row, int col, Object value) {
        if (row >= 0 && (col == 1 || col == 2 || col == 3 ||col == 6)) {
            int idProdottoComposito = (Integer) table.getValueAt(row, 0);
            String newValue = null;
            Object selectedValue = null;
            if (col == 6) {
                MagazzinoBusiness magazzinoBusiness = new MagazzinoBusiness();
                int idMagazzino = magazzinoBusiness.getIdMagazzinoByIdProdottoComposito(idProdottoComposito);
                CollocazioneBusiness collocazioneBusiness = new CollocazioneBusiness();
                List<Collocazione> collocazioni = collocazioneBusiness.getCollocazioni(idMagazzino);
                Object[] options = collocazioni.toArray(new Object[0]);
                options = Arrays.copyOf(options, options.length + 1);
                selectedValue = JOptionPane.showInputDialog(null, "Seleziona la collocazione in cui inserire il prodotto:",
                        "Seleziona collocazione", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            } else {
                newValue = JOptionPane.showInputDialog(frame, "Modifica contenuto cella:", value);
            }
            if (newValue != null || selectedValue != null) {

                ProdottoCompositoBusiness prodottoCompositoBusiness = new ProdottoCompositoBusiness();
                ProdottoComposito prodottoComposito = prodottoCompositoBusiness.getProdottoCompositoById(idProdottoComposito);
                switch (col) {
                    case 1:
                        table.setValueAt(newValue, row, col);
                        String[] prodotti = newValue.split("\\(");
                        String sottoprodottoString  = prodotti[1].substring(0, prodotti[1].length()-1);
                        String[] sottoprodotti = sottoprodottoString.split(";");
                        ProdottoBusiness prodottoBusiness = new ProdottoBusiness();
                        List<Prodotto> listToAdd = new ArrayList<>();
                        List<Prodotto> listToRemove = new ArrayList<>();
                        listToRemove.addAll(prodottoComposito.getSottoprodotti());
                        for (String sottoprodotto: sottoprodotti) {
                            listToAdd.add(prodottoBusiness.getProdottoByName(sottoprodotto.replace(";", "")));
                        }
                        prodottoComposito.setSottoprodotti(listToAdd);
                        prodottoCompositoBusiness.updateSottoprodotti(idProdottoComposito, listToRemove, listToAdd);
                        table.setValueAt(prodottoComposito.getPrezzo(), row, 2);
                        break;
                    case 2:
                        table.setValueAt(newValue, row, col);
                        prodottoComposito.setDescrizione(newValue);
                        prodottoCompositoBusiness.updateProdottoComposito(prodottoComposito);
                        break;
                    case 3:
                        table.setValueAt(newValue, row, col);
                        prodottoComposito.setPrezzo(Float.parseFloat(newValue));
                        prodottoCompositoBusiness.updateProdottoComposito(prodottoComposito);
                        break;
                    case 6:
                        Collocazione collocazione = (Collocazione) selectedValue;
                        table.setValueAt(collocazione, row, col);
                        prodottoComposito.setCollocazione(collocazione.getIdCollocazione(), collocazione.getCorsia(), collocazione.getScaffale());
                        prodottoCompositoBusiness.updateProdottoComposito(prodottoComposito);
                        break;
                }
            }
        }
    }

    public void modifyServizio(int row, int col, Object value) {
        if (row >= 0 && (col == 1 || col == 2 || col == 3)) {
            int idServizio = (Integer) table.getValueAt(row, 0);
            String newValue = JOptionPane.showInputDialog(frame, "Modifica contenuto cella:", value);
            if (newValue != null) {
                ServizioBusiness servizioBusiness = new ServizioBusiness();
                Servizio servizio = servizioBusiness.getServizioById(idServizio);
                switch (col) {
                    case 1:
                        table.setValueAt(newValue, row, col);
                        servizio.setNome(newValue);
                        break;
                    case 2:
                        table.setValueAt(newValue, row, col);
                        servizio.setDescrizione(newValue);
                        break;
                    case 3:
                        table.setValueAt(newValue, row, col);
                        servizio.setPrezzo(Float.parseFloat(newValue));
                        break;
                }
                servizioBusiness.updateServizio(servizio);
            }
        }
    }

    private void editCellsByAmministratore(int row, int col, Object value) {
        if (isProdotto) {
            if (((String) table.getValueAt(row, 1)).contains(";")) {
                modifyProdottoComposito(row, col, value);
            } else {
                modifyProdotto(row, col, value);
            }
        } else {
            modifyServizio(row, col, value);
        }
    }

    private void editCellsByManager(int row, int col, Object value) {
        if (row >= 0 && col == 4) {
            int idProdotto = (Integer) table.getValueAt(row, 0);
            String nomeProdotto = (String) table.getValueAt(row, 1);
            boolean isComposito = false;
            for (ProdottoMagazzino prodottoMagazzino : prodottiMagazzino) {
                if (prodottoMagazzino.getProdotto().getId() == idProdotto && prodottoMagazzino.getProdotto().getNome().equalsIgnoreCase(nomeProdotto)) {
                    isComposito = prodottoMagazzino.getProdotto() instanceof ProdottoComposito;
                }
            }
            String newValue = JOptionPane.showInputDialog(frame, "Modifica contenuto cella:", value);
            if (newValue != null) {
                ProdottoMagazzinoBusiness prodottoMagazzinoBusiness = new ProdottoMagazzinoBusiness();
                prodottoMagazzinoBusiness.updateQuantita(idProdotto, Integer.parseInt(newValue), isComposito);
            }
        }
    }

}
