import java.util.List;
import java.util.Map;

class KioskController {
    private MenuManager menuManager;
    private KioskView kioskView;

    public KioskController(MenuManager menuManager, KioskView kioskView) {
        this.menuManager = menuManager;
        this.kioskView = kioskView;
    }

    public void run() {
        while (true) {
            kioskView.displayMenu();
            String choice = kioskView.getUserChoice();
            switch (choice) {
                case "list":
                    displayMenuList();
                    break;
                case "select":
                    selectMenu();
                    break;
                case "cancel":
                    cancelMenu();
                    break;
                case "payment":
                    pay();
                    break;
                case "exit":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 명령어가 아닙니다.");
            }
        }
    }

    private void displayMenuList() {
        Map<String, List<Menu>> menuCategories = menuManager.getMenuCategories();
        kioskView.displayMenuList(menuCategories);
    }

    private void selectMenu() {
        Map<String, List<Menu>> menuCategories = menuManager.getMenuCategories();
        List<Menu> selectedMenu = menuManager.getSelectedMenu();

        while (true) {
            kioskView.displayMenuList(menuCategories);
            String category = kioskView.getCategoryChoice();

            if (category.equals("exit")) {
                break;
            }

            if (!category.equals("drink") && !category.equals("food")) {
                System.out.println("잘못된 카테고리입니다.");
                continue;
            }

            List<Menu> menuList;
            if (category.equals("drink")) {
                menuList = menuCategories.get("음료");
            } else {
                menuList = menuCategories.get("음식");
            }

            if (menuList == null || menuList.isEmpty()) {
                System.out.println("해당 카테고리에 메뉴가 없습니다.");
                continue;
            }

            String choice = kioskView.getMenuChoice();

            if (choice.equals("exit")) {
                break;
            }

            int menuIdx;
            try {
                menuIdx = Integer.parseInt(choice) - 1;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }

            if (menuIdx < 0 || menuIdx >= menuList.size()) {
                System.out.println("잘못된 메뉴 번호입니다.");
                continue;
            }

            Menu selected = menuList.get(menuIdx);
            selectedMenu.add(selected);
            System.out.println(selected.getName() + "이(가) 선택되었습니다.");
        }

        if (!selectedMenu.isEmpty()) {
            kioskView.displaySelectedMenu(selectedMenu);
        }
    }

    private void cancelMenu() {
        List<Menu> selectedMenu = menuManager.getSelectedMenu();
        if (selectedMenu.isEmpty()) {
            System.out.println("선택된 메뉴가 없습니다.");
            return;
        }

        kioskView.displaySelectedMenu(selectedMenu);
        int menuIdx = kioskView.getMenuIndex() - 1;

        if (menuIdx < 0 || menuIdx >= selectedMenu.size()) {
            System.out.println("잘못된 메뉴 번호입니다.");
            return;
        }

        menuManager.cancelMenu(menuIdx);
        System.out.println("메뉴가 취소되었습니다.");
    }

    private void pay() {
        List<Menu> selectedMenu = menuManager.getSelectedMenu();
        if (selectedMenu.isEmpty()) {
            System.out.println("선택된 메뉴가 없습니다.");
            return;
        }

        int totalPrice = calculatePrice(selectedMenu);
        kioskView.displayTotalPrice(totalPrice);
        kioskView.displayPaymentComplete();
        selectedMenu.clear();
    }

    private int calculatePrice(List<Menu> selectedMenu) {
        int totalPrice = 0;
        for (Menu menu : selectedMenu) {
            totalPrice += menu.getPrice();
        }
        return totalPrice;
    }

    public static void main(String[] args) {
        MenuManager menuManager = new MenuManager();
        KioskView kioskView = new KioskView();
        KioskController kioskController = new KioskController(menuManager, kioskView);
        kioskController.run();
    }
}
