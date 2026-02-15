import Console.Console;

import Interfaces.ICustomerManager;
import Interfaces.IProductManager;
import Managers.CustomerManager;
import Managers.ProductManager;
import Menus.MenuController;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        IProductManager productManager = new ProductManager();
        ICustomerManager customerManager = new CustomerManager();

        MenuController controller = new MenuController(console, productManager, customerManager);
        controller.start();

    }
}