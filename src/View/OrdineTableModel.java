package View;

import Model.Cliente;
import Model.Manager;
import Model.Recensione;
import Model.Utente;
import View.ViewModel.RigaFeedback;
import View.ViewModel.RigaOrdine;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class OrdineTableModel extends AbstractTableModel {
    private List<RigaOrdine> righe = new ArrayList<RigaOrdine>();
    private Utente utente;

    public OrdineTableModel(List<RigaOrdine> righe) {
        this.righe = righe;
    }

    public void setRighe(List<RigaOrdine> righe) {
        this.righe = righe;
        fireTableDataChanged();
    }


    public List<RigaOrdine> getRighe() {
        return righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaOrdine riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: return riga.getIdProdotto();
            case 1: return riga.getNomeProdotto();
            case 2: return riga.getQuantita();
            case 3: return riga.isSelezionato();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        RigaOrdine riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: riga.setIdProdotto(Integer.parseInt(value.toString())); break;
            case 1: riga.setNomeProdotto(value.toString()); break;
            case 2: riga.setQuantita(Integer.parseInt(value.toString())); break;
            case 3: riga.setSelezionato(Boolean.parseBoolean(value.toString())); break;
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (columnIndex) {
            case 0: return "Id prodotto";
            case 1: return "Nome prodotto";
            case 2: return "Quantita";
            case 3: return "Seleziona";
        }
        return null;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 3) {
            return Boolean.class;
        }
        return Object.class;
    }
}
