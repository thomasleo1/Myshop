package View;

import Model.Cliente;
import Model.Manager;
import Model.Recensione;
import Model.Utente;
import View.ViewModel.RigaFeedback;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class VediFeedbackTableModel extends AbstractTableModel {

    private List<RigaFeedback> righe = new ArrayList<RigaFeedback>();
    private Utente utente;

    public VediFeedbackTableModel(List<RigaFeedback> righe, Utente utente) {
        this.righe = righe;
        this.utente = utente;
    }

    public void setRighe(List<RigaFeedback> righe) {
        this.righe = righe;
        fireTableDataChanged();
    }


    public List<RigaFeedback> getRighe() {
        return righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        if (utente instanceof Cliente) {
            return 4;
        } else {
            return 7;
        }

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaFeedback riga = righe.get(rowIndex);

        if (utente instanceof Cliente) {
            switch (columnIndex) {
                case 0: return riga.getNomeProdotto();
                case 1: return riga.getFeedback();
                case 2: return riga.getCommento();
                case 3: return riga.getRisposta();
            }
        } else {
            switch (columnIndex) {
                case 0: return riga.getIdRecensione();
                case 1: return riga.getNomeProdotto();
                case 2: return riga.getEmailCliente();
                case 3: return riga.getFeedback();
                case 4: return riga.getCommento();
                case 5: return riga.getRisposta();
                case 6: return riga.isVisualizzato();
            }
        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        RigaFeedback riga = righe.get(rowIndex);

        if (utente instanceof Cliente) {
            switch (columnIndex) {
                case 0: riga.setNomeProdotto(value.toString()); break;
                case 1: riga.setFeedback(Recensione.Feedback.valueOf(value.toString())); break;
                case 2: riga.setCommento(value.toString()); break;
                case 3: riga.setRisposta(value.toString()); break;
            }
        } else {
            switch (columnIndex) {
                case 0: riga.setIdRecensione(Integer.parseInt(value.toString())); break;
                case 1: riga.setNomeProdotto(value.toString()); break;
                case 2: riga.setEmailCliente(value.toString()); break;
                case 3: riga.setFeedback(Recensione.Feedback.valueOf(value.toString())); break;
                case 4: riga.setCommento(value.toString()); break;
                case 5: riga.setRisposta(value.toString()); break;
                case 6: riga.setVisualizzato(Boolean.parseBoolean(value.toString())); break;
            }
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        if (utente instanceof Manager) {
            return columnIndex == 6;
        }
        return false;

    }

    @Override
    public String getColumnName(int columnIndex) {

        if (utente instanceof Cliente) {
            switch (columnIndex) {
                case 0: return "Nome Prodotto";
                case 1: return "Feedback";
                case 2: return "Commento";
                case 3: return "Risposta";
            }
        } else {
            switch (columnIndex) {
                case 0: return "Id Recensione";
                case 1: return "Nome Prodotto";
                case 2: return "Email cliente";
                case 3: return "Feedback";
                case 4: return "Commento";
                case 5: return "Risposta";
                case 6: return "Seleziona";
            }
        }

        return null;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 6) {
            return Boolean.class;
        }
        return Object.class;
    }
}
