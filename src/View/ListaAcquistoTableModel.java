package View;

import Model.ListaAcquisto;
import View.ViewModel.RigaLista;

import javax.swing.table.AbstractTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaAcquistoTableModel extends AbstractTableModel {

    private List<RigaLista> righe = new ArrayList<RigaLista>();

    public ListaAcquistoTableModel(List<RigaLista> righe) {
        this.righe = righe;
    }

    public void setRighe(List<RigaLista> righe) {
        this.righe = righe;
        fireTableDataChanged();
    }

    public List<RigaLista> getRighe() {
        return righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaLista riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: return riga.getIdLista();
            case 1: return riga.getNomeLista();
            case 2: return riga.getDataCreazione();
            case 3: return riga.getStatoLista();
            case 4: return riga.getProdotti() + riga.getServizi();
            case 5: return riga.getPrezzo();
            case 6: return riga.getSelezionato();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        RigaLista riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: riga.setIdLista(Integer.parseInt(value.toString()));
            case 1: riga.setNomeLista(value.toString());
            case 2:  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date data = dateFormat.parse(value.toString());
                    riga.setDataCreazione(data);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            case 3: riga.setStatoLista(ListaAcquisto.StatoLista.valueOf((String) value));
            case 4: String[] articoli = value.toString().split("\n");
                if (articoli.length == 2) {
                    riga.setProdotti(articoli[0]);
                    riga.setServizi(articoli[1]);
                }
            case 5: riga.setPrezzo(Float.parseFloat(value.toString()));
            case 6: riga.setSelezionato(Boolean.valueOf(value.toString()));
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 6;
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (columnIndex) {
            case 0: return "ID";
            case 1: return "Nome";
            case 2: return "Data creazione";
            case 3: return "Stato";
            case 4: return "Prodotti";
            case 5: return "Prezzo totale";
            case 6: return "Seleziona";
        }
        return null;
    }

    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 6) {
            return Boolean.class;
        }
        return Object.class;
    }


}
