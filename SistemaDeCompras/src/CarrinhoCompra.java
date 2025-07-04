import java.util.ArrayList;

public class CarrinhoCompra {
    private ArrayList<Item> interiorCarrinho;
    private EstoqueProdutos estoqueDisponivel;
    
    public CarrinhoCompra(EstoqueProdutos ep){
        this.interiorCarrinho=new ArrayList<Item>();
        this.estoqueDisponivel =ep;
    }
    public boolean adicionaItem(Item i){
        ProdutoEstoque auxProduto = this.estoqueDisponivel.procurarItem(i.getNome());
        Item auxItem=this.procuraItemNoCarrinho(i.getNome());
        if(auxItem != null){
            auxItem.aumentarQuantidade(i.getQuantidade());
            return true;
        }
        if(i.getQuantidade() <= auxProduto.getQuantidade()){
            this.interiorCarrinho.add(i);
            return true;
        }else{
            System.out.println("quantidade insuficiente do produto " + i.getNome());
            return false;
        }
    }

    public boolean finalizaCompra(){
        for (Item item : interiorCarrinho) {
            ProdutoEstoque auxProduto = estoqueDisponivel.procurarItem(item.getNome());
            if (auxProduto != null && auxProduto.getQuantidade() >= item.getQuantidade()) {
                estoqueDisponivel.removerProduto(item.getNome(),item.getQuantidade());  
            }else{
                System.out.println("Quantidade insuficiente do produto " + item.getNome());
                System.out.println("Quantidade em estoque: "+auxProduto.getQuantidade()+" Quantidade no carrinho: "+item.getQuantidade());
                return false;
            }
        }
        return true;
    }
    public double calculaTotal(){
        double somador=0;
        for (Item item : this.interiorCarrinho) {
            ProdutoEstoque auxProduto = this.estoqueDisponivel.procurarItem(item.getNome());
            somador = somador + (item.getQuantidade() * auxProduto.getPrice());
        }
        return somador;
    }
    //retorna -1 para erros, 0 para remoção total e 1 para remoção parcial
    public int removeItem(String s,int quantidade){ // mudar para aproveitar a procuraItemNoCarrinho
        for (Item item : this.interiorCarrinho) {
            if(item.getNome().equals(s)){
                if (quantidade > item.getQuantidade()) {
                    System.out.println("não é possivel tirar "+quantidade+" "+ s + "(s) do carrinho, pois só existem" + item.getQuantidade()+ "");
                    return -1;
                }else if(quantidade == item.getQuantidade()){
                    interiorCarrinho.remove(item);
                    System.out.println("Item " + s + " removido completamente do carrinho.");
                    return 0;
                }else{
                    item.removerItem(quantidade);
                    System.out.println("Foram removidos " + quantidade + " do item " + s + " do carrinho.");
                    return 1;
                }
                
            }

        }
        System.out.println("Item " + s + " não encontrado no carrinho.");
        return -1;
    }
    public void listarItensDoCarrinho(){
        for (Item item : this.interiorCarrinho) {
            System.out.println(item.getNome() + " - Quantidade: " + item.getQuantidade());
        }
    }
    public void listarItensEmEstoque(){
        estoqueDisponivel.listarItensEmEstoque();
    }
    public ProdutoEstoque procurarItemEmEstoque(String s){  
        return estoqueDisponivel.procurarItem(s);
    }
    public Item procuraItemNoCarrinho(String nome){
        for (Item item : interiorCarrinho) {
            if (item.getNome().equals(nome)) {
                    return item;
            }
        }
        return null;
    }
}
