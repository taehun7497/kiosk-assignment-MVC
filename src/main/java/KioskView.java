import java.util.List;
import java.util.Map;
import java.util.Scanner;

class KioskView {
    private Scanner scanner;

    public KioskView() {
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("===== 키오스크 메뉴 =====");
        System.out.println("list: 메뉴 목록 보기");
        System.out.println("select: 메뉴 선택하기");
        System.out.println("cancel: 메뉴 취소하기");
        System.out.println("payment: 결제하기");
        System.out.println("exit: 종료");
        System.out.print("원하는 기능을 선택하세요: ");
    }

    public String getUserChoice() {
        return scanner.nextLine();
    }

    public void displayMenuList(Map<String, List<Menu>> menuCategories) {
        System.out.println("===== 메뉴 목록 =====");
        for (String category : menuCategories.keySet()) {
            String categoryName;
            if (category.equals("음료")) {
                categoryName = "drink";
            } else {
                categoryName = "food";
            }
            List<Menu> menuList = menuCategories.get(category);
            System.out.println(categoryName);
            for (int i = 0; i < menuList.size(); i++) {
                Menu menu = menuList.get(i);
                System.out.println((i + 1) + ". " + menu.getName() + " - " + menu.getPrice() + "원");
            }
        }
    }

    public String getCategoryChoice() {
        System.out.print("카테고리를 선택하세요 (나가려면 'exit' 입력): ");
        return scanner.nextLine();
    }

    public String getMenuChoice() {
        System.out.print("메뉴 번호를 입력하세요 (나가려면 'exit' 입력): ");
        return scanner.nextLine();
    }

    public int getMenuIndex() {
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
        return choice;
    }

    public void displaySelectedMenu(List<Menu> selectedMenu) {
        System.out.println("선택한 메뉴 목록:");
        for (Menu menu : selectedMenu) {
            System.out.println("- " + menu.getName() + " (" + menu.getPrice() + "원)");
        }
    }

    public void displayTotalPrice(int totalPrice) {
        System.out.println("총 가격: " + totalPrice + "원");
    }

    public void displayPaymentComplete() {
        System.out.println("결제가 완료되었습니다.");
    }
}