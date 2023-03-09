/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.categprods;

import cat.proven.categprods.exceptions.StoreDalException;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.model.Product;
import cat.proven.categprods.model.StoreModel;
import cat.proven.view.MainMenu;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dax
 */
public class Main {

    private MainMenu mainMenu;
    private boolean exit;

    private StoreModel model;

    public static void main(String[] args) throws StoreDalException {
        Main ap = new Main();
        ap.start();
    }

    private void start() throws StoreDalException {
        model = new StoreModel();
        mainMenu = new MainMenu();
        exit = false;

        //interact with user
        do {
            //display menu
            mainMenu.show();
            //get action
            String action = mainMenu.getSelectedOptionActionCommand();
            if (action == null) {
                action = "invalid action";
            }
            //control
            switch (action) {
                case "exit":
                    exit = true;
                    break;
                case "category/add":
                    addCategory();//works
                    break;
                case "category/all":
                    listAllCategories();//works
                    break;
                case "category/modify":
                    modifyCategory(); //works
                    break;
                case "category/code":
                    listCategoryByCode();//works
                    break;
                case "category/remove":
                    deleteCategory(); //works
                    break;
                case "product/all":
                    listAllProducts();//works
                    break;
                case "product/add":
                    addProduct(); //works
                    break;
                case "product/category":
                    listProductCategory();//works
                    break;
                case "product/modify":
                    modifyProduct(); //works
                    break;
                case "product/code":
                    listProductByCode();//works
                    break;
                case "product/remove":
                    deleteProduct();//works
                    break;
                case "product/name":
                    listProductByName();//works
                    break;
                default:
                    System.out.println("Invalid action");
                    break;
            }
        } while (!exit);
    }
    //CONTROL METHODS

    /**
     * gets a list with all categories and displays it
     */
    private void listAllCategories() {
        try {
            List<Category> data = model.findAllCategories();
            if (data != null) {
                printList(data);
            } else {
                displayMessage("Error retrieving data");
            }
        } catch (StoreDalException ex) {

            displayMessage("Error connecting to database");
        }

    }

    private void addCategory() {
        Category category = inputCategory();
        if (category != null) {
            int result = model.addCategory(category);
            if (result == 1) {
                displayMessage("Category succeessfully added");
            } else {
                displayMessage("Error adding category");
            }
        } else {
            displayMessage("Error reading data");
        }
    }

    /**
     * asks the user to input the code of the category to modify, searches the
     * category in database with given code. if found, it displays category to
     * user, asks for confirmation and asks new data for the category, otherwise
     * it reports to user. If new data correctly read ,then it modifies the
     * category in database. Finally, it reports result to user asks the user to
     * input data for the category to modify. If prevents code duplicates. null
     * codes...
     */
    private void modifyCategory() throws StoreDalException {
        System.out.println("Update a category");
        Scanner scan = new Scanner(System.in);
        String code = inputString("Input category code: ");
        if (code != null) {
            Category found = model.findCategoryByCode(code);
            if (found != null) {
                displayCategory(found);
            } else {
                displayMessage("Category not found");
            }
        }
        System.out.print("Category id (of category to be updated): ");
        long id = scan.nextLong();
        Category actualCategory = new Category(id);
        Category updatedCategory = inputCategory();
        int result = model.modifyCategory(actualCategory, updatedCategory);
        String resultMsg = (result == 1) ? "Category updated" : "Category not updated";
        System.out.println(resultMsg);
    }
    
    /**
     * This metod ask the user id of the category and deletes using it
     */
    private void deleteCategory() {
        System.out.println("Delete a category");
        Scanner scan = new Scanner(System.in);
        System.out.println("Category id: ");
        long id = scan.nextLong();
        Category category = new Category(id);
        int result = model.deleteCategory(category);
        String resultMsg = (result == 0) ? "Category deleted" : "Category not deleted";
        System.out.println(resultMsg);

    }
    
    
    // ask the code of the category and shows it
    private void listCategoryByCode() {
        //read code
        String code = inputString("Input Category code: ");
        if (code != null) {
            Category found = model.findCategoryByCode(code);
            if (found != null) {
                //display category
                displayCategory(found);
            } else {
                displayMessage("Category not found");
            }
        } else {
            displayMessage("Error reading code");
        }
    }


