package Model;

public class CategoriaProdotto implements ICategoria {

    private int idCategoriaProdotto;
    private String nome;

    public CategoriaProdotto() {
    }


    public CategoriaProdotto(int idCategoria, String nome) {
        this.idCategoriaProdotto = idCategoria;
        this.nome = nome;
    }

    public CategoriaProdotto(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return idCategoriaProdotto;
    }

    public void setIdCategoriaProdotto(int idCategoriaProdotto) {
        this.idCategoriaProdotto = idCategoriaProdotto;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return this.nome;
    }

}
