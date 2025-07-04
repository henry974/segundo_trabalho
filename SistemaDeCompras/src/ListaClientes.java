import java.util.ArrayList;

public class ListaClientes {
    private ArrayList<Cliente> listaClientes;
    
    public ListaClientes(){
        this.listaClientes= new ArrayList<Cliente>();
    }
    public void addCliente(Cliente cliente){
        this.listaClientes.add(cliente);
    }
    public void removeCliente(Cliente cliente){
        this.listaClientes.remove(cliente);
    }
    // public void printClientes(){
    //     for (Cliente cliente : this.listaClientes) {
    //         System.out.println(cliente.getNome());
    //     }
    // }

    // public boolean atenticaConta(String nome, String senha){
    //     for (Cliente cliente : this.listaClientes) {
    //         if(cliente.getNome().equals(nome)){
    //             if (cliente.getSenha().equals(senha)) {
    //                 return true;
    //             }else{
    //                 return false;
    //             }

    //         }

    //     }
    //     System.out.println("nome de usuário não encontrado");
    //     return false;
    // }

    public boolean atenticaConta(String nome, String senha){
    for (Cliente cliente : this.listaClientes) {
        if (cliente.getNome().equals(nome) && cliente.getSenha().equals(senha)) {
            return true;
        }
    }
    System.out.println("nome de usuário ou senha incorretos");
    return false;
    }
    public Cliente getCliente(String nome){
        for (Cliente cliente : this.listaClientes) {
            if(cliente.getNome().equals(nome)){
                return cliente;
            }
        }
        System.out.println("nome nao encontrado");
        return null;
    }
    public void printClientes() {
    System.out.println("=== Lista de clientes ===");
    for (Cliente cliente : this.listaClientes) {
        System.out.println(cliente.getNome() + " - " + cliente.getSenha());
    }
}
}
