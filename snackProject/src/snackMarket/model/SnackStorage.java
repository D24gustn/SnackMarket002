package snackMarket.model;

import java.io.*;
import java.util.ArrayList;

public class SnackStorage {
    private ArrayList<Snack> snackList;
    private boolean saved;

    public SnackStorage() {
        snackList = new ArrayList<>();
        loadSnackList();
    }

    public void addSnack(String name, String countryOfOrigin, String manufacturer, int price) {
        Snack snack = new Snack(name, countryOfOrigin, manufacturer, price);
        snackList.add(snack);
        saved = false;
    }

    public void deleteItem(int snackId) {
        snackList.removeIf(snack -> snack.getSnackId() == snackId);
        saved = false;
    }

    public Snack getSnackById(int snackId) {
        for (Snack snack : snackList) {
            if (snack.getSnackId() == snackId) return snack;
        }
        return null;
    }

    public boolean isEmpty() {
        return snackList.isEmpty();
    }

    public int getMaxQuantity() {
        return 100;
    }

    public void loadSnackList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("snackList.dat"))) {
            snackList = (ArrayList<Snack>) ois.readObject();
            saved = true;
        } catch (IOException | ClassNotFoundException e) {
            snackList = new ArrayList<>();
        }
    }

    public void saveSnackList2File() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("snackList.dat"))) {
            oos.writeObject(snackList);
            saved = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSaved() {
        return saved;
    }

    public ArrayList<Snack> getSnackList() {
        return snackList;
    }
}
