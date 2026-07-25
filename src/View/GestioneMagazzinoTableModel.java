package View;


import View.ViewModel.RigaMagazzino;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GestioneMagazzinoTableModel extends AbstractTableModel {

    private List<RigaMagazzino> righe = new ArrayList<RigaMagazzino>();

    public GestioneMagazzinoTableModel(List<RigaMagazzino> righe) {
        this.righe = righe;
    }

    public void setRighe(List<RigaMagazzino> righe) {
        this.righe = righe;
        fireTableDataChanged();
    }


    public List<RigaMagazzino> getRighe() {
        return righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaMagazzino riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: return riga.getIdProdotto();
            case 1: return riga.getNomeProdotto();
            case 2: return riga.getPrezzo();
            case 3: return riga.getDisponibilita();
            case 4: return riga.getQuantita();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        RigaMagazzino riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: riga.setIdProdotto(Integer.parseInt(value.toString())); break;
            case 1: riga.setNomeProdotto(value.toString()); break;
            case 2: riga.setPrezzo(Float.parseFloat(value.toString())); break;
            case 3: riga.setDisponibilita(); break;
            case 4: riga.setQuantita(Integer.parseInt(value.toString())); break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return columnIndex == 4;

    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (columnIndex) {
            case 0: return "ID";
            case 1: return "Nome";
            case 2: return "Prezzo (€)";
            case 3: return "Disponibile";
            case 4: return "Quantita";
        }

        return null;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Object.class;
    }
}
