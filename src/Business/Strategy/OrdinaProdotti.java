package Business.Strategy;

import Model.Composite.IProdotto;
import Model.Composite.Prodotto;

import java.util.List;

public class OrdinaProdotti {

    private List<IProdotto> prodotti;

    private IOrdinaProdotti ordinaProdotti;

    public OrdinaProdotti(List<IProdotto> prodotti) {
        this.prodotti = prodotti;
    }

    public void setOrdinaProdotti(IOrdinaProdotti ordinaProdotti) {
        this.ordinaProdotti = ordinaProdotti;
    }

    public void ordina() {
        ordinaProdotti.ordina(prodotti);
    }

    public List<IProdotto> getProdotti() {
        return prodotti;
    }
}
