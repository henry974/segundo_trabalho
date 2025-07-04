public class ProdutoEstoque {
    private String productName;
    private double price;
    private int qtd;
    public ProdutoEstoque(String s, double p, int q){
        this.productName=s;this.price=p;this.qtd=q;
    }
    public String getNome(){
        return this.productName;
    }
    public double getPrice(){
        return this.price;
    }
    public int getQuantidade(){
        return this.qtd;
    }
    public void tirarDeEstoque(int quantidade){
        this.qtd -= quantidade;
    }
    public void aumentarQuantidade(int quantidade){
        this.qtd +=quantidade;
    }
}
