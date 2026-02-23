
import Console.Console;
import Managers.CustomerManager;
import Managers.ProductManager;
import Menu.MainMenu;

public class Main {


    public static void main(String[] args) {
        var productManager = new ProductManager<>();
        var customerManager = new CustomerManager();
        var console = new Console();

        var menu = new MainMenu(console, productManager, customerManager);
        menu.start();
    }

}