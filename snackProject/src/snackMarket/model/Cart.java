package snackMarket.model;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> itemList = new ArrayList<>();

    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    public ArrayList<CartItem> getItemList() {
        return itemList;
    }

    public int getNumItems() {
        return itemList.size();
    }

    public String getItemInfo(int index) {
        return itemList.get(index).toString();
    }

    public void addItem(Snack snack) {
        CartItem item = getCartItem(snack);
        if (item == null) {
            itemList.add(new CartItem(snack));
        } else {
            item.addQuantity(1);
        }
    }

    private CartItem getCartItem(Snack snack) {
        for (CartItem item : itemList) {
            if (item.getSnack().getSnackId() == snack.getSnackId()) return item;
        }
        return null;
    }

    private CartItem getCartItem(int snackId) {
        for (CartItem item : itemList) {
            if (item.getSnack().getSnackId() == snackId) return item;
        }
        return null;
    }

    public void resetCart() {
        itemList.clear();
    }

    public boolean isValidItem(int snackId) {
        for (CartItem item : itemList) {
            if (item.getSnack().getSnackId() == snackId) return true;
        }
        return false;
    }

    public void deleteItem(int snackId) {
        CartItem item = getCartItem(snackId);
        if (item != null) {
            itemList.remove(item);
        }
    }

    public void updateQuantity(int snackId, int quantity) {
        CartItem item = getCartItem(snackId);
        if (item != null) {
            item.setQuantity(quantity);
        }
    }
}
