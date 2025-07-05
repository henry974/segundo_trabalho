package service;
import java.util.ArrayList;

import model.ProdutoEstoque;

public class EstoqueProdutos {
    private ArrayList<ProdutoEstoque> produtosEmEstoque;

    public EstoqueProdutos(){
        produtosEmEstoque = new ArrayList<ProdutoEstoque>();
    }
    public boolean adicionaProduto(ProdutoEstoque pe){ 
        for (ProdutoEstoque produtoEstoque : produtosEmEstoque) {
            if (produtoEstoque.getNome().equals(pe.getNome())) {
                produtoEstoque.aumentarQuantidade(pe.getQuantidade());
                return false;
            }
        }
        produtosEmEstoque.add(pe);
        return true;
    }
    //-1 :erro,0:remoção completa, 1: remoção parcial
    public int removerProduto(String nome,int quantidade){
        ProdutoEstoque auxProduto = this.procurarItem(nome);
        if(auxProduto == null){
            System.out.println("Item " + nome + " não encontrado.");
            return -1;
        }
        if(auxProduto.getQuantidade() < quantidade){
            System.out.println("não é possivel tirar "+quantidade+" "+ nome + "(s), pois só existem" + auxProduto.getQuantidade()+ "");
            return -1;
        }
        if(auxProduto.getQuantidade() == quantidade){
            produtosEmEstoque.remove(auxProduto);
            System.out.println("Item " + nome + " removido completamente.");
            return 0;
        }else{
            auxProduto.tirarDeEstoque(quantidade);
            System.out.println("Foram removidos " + quantidade + " do item " + nome + ".");
            return 1;
        }

        
    }
    public ProdutoEstoque procurarItem(String s){  
        for (ProdutoEstoque produtoEstoque : produtosEmEstoque) {
            if (produtoEstoque.getNome().equals(s)) {
                return produtoEstoque;
            }
        }
        return null;
    }
    public void listarItensEmEstoque(){
        for (ProdutoEstoque produto : produtosEmEstoque) {
            System.out.println(produto.getNome() + " - Quantidade: " + produto.getQuantidade());
        }
    }
}

