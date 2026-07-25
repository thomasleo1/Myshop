package Business.Strategy;

import Model.Composite.IProdotto;
import Model.Composite.Prodotto;

import java.util.List;

public interface IOrdinaProdotti {

    void ordina(List<IProdotto> prodotti);
}
