public class Cliente extends User {
    private CarrinhoCompra carrinho;
    private boolean isAdmin;
    public Cliente(String n, String s,EstoqueProdutos ep){
        this.nome=n;this.senha=s;carrinho= new CarrinhoCompra(ep);
        if(nome.equals("admin") && senha.equals("amocalopsitas")){
            this.isAdmin =true;
        }else{
            this.isAdmin=false;
        }
    }
    public void finalizaCompra(){
        carrinho.finalizaCompra();
    }
    public boolean adicionaItem(Item i){
        return carrinho.adicionaItem(i);
    }
    public int calculaTotal(){
        return carrinho.calculaTotal();
    }
    public int removeItem(String s,int quantidade){
        return carrinho.removeItem(s, quantidade);
    }
    public void listarItensDoCarrinho(){
        carrinho.listarItensDoCarrinho();
    }
    public void listarItensEmEstoque(){
        carrinho.listarItensEmEstoque();
    }
    public boolean isAdmin(){
        return isAdmin;
    }
    public String getSenha(){
        return senha;
    }
    public CarrinhoCompra getCarrinhoCompra(){
        return carrinho;
    }
    public ProdutoEstoque procurarItemEmEstoque(String s){  
        return carrinho.procurarItemEmEstoque(s);
    }
    public Item procuraItemNoCarrinho(String nome){
        return carrinho.procuraItemNoCarrinho(nome);
    }
}
