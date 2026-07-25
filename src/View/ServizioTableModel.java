package View;

import Model.Recensione;
import View.ViewModel.RigaCatalogo;
import View.ViewModel.RigaServizio;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ServizioTableModel extends AbstractTableModel {

    private List<RigaServizio> righe = new ArrayList<RigaServizio>();

    public ServizioTableModel(List<RigaServizio> righe) {
        this.righe = righe;
    }

    public void setRighe(List<RigaServizio> righe) {
        this.righe = righe;
        fireTableDataChanged();
    }


    public List<RigaServizio> getRighe() {
        return righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaServizio riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: return riga.getIdServizio();
            case 1: return riga.getNomeServizio();
            case 2: return riga.getDescrizione();
            case 3: return riga.getPrezzo();
            case 4: return riga.getNomeProduttore();
            case 5: return riga.getNomeCategoria();
            case 6: return riga.getRecensione();
            case 7: return riga.getSelezionato();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        RigaServizio riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: riga.setIdServizio(Integer.parseInt(value.toString())); break;
            case 1: riga.setNomeServizio(value.toString()); break;
            case 2: riga.setDescrizione(value.toString()); break;
            case 3: riga.setPrezzo(Float.parseFloat(value.toString())); break;
            case 4: riga.setNomeProduttore(value.toString()); break;
            case 5: riga.setNomeCategoria(value.toString()); break;
            case 6: riga.setRecensione(Recensione.Feedback.valueOf((String) value), value.toString()); break;
            case 7: riga.setSelezionato(Boolean.parseBoolean(value.toString())); break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return columnIndex >= 7;

    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (columnIndex) {
            case 0: return "ID Servizio";
            case 1: return "Nome";
            case 2: return "Descrizione";
            case 3: return "Prezzo (€)";
            case 4: return "Produttore";
            case 5: return "Categoria";
            case 6: return "Recensione";
            case 7: return "Seleziona";
        }

        return null;
    }

    @Override
    public Class getColumnClass(int columnIndex) {

        if (columnIndex == 7) {
            return Boolean.class;
        }

        return Object.class;
    }

    public void removeRow(int row) {
        if (row >= 0 && row < righe.size()) {
            righe.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }

}