    private void listAllProducts() throws StoreDalException {
        List<Product> data = model.findAllProducts();
        if (data != null) {
            printList(data);
        } else {
            displayMessage("Error retrieving data");
        }
    }

    private void deleteProduct() {
        System.out.println("Delete a product");
        Scanner scan = new Scanner(System.in);
        System.out.println("Product id: ");
        long id = scan.nextLong();
        Product product = new Product(id);
        int result = model.deleteProduct(product);
        String resultMsg = (result == 0) ? "Product deleted" : "product not deleted";
        System.out.println(resultMsg);

    }

    private void modifyProduct() {
        System.out.println("Update a product");
        Scanner scan = new Scanner(System.in);
        String code = inputString("Input category code: ");
        if (code != null) {
            Product found = model.findProductByCode(code);
            if (found != null) {
                displayProduct(found);
            } else {
                displayMessage("Category not found");
            }
        }
        System.out.print("Product id (of product to be updated): ");
        long id = scan.nextLong();
        Product oldP = new Product(id);
        Product newP = inputProduct();
        int result = model.modifyProduct(oldP, newP);
        String resultMsg = (result == 1) ? "Product updated" : "Product not updated";
        System.out.println(resultMsg);
    }
    
    
    /**
     * asks the user code,name,stock,priece of a product 
     * and creates the product
     */
    private void addProduct() {
        System.out.println("Insert a new product");
        Scanner scan = new Scanner(System.in);
        System.out.print("Product code: ");
        String code = scan.next();
        System.out.print("Product name: ");
        String name = scan.next();
        System.out.print("Product stock: ");
        int stock = scan.nextInt();
        System.out.print("Product price: ");
        double price = scan.nextDouble();
        System.out.print("Product category id: ");
        long categoryId = scan.nextLong();
        Product product = new Product(0, code, name, stock, price, new Category(categoryId));
        int result = model.addProduct(product);
        String resultMsg = (result == 1) ? "Product saved" : "Product not saved";
        System.out.println(resultMsg);
    }

    /**
     * Asks the user the category to search if correctly read, it returns the
     * product with that category and reports result to user
     */
    private void listProductCategory() {
        System.out.println("Retrieve product with category");
        Scanner scan = new Scanner(System.in);
        System.out.println("Input product id: ");
        long productId = scan.nextLong();
        Product product = model.findProductWithCategory(new Product(productId));
        System.out.println(product);
    }

    /**
     * Asks the user the code to search if correctly read, it returns the
     * product with that code and reports result to user
     */
    private void listProductByCode() {
        //read code
        String code = inputString("Input product code: ");
        if (code != null) {
            Product found = model.findProductByCode(code);
            if (found != null) {
                //display product
                displayProduct(found);
            } else {
                displayMessage("Product not found");
            }
        } else {
            displayMessage("Error reading code");
        }
    }

    private void listProductByName() throws StoreDalException {
        String name = inputString("Input product name: ");
        if (name != null) {
            Product found = model.findProductByName(name);
            if (found != null) {
                displayProduct(found);
            } else {
                displayMessage("Product not found");
            }
        }

    }

    //VIEW METHODS
    /**
     * displays a list of data
     *
     * @param <T> data type to display
     * @param data the list to display
     */
    public <T> void printList(List<T> data) {
        if (data != null) {
            data.forEach(System.out::println);
            System.out.format("%d elements displayed\n", data.size());
        }

    }

    /**
     * displays a product
     *
     * @param p the product to display
     */
    public void displayProduct(Product p) {
        System.out.println(p);
    }

    public void displayCategory(Category c) {
        System.out.println(c);
    }

    /**
     * displays a message
     *
     * @param message displayed
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String inputString(String message) {
        System.out.print(message);
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    private Category inputCategory() {
        String code = inputString("Code: ");
        String name = inputString("name: ");
        return new Category(0, code, name);
    }

    private Product inputProduct() {
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
    }

}
