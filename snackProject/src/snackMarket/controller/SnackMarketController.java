package snackMarket.controller;

import snackMarket.model.Admin;
import snackMarket.model.SnackStorage;
import snackMarket.model.Cart;
import snackMarket.model.Customer;
import snackMarket.view.ConsoleView;

public class SnackMarketController {

    ConsoleView view;
    SnackStorage mSnackStorage;
    Cart mCart;
    Customer mCustomer;
    Admin mAdmin;
    
    String[] menuList = {
        "0. 종료",
        "1. 과자 정보 보기",
        "2. 장바구니 보기",
        "3. 장바구니에 과자 추가",
        "4. 장바구니 과자 삭제",
        "5. 장바구니 과자 수량 변경",
        "6. 장바구니 비우기",
        "7. 주문",
        "8. 관리자 모드"
    };
    
    String[] adminMenuList = {
        "0. 종료",
        "1. 과자 정보 추가",
        "2. 과자 정보 삭제",
        "3. 과자 정보 보기",
        "4. 과자 정보 파일 저장"
    };
    
    public SnackMarketController(SnackStorage snackStorage, Cart cart, ConsoleView view) {
        this.view = view;
        mSnackStorage = snackStorage;
        mCart = cart;
        mAdmin = new Admin();
    }

    public void start() {
        welcome();
        registerCustomerInfo();
        
        int menu;
        
        do {
            menu = view.selectMenu(menuList);
            
            switch (menu) {
                case 1 -> viewSnackInfo();
                case 2 -> viewCart();
                case 3 -> addSnack2Cart();
                case 4 -> deleteSnackInCart();
                case 5 -> updateSnackInCart();
                case 6 -> resetCart();
                case 7 -> order();
                case 8 -> adminMode();
                case 0 -> end();
                default -> view.showMessage("잘못된 메뉴 번호입니다.");
            }
        } while (menu != 0);
    }

    private void welcome() {
        view.displayWelcome();
    }
    
    private void registerCustomerInfo() {
        mCustomer = new Customer();
        view.inputCustomerInfo(mCustomer);
    }

    private void viewSnackInfo() {
        view.displaySnackInfo(mSnackStorage);
    }
    
    private void viewCart() {
        view.displayCart(mCart);
    }

    private void addSnack2Cart() {
        view.displaySnackInfo(mSnackStorage);
        int snackId = view.selectSnackId(mSnackStorage);
        mCart.addItem(mSnackStorage.getSnackById(snackId));
        view.showMessage(">>장바구니에 과자를 추가하였습니다.");
    }
    
    private void deleteSnackInCart() {
        view.displayCart(mCart);
        if (!mCart.isEmpty()) {
            int snackId = view.selectSnackId(mCart);
            if (view.askConfirm(">> 해당 과자를 삭제하려면 yes를 입력하세요 : ", "yes")) {
                mCart.deleteItem(snackId);
                view.showMessage(">> 해당 과자를 삭제했습니다.");
            }
        }
    }
    
    private void updateSnackInCart() {
        view.displayCart(mCart);
        if (!mCart.isEmpty()) {
            int snackId = view.selectSnackId(mCart);
            int quantity = view.inputQuantity(0, mSnackStorage.getMaxQuantity());
            mCart.updateQuantity(snackId, quantity);
        }
    }

    private void resetCart() {
        view.displayCart(mCart);
        
        if (!mCart.isEmpty()) {
            if (view.askConfirm(">> 장바구니를 비우려면 yes를 입력하세요 : ", "yes")) {
                mCart.resetCart();
                view.showMessage(">> 장바구니를 비웠습니다.");
            }
        }
    }
    
    private void order() {
        getDeliveryInfo();
        viewOrderInfo();
        if (view.askConfirm("진짜 주문하려면 yes를 입력하세요 : ", "yes")) {
            mCart.resetCart();
        }
    }
    
    private void getDeliveryInfo() {
        view.inputDeliveryInfo(mCustomer);
    }

    private void viewOrderInfo() {
        view.displayOrder(mCart, mCustomer);
    }
    
    private void adminMode() {
        if (!authenticateAdmin()) {
            view.showMessage("관리자 모드로 전환할 수 없습니다.");
            return;
        }
        
        int menu;
        do {
            menu = view.selectMenu(adminMenuList);
            
            switch (menu) {
                case 1 -> addSnack2Storage();
                case 2 -> deleteSnackInStorage();
                case 3 -> viewSnackInfo();
                case 4 -> saveSnackList2File();
                case 0 -> adminEnd();
                default -> view.showMessage("잘못된 메뉴 번호입니다.");
            }
        } while (menu != 0);
    }

    private boolean authenticateAdmin() {
        view.showMessage("관리자 모드 진입을 위한 관리자 인증");
        String id = view.inputString("관리자 ID : ");
        String password = view.inputString("관리자 password : ");
        return mAdmin.login(id, password);
    }
    
    private void addSnack2Storage() {
        view.showMessage("새로운 과자를 추가합니다.");
        mSnackStorage.addSnack(view.inputString("과자 이름 : "),
                view.inputString("어느 나라 : "), view.inputString("만든 곳 : "),
                view.readNumber("가격 : "));
    }
    
    private void deleteSnackInStorage() {
        if (mSnackStorage.isEmpty()) {
            view.showMessage("과자 창고에 과자가 없습니다.");
            return;
        }
        viewSnackInfo();
        int snackId = view.selectSnackId(mSnackStorage);
        if (view.askConfirm(">> 해당 과자를 삭제하려면 yes를 입력하세요 : ", "yes")) {
            mSnackStorage.deleteItem(snackId);
            view.showMessage(">> 해당 과자를 삭제했습니다.");
        }
    }

    private void saveSnackList2File() {
        if (mSnackStorage.isSaved()) {
            view.showMessage("과자 정보가 파일과 동일합니다.");
        } else {
            mSnackStorage.saveSnackList2File();
            view.showMessage("과자 정보를 저장하였습니다.");
        }
    }
    
    private void adminEnd() {
        if (!mSnackStorage.isSaved()) {
            view.showMessage("수정한 과자정보가 파일로 저장되지 않았습니다.");
            if (view.askConfirm("과자정보를 저장하려면 yes를 입력하세요 : ", "yes")) {
                mSnackStorage.saveSnackList2File();
            }
        }
        view.showMessage("관리자 모드가 종료되었습니다.\n");
    }
    
    private void end() {
        view.showMessage(">> hyunsu Snack Market을 종료합니다.");
    }
}
