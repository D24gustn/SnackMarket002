package snackMarket.model;

public class CartItem {
    private Snack snack;
    private int quantity;

    public CartItem(Snack snack) {
        this.snack = snack;
        this.quantity = 1;
    }

    public Snack getSnack() {
        return snack;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return snack + " (수량: " + quantity + ")";
    }
}
