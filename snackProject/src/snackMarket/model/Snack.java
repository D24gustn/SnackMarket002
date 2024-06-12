package snackMarket.model;

public class Snack {
    private static int idCounter = 1;
    private int snackId;
    private String name;
    private String countryOfOrigin;
    private String manufacturer;
    private int price;

    public Snack(String name, String countryOfOrigin, String manufacturer, int price) {
        this.snackId = idCounter++;
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public int getSnackId() {
        return snackId;
    }

    public String getName() {
        return name;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "과자 ID: " + snackId + ", 이름: " + name + ", 원산지: " + countryOfOrigin + ", 제조사: " + manufacturer + ", 가격: " + price;
    }
}
