package View;

import Model.Collocazione;
import Model.ListaAcquisto;
import Model.Recensione;
import View.ViewModel.RigaCatalogo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatalogoTableModel extends AbstractTableModel {

    private List<RigaCatalogo> righe = new ArrayList<>();

    public CatalogoTableModel(List<RigaCatalogo> righe) {
        this.righe = righe;
    }

    public void setRighe(List<RigaCatalogo> righe) {
        this.righe = righe;
        fireTableDataChanged();
    }

    public List<RigaCatalogo> getRighe() {
        return righe;
    }

    public RigaCatalogo getRiga(int rowIndex) {
        return righe.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaCatalogo riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: return riga.getIdProdotto();
            case 1: String nome = riga.getNomeProdotto();
                if (riga.getNomeSottoprodotti() != null) {
                    nome = nome + "(" + riga.getNomeSottoprodotti() + ")";
                }
                return nome;
            case 2: return riga.getDescrizione();
            case 3: return riga.getPrezzo();
            case 4: return riga.getNomeProduttore();
            case 5: String cat = riga.getNomeCategoria();
            if (riga.getNomeSottocategoria() != null ) {
                cat = cat + "\n(" + riga.getNomeSottocategoria() + ")";
            }
            return cat;
            case 6: return riga.getCollocazione();
            case 7: return riga.getRecensione();
            case 8:
                File file = new File(riga.getImmagine());
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new FileInputStream(file)));
                    Image img = icon.getImage();
                    Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImg);
                } catch (IOException e) {
                    e.printStackTrace();
                    return new ImageIcon();
                }
            case 9: return riga.getSelezionato();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        RigaCatalogo riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: riga.setIdProdotto(Integer.parseInt(value.toString())); break;
            case 1: String[] prodotti = value.toString().split("\\(");
                if (prodotti.length == 1) {
                    riga.setNomeProdotto(prodotti[0]);
                }
                else if (prodotti.length == 2) {
                    riga.setNomeProdotto(prodotti[0]);
                    riga.setNomeSottoprodotti(prodotti[1].substring(0, prodotti[1].length()-1));
                } break;
            case 2: riga.setDescrizione(value.toString()); break;
            case 3: riga.setPrezzo(Float.parseFloat(value.toString())); break;
            case 4: riga.setNomeProduttore(value.toString()); break;
            case 5: String[] categorie = value.toString().split("\n");
                if (categorie.length == 2) {
                    riga.setNomeCategoria(categorie[0]);
                    riga.setNomeSottocategoria(categorie[1].substring(1, categorie[1].length() - 1));
                } break;
            case 6: riga.setCollocazione(((Collocazione) value).getScaffale(), ((Collocazione) value).getCorsia()); break;
            case 7: riga.setRecensione(Recensione.Feedback.valueOf((String) value), value.toString()); break;
            case 8: riga.setImmagine(value.toString()); break;
            case 9: riga.setSelezionato(Boolean.parseBoolean(value.toString())); break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 9;
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (columnIndex) {
            case 0: return "ID prodotto";
            case 1: return "Nome";
            case 2: return "Descrizione";
            case 3: return "Prezzo (€)";
            case 4: return "Produttore";
            case 5: return "Categoria";
            case 6: return "Collocazione";
            case 7: return "Recensione";
            case 8: return "Immagine";
            case 9: return "Seleziona";
        }
        return null;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 8) {
            return ImageIcon.class;
        }

        if (columnIndex == 9) {
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
