package View;

import View.ViewModel.RigaRecensione;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class RecensioneTableModel extends AbstractTableModel {

    private List<RigaRecensione> righe = new ArrayList<RigaRecensione>();

    public RecensioneTableModel(List<RigaRecensione> righe) {
        this.righe = righe;
    }

    public void setRighe(List<RigaRecensione> righe) {
        this.righe = righe;
        fireTableDataChanged();
    }

    public List<RigaRecensione> getRighe() {
        return righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaRecensione riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: return riga.getId();
            case 1: return riga.getNome();
            case 2: return riga.isSelezionato();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        RigaRecensione riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: riga.setId(Integer.parseInt(value.toString()));
            case 1: riga.setNome(value.toString());
            case 2: riga.setSelezionato(Boolean.valueOf(value.toString()));
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (columnIndex) {
            case 0: return "ID";
            case 1: return "Nome";
            case 2: return "Seleziona";

        }
        return null;
    }

    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 2) {
            return Boolean.class;
        }
        return Object.class;
    }


}
