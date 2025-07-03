import java.util.ArrayList;

public class CarrinhoCompra {
    ArrayList<Item> interiorCarrinho;
    EstoqueProdutos estoqueDisponivel;
    public CarrinhoCompra(EstoqueProdutos ep){
        this.interiorCarrinho=new ArrayList<Item>();
        this.estoqueDisponivel =ep;
    }
    public void adicionaItem(Item i){
        ProdutoEstoque auxProduto = this.estoqueDisponivel.procurarItem(i.getNome());
        if(i.getQuantidade() <= auxProduto.qtd){
            this.interiorCarrinho.add(i);
        }else{
            System.out.println("quantidade insuficiente do produto " + i.getNome());
        }
    }

    public void finalizaCompra(){
        for (Item item : interiorCarrinho) {
            ProdutoEstoque auxProduto = estoqueDisponivel.procurarItem(item.getNome());
            if (auxProduto != null && auxProduto.qtd >= item.getQuantidade()) {
                auxProduto.qtd = auxProduto.qtd - item.getQuantidade();
            }else{
                System.out.println("Quantidade insuficiente do produto " + item.getNome());
            }
        }
    }
    public int calculaTotal(){
        int somador=0;
        for (Item item : this.interiorCarrinho) {
            ProdutoEstoque auxProduto = this.estoqueDisponivel.procurarItem(item.getNome());
            somador = somador + (item.getQuantidade() * auxProduto.price);
        }
        return somador;
    }

    public void removeItem(String s,int quantidade){
        for (Item item : this.interiorCarrinho) {
            if(item.getNome().equals(s)){
                if (quantidade > item.getQuantidade()) {
                    System.out.println("não é possivel tirar "+quantidade+" "+ s + "(s) do carrinho, pois só existem" + item.getQuantidade()+ "");
                    return;
                }else if(quantidade == item.getQuantidade()){
                    interiorCarrinho.remove(item);
                    System.out.println("Item " + s + " removido completamente do carrinho.");
                }else{
                    item.removerItem(quantidade);
                    System.out.println("Foram removidos " + quantidade + " do item " + s + " do carrinho.");
                }
                return;
            }

        }
        System.out.println("Item " + s + " não encontrado no carrinho.");
    }
    public void listarItens(){
        for (Item item : this.interiorCarrinho) {
            System.out.println(item.getNome() + " - Quantidade: " + item.getQuantidade());
        }
    }
    
}
