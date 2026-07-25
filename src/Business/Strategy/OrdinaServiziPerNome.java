package Business.Strategy;

import Model.Servizio;

import java.util.Comparator;
import java.util.List;

public class OrdinaServiziPerNome implements IOrdinaServizi{

    @Override
    public void ordina(List<Servizio> servizi) {

        servizi.sort(new Comparator<Servizio>() {
            @Override
            public int compare(Servizio s1, Servizio s2) {
                return s1.getNome().compareTo(s2.getNome());

            }
        });
    }
}
