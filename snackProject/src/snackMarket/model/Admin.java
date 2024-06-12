package snackMarket.model;

public class Admin extends User {

    private String id = "gustn";
    private String password = "0917";
    
    public boolean login(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }
}
