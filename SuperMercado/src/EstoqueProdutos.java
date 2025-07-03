import java.util.ArrayList;

public class EstoqueProdutos {
    private ArrayList<ProdutoEstoque> produtosEmEstoque;

    public EstoqueProdutos(){
        produtosEmEstoque = new ArrayList<ProdutoEstoque>();
    }
    public void adicionaProduto(ProdutoEstoque pe){
        produtosEmEstoque.add(pe);
    }
    public ProdutoEstoque procurarItem(String s){  
        for (ProdutoEstoque produtoEstoque : produtosEmEstoque) {
            if (produtoEstoque.productName.equals(s)) {
                return produtoEstoque;
            }
        }
        return null;
    }
}

