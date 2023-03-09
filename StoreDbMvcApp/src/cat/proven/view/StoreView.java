package cat.proven.view;

import cat.proven.categprods.controllers.StoreController;
import cat.proven.categprods.exceptions.StoreDalException;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.model.Product;
import cat.proven.categprods.model.StoreModel;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dax
 */
public class StoreView {

    private final StoreController controller;
    private final StoreModel model;
    private MainMenu mainMenu;
    private boolean exit;

    public StoreView(StoreController controller, StoreModel model) {
        this.controller = controller;
        this.model = model;
        this.mainMenu = new MainMenu();
    }

    public void display() throws StoreDalException {
        exit = false;
        do {
            //display menu
            mainMenu.show();
            //get action
            String action = mainMenu.getSelectedOptionActionCommand();
            controller.processAction(action);
        } while (!exit);
    }

    /**
     * prompts a message to user and reads answer
     *
     * @param message the msg to display
     * @return user's answer
     */
    public String inputString(String message) {
        System.out.print(message);
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public <T> void displayList(List<T> data) {
        if (data != null) {
            data.forEach(System.out::println);
            System.out.format("%d elements displayed\n", data.size());
        }

    }

    public void displayProduct(Product p) {
        System.out.println(p);
    }

    public Product inputProduct() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("code: ");
            String code = scan.nextLine();
            System.out.println("name: ");
            String name = scan.nextLine();
            System.out.println("stock: ");
            int stock = scan.nextInt();
            System.out.println("price: ");
            double price = scan.nextDouble();
            return new Product(0, code, name, stock, price, new Category());
        } catch (InputMismatchException e) {
            System.out.println("Error: "+e.getMessage());
        }
        return null;
    }

    public Category inputCategory() {
        String code = inputString("Code: ");
        String name = inputString("name: ");
        return new Category(0, code, name);
    }

    public void displayCategory(Category c) {
        System.out.println(c);
    }

    public void close() {
        this.exit = true;
    }
}
