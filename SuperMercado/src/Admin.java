public class Admin extends User {
    
    public Admin(String n, String s){
        this.nome=n;this.senha=s;
    }
    public boolean isAdmin(){
        return true;
    }
}
