package Model.Composite;

import Model.ICategoria;
import Model.Collocazione;
import Model.Produttore;
import Model.Recensione;

import java.util.List;

public interface IProdotto {
    Float getPrezzo();
    int getId();
    String getNome();
    String getDescrizione();
    Produttore getProduttore();
    Collocazione getCollocazione();
    Recensione getRecensione();
    ICategoria getCategoria();
    ICategoria getSottocategoria();
    String getImmagine();
    List<Prodotto> getSottoprodotti();
    void setPrezzo(Float prezzo);

}
