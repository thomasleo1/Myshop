package Model;

public class SottoCategoria extends CategoriaProdotto implements ICategoria  {

    private int idSottoCategoria;

    public SottoCategoria() {
    }

    public SottoCategoria(int idCategoria, String nome, int idSottoCategoria) {
        super(idCategoria, nome);
        this.idSottoCategoria = idSottoCategoria;
    }

    public int getIdSottoCategoria() {
        return idSottoCategoria;
    }

    public void setIdSottoCategoria(int idSottoCategoria) {
        this.idSottoCategoria = idSottoCategoria;
    }
}
