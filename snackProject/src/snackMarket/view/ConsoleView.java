package snackMarket.view;

import snackMarket.model.Cart;
import snackMarket.model.SnackStorage;
import snackMarket.model.Customer;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    public void displayWelcome() {
        System.out.println("Welcome to Hyunsu Snack Market!");
    }

    public void inputCustomerInfo(Customer customer) {
        System.out.print("이름: ");
        customer.setName(scanner.nextLine());
        System.out.print("이메일: ");
        customer.setEmail(scanner.nextLine());
    }

    public void displaySnackInfo(SnackStorage storage) {
        if (storage.isEmpty()) {
            System.out.println("과자 정보가 없습니다.");
        } else {
            storage.getSnackList().forEach(System.out::println);
        }
    }

    public int selectMenu(String[] menuList) {
        for (String menu : menuList) {
            System.out.println(menu);
        }
        System.out.print("메뉴 선택: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayCart(Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("장바구니가 비었습니다.");
        } else {
            cart.getItemList().forEach(System.out::println);
        }
    }

    public int selectSnackId(SnackStorage storage) {
        System.out.print("과자 ID 선택: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int selectSnackId(Cart cart) {
        System.out.print("과자 ID 선택: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public boolean askConfirm(String message, String confirmation) {
        System.out.print(message);
        return scanner.nextLine().equals(confirmation);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int inputQuantity(int min, int max) {
        int quantity;
        do {
            System.out.print("수량 (" + min + " ~ " + max + "): ");
            quantity = Integer.parseInt(scanner.nextLine());
        } while (quantity < min || quantity > max);
        return quantity;
    }

    public void inputDeliveryInfo(Customer customer) {
        System.out.print("주소: ");
        customer.setAddress(scanner.nextLine());
        System.out.print("전화번호: ");
        customer.setPhoneNumber(scanner.nextLine());
        System.out.print("배달 메모: ");
        customer.setDeliveryNotes(scanner.nextLine());
    }

    public void displayOrder(Cart cart, Customer customer) {
        System.out.println("주문 정보");
        System.out.println("고객: " + customer.getName() + ", " + customer.getEmail());
        System.out.println("주소: " + customer.getAddress());
        System.out.println("전화번호: " + customer.getPhoneNumber());
        System.out.println("배달 메모: " + customer.getDeliveryNotes());
        System.out.println("주문한 과자들:");
        cart.getItemList().forEach(System.out::println);
    }

    public String inputString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public int readNumber(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }
}
