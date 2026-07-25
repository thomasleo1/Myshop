package Business.Strategy;

import Model.Composite.IProdotto;
import Model.Composite.Prodotto;

import java.util.Comparator;
import java.util.List;

public class OrdinaProdottiPerNome implements IOrdinaProdotti{

    @Override
    public void ordina(List<IProdotto> prodotti) {

        prodotti.sort(new Comparator<IProdotto>() {
            @Override
            public int compare(IProdotto p1, IProdotto p2) {
                return p1.getNome().compareTo(p2.getNome());

            }
        });
    }
}
