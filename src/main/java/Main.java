public class Main {
    public static void main(String[] args) {
        MenuManager menuManager = new MenuManager();
        KioskView kioskView = new KioskView();
        KioskController kioskController = new KioskController(menuManager, kioskView);
        kioskController.run();
    }
}