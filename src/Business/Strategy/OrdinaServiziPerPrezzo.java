package Business.Strategy;

import Model.Composite.Prodotto;
import Model.Servizio;

import java.util.Comparator;
import java.util.List;

public class OrdinaServiziPerPrezzo implements IOrdinaServizi{

    @Override
    public void ordina(List<Servizio> servizi) {

        servizi.sort(new Comparator<Servizio>() {
            @Override
            public int compare(Servizio s1, Servizio s2) {
                if (s1.getPrezzo() == s2.getPrezzo()) {
                    return 0;
                } else if (s1.getPrezzo() > s2.getPrezzo()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }
}
