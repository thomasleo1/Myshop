package Business.Strategy;

import Model.Composite.Prodotto;
import Model.Servizio;

import java.util.List;

public interface IOrdinaServizi {

    void ordina(List<Servizio> servizi);
}
