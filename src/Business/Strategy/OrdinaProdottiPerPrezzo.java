package Business.Strategy;

import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import java.util.Comparator;
import java.util.List;

public class OrdinaProdottiPerPrezzo implements IOrdinaProdotti{

    @Override
    public void ordina(List<IProdotto> prodotti) {

        prodotti.sort(new Comparator<IProdotto>() {
            @Override
            public int compare(IProdotto p1, IProdotto p2) {
                if (p1.getPrezzo() == p2.getPrezzo()) {
                    return 0;
                } else if (p1.getPrezzo() > p2.getPrezzo()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }
}
