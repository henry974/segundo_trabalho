public class Cliente extends User {
   
    public Cliente(String n, String s){
        this.nome=n;this.senha=s;
    }
    
    public boolean isAdmin(){
        return false;
    }
    public String getSenha(){
        return senha;
    }
}
