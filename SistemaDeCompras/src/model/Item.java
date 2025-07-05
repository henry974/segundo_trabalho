package model;
public class Item {
    private String nomeProduto;
    private int qtd;
    public Item(String s, int q){
        this.nomeProduto=s;qtd=q;
    }
    public int getQuantidade(){
        return this.qtd;
    }
    public String getNome(){
        return this. nomeProduto;
    }
    public void removerItem(int quantidade){
        this.qtd -= quantidade;
    }
    public void aumentarQuantidade(int quantidade){
        this.qtd +=quantidade;
    }

}
