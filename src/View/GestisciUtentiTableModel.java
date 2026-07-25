package View;

import Model.Utente;
import View.ViewModel.RigaUtente;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GestisciUtentiTableModel extends AbstractTableModel {

    private List<RigaUtente> righe = new ArrayList<RigaUtente>();

    public GestisciUtentiTableModel(List<RigaUtente> righe) {
        this.righe = righe;
    }

    public void setRighe(List<RigaUtente> righe) {
        this.righe = righe;
        fireTableDataChanged();
    }


    public List<RigaUtente> getRighe() {
        return righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaUtente riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: return riga.getId();
            case 1: return riga.getNome();
            case 2: return riga.getCognome();
            case 3: return riga.getEmail();
            case 4: return riga.getStato();
            case 5: return riga.isSelezionato();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        RigaUtente riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: riga.setId(Integer.parseInt(value.toString())); break;
            case 1: riga.setNome(value.toString()); break;
            case 2: riga.setCognome(value.toString()); break;
            case 3: riga.setEmail(value.toString()); break;
            case 4: riga.setStato(Utente.Stato.valueOf(value.toString())); break;
            case 5: riga.setSelezionato(Boolean.parseBoolean(value.toString())); break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return columnIndex == 5;

    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (columnIndex) {
            case 0: return "ID";
            case 1: return "Nome";
            case 2: return "Cognome";
            case 3: return "Email";
            case 4: return "Stato";
            case 5: return "Selezionato";
        }

        return null;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 5) {
            return Boolean.class;
        }
        return Object.class;
    }
}
