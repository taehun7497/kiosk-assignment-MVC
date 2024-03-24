import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

class Menu {
    private String name;
    private int price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

class MenuManager {
    private Map<String, List<Menu>> menuCategories;
    private List<Menu> selectedMenu;

    public MenuManager() {
        menuCategories = new HashMap<>();
        selectedMenu = new ArrayList<>();
        addMenu("음료", new Menu("아메리카노", 3500));
        addMenu("음료", new Menu("라떼", 4000));
        addMenu("음료", new Menu("루이보스티", 4000));
        addMenu("음식", new Menu("샌드위치", 5000));
        addMenu("음식", new Menu("베이글", 4500));
        addMenu("음식", new Menu("조각케이크", 5500));
    }

    public void addMenu(String category, Menu menu) {
        List<Menu> menuList = menuCategories.get(category);
        if (menuList == null) {
            menuList = new ArrayList<>();
            menuCategories.put(category, menuList);
        }
        menuList.add(menu);
    }

    public Map<String, List<Menu>> getMenuCategories() {
        return menuCategories;
    }

    public List<Menu> getSelectedMenu() {
        return selectedMenu;
    }

    public void cancelMenu(int menuIdx) {
        selectedMenu.remove(menuIdx);
    }
}