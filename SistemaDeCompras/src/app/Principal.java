package app;

import java.util.Scanner;
import model.Cliente;
import model.Item;
import model.ProdutoEstoque;
import service.EstoqueProdutos;
import model.CarrinhoCompra;
import service.ListaClientes;

import java.util.Scanner;


public class Principal {
    
    public static void main(String[] args) {
        ListaClientes lista_clientes = new ListaClientes(); // armazena os clientes, reseta a cada execução mas uma melhoria seria salvar em arquivos
        EstoqueProdutos estoque = new EstoqueProdutos();
        boolean rodandoLogin =true,rodandoPrograma=true;
        Scanner scanner = new Scanner(System.in);
        Cliente clienteAtual;
        int option;
        while (rodandoLogin) {
            
            menuLoginOuCadastro();
            option = scanner.nextInt();
            scanner.nextLine();
            clienteAtual=processEntrar(option,lista_clientes,estoque,scanner);
            if (clienteAtual == null) {
                continue;
            }
            if(clienteAtual.getNome().equals("sair")){
                rodandoLogin = false;
                continue;
            }
            rodandoPrograma=true;
            while (rodandoPrograma) {
                if(clienteAtual.isAdmin() == false){
                    menuProgramaCliente();
                    option = scanner.nextInt();
                    scanner.nextLine();
                    switch (processOptions(option, clienteAtual,scanner)) {
                        case -1:
                            rodandoPrograma = false;
                            continue;
                        default:
                            continue;
                    }
                }else{
                    menuAdmin();
                    option = scanner.nextInt();
                    scanner.nextLine();
                    switch (processaOptionsAdmin(option, clienteAtual, estoque,scanner)) {
                        case -1:
                            rodandoPrograma=false;
                            continue;
                        default:
                            continue;
                    }
                }

            }
            
        }


        scanner.close();
    }

    public static void menuLoginOuCadastro(){
        System.out.println("====== ENTRAR ======");
        System.out.println("1 - JA TENHO CONTA");
        System.out.println("2 - ME CADASTRAR");
        System.out.println("3 - ENTRAR COMO ADMIN");
        System.out.println("4 - SAIR");
    }
    public static Cliente processEntrar(int option,ListaClientes lista_clientes,EstoqueProdutos estoque,Scanner scanner){
        String nome;
        String senha;
        Cliente newCliente;
        switch (option) {
            case 1:
                
                System.out.println("===== LOGIN =====");
                System.out.print("Nome de usuário: ");
                nome = scanner.nextLine();
                System.out.print("Senha: ");
                senha = scanner.nextLine();
                if(lista_clientes.atenticaConta(nome, senha) == false){
                    System.out.println("login inválido");
                    return null;
                }else{
                    return lista_clientes.getCliente(nome);}
            case 2:
                System.out.println("===== CADASTRO =====");
                System.out.print("Nome de usuário: ");
                nome = scanner.nextLine();
                System.out.print("Senha: ");
                senha = scanner.nextLine();
                newCliente = new Cliente(nome, senha, estoque);
                lista_clientes.addCliente(newCliente);
                System.out.println("Bem Vindo, " + newCliente.getNome());
                return lista_clientes.getCliente(nome);
            case 3:
                System.out.println("===== LOGIN COMO ADMIN =====");
                System.out.print("Nome de usuário: ");
                nome = scanner.nextLine();
                System.out.print("Senha: ");
                senha = scanner.nextLine();
                newCliente = new Cliente(nome, senha, estoque);
                if (newCliente.isAdmin() == false){
                    System.out.println("login inválido");
                    return null;
                }else{
                lista_clientes.addCliente(newCliente);
                System.out.println("Bem Vindo, " + newCliente.getNome());
                return lista_clientes.getCliente(nome);}
            case 4:
                System.out.println("Encerrando programa...");
                newCliente = new Cliente("sair", "000'", estoque);
                return newCliente;
            default:
                System.out.println("Entrada invalida.");
                return null;
        }
    }

    public static void menuProgramaCliente(){
        System.out.println("====== OPÇÕES ======");
        System.out.println("1 - ADICIONAR ITENS AO CARRINHO");
        System.out.println("2 - REMOVER ITENS DO CARRINHO");
        System.out.println("3 - VER PREÇO TOTAL DO CARRINHO");
        System.out.println("4 - LISTAR ITENS DO CARRINHO");
        System.out.println("5 - FINALIZAR COMPRA");
        System.out.println("6 - SAIR");
    }

    // public static void menuItensDisponiveis(EstoqueProdutos estoqueProdutos){
    //     estoqueProdutos.listarItensEmEstoque();
    // }
    //retorna -1 para finalização de compras/sair, 0 para erros e 1 para sucesso
    public static int processOptions(int option,Cliente cliente,Scanner scanner){
        String nome;
        int quantidade;
        
        switch (option) {
            case 1:
                cliente.listarItensEmEstoque();
                System.out.println("digite o nome do item que deseja adicionar: ");
                nome = scanner.nextLine();
                if(cliente.procurarItemEmEstoque(nome) == null){
                    System.out.println("nome inválido");
                    return 0;
                }
                System.out.println("digite a quantidade que deseja: ");
                quantidade = scanner.nextInt();
                scanner.nextLine();
                if (cliente.adicionaItem(new Item(nome, quantidade)) == false) {
                    System.out.println("quantidade maior que o estoque.");
                    return 0;
                }
                return 1;
                
            case 2:
                cliente.listarItensDoCarrinho();
                System.out.println("digite o nome do item que deseja remover: ");
                nome = scanner.nextLine();
                if(cliente.procuraItemNoCarrinho(nome) == null){
                    System.out.println("nome inválido");
                    return 0;
                }
                System.out.println("digite a quantidade que deseja remover: ");
                quantidade = scanner.nextInt();
                scanner.nextLine();
                if(cliente.removeItem(nome, quantidade) == -1){
                    return 0;
                }
                return 1;
                
            case 3:
                System.out.println(cliente.calculaTotal());
                return 1;
            case 4:
                cliente.listarItensDoCarrinho();
                return 1;
            case 5:
                if(cliente.finalizaCompra() == false){
                    System.out.println("Não foi possivel finalizar as compras.");
                    return 0;
                }else{
                    return -1;
                }
            case 6:
                return -1;
                
            default:
            System.out.println("entrada inválida");
                return 0;
                
        }
    }
    public static void menuAdmin(){
        System.out.println("====== OPÇÕES DE ADMIN ======");
        System.out.println("1 - ADICIONAR ITENS AO ESTOQUE");
        System.out.println("2 - REMOVER ITENS DO ESTOQUE");
        System.out.println("3 - VER ESTOQUE");
        System.out.println("4 - SAIR");
    }
    // retorna -1 para sair de loop, 0 para erros e 1 para sucesso
    public static int processaOptionsAdmin(int option,Cliente cliente, EstoqueProdutos estoqueProdutos,Scanner scanner){
        String nome;
        int quantidade;
        double price;
        switch (option) {
            case 1:
                System.out.println("digite o nome do item que deseja adicionar: ");
                nome = scanner.nextLine();
                System.out.println("digite a quantidade que deseja adicionar: ");
                quantidade = scanner.nextInt();
                scanner.nextLine();
                if(estoqueProdutos.procurarItem(nome) != null){
                    estoqueProdutos.adicionaProduto(new ProdutoEstoque(nome,0,quantidade));
                    return 1;
                }else{
                System.out.println("digite o preço do produto: ");
                price = scanner.nextDouble();
                scanner.nextLine();
                estoqueProdutos.adicionaProduto(new ProdutoEstoque(nome,price,quantidade));
                return 1;}
            case 2:
                System.out.println("digite o nome do item que deseja remover: ");
                nome = scanner.nextLine();
                if(estoqueProdutos.procurarItem(nome) == null){
                    System.out.println("esse produto não está em estoque");
                    return 0;
                }
                System.out.println("digite a quantidade que deseja retirar: ");
                quantidade = scanner.nextInt();
                scanner.nextLine();
                if(estoqueProdutos.removerProduto(nome, quantidade) == -1){
                    return 0;
                }
                return 1;
            case 3:
                estoqueProdutos.listarItensEmEstoque();
                return 1;
            case 4:
                return -1;

            default:
                return 0;
                
        }
    }
}

