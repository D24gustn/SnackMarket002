package snackMarket;

import java.io.IOException;
import snackMarket.controller.SnackMarketController;
import snackMarket.model.SnackStorage;
import snackMarket.model.Cart;
import snackMarket.view.ConsoleView;

public class SnackMarket {
    public static void main(String[] args) throws IOException {
        // 모델 생성
        SnackStorage snackStorage = new SnackStorage();
        Cart cart = new Cart();
        
        // 뷰 생성
        ConsoleView view = new ConsoleView();
        
        // 컨트롤러 생성 (모델, 뷰)
        SnackMarketController controller = new SnackMarketController(snackStorage, cart, view);
        controller.start();
    }
}