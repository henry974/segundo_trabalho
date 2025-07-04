public class ProdutoEstoque {
    private String productName;
    private int price;
    private int qtd;
    public ProdutoEstoque(String s, int p, int q){
        this.productName=s;this.price=p;this.qtd=q;
    }
    public String getNome(){
        return this.productName;
    }
    public int getPrice(){
        return this.price;
    }
    public int getQuantidade(){
        return this.qtd;
    }
    public void tirarDeEstoque(int quantidade){
        this.qtd -= quantidade;
    }
}
