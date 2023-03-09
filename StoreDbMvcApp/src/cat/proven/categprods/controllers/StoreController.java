package cat.proven.categprods.controllers;

import cat.proven.categprods.exceptions.StoreDalException;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.model.Product;
import cat.proven.categprods.model.StoreModel;
import cat.proven.view.StoreView;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dax
 */
public class StoreController {

    private StoreModel model;
    private StoreView view;

    public StoreController(StoreModel model) {
        this.model = model;
        this.view = new StoreView(this, model);

    }

    public void start() throws StoreDalException {
        view.display();
    }

    public void processAction(String action) throws StoreDalException {
        if (action != null) {
            switch (action) {
                case "exit"://exit
                    exitApplication();
                    break;
                case "category/all":
                    listAllCategories();
                    break;
                case "category/add":
                    addCategory();
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
                    view.displayMessage("Action not supported");
                    break;
            }
        }
    }

    /**
     * asks for confirmation and, if so, exits application
     */
    public void exitApplication() {
        String answer = view.inputString("Sure to exit?");
        if (answer.equalsIgnoreCase("yes")) {
            view.close();
        }
    }

    /**
     * get all categories from database and displays then.
     */
    private void listAllCategories() {
        //retrieve all categories
        try {
            List<Category> data = model.findAllCategories();
            if (data != null) {
                view.displayList(data);
            } else {
                view.displayMessage("Error retrieving data");
            }
        } catch (StoreDalException ex) {
            view.displayMessage("Error connecting to database");
        }

    }

    /**
     * Add a new category in the database
     */
    private void addCategory() {
        Category category = view.inputCategory();
        if (category != null) {
            int result = model.addCategory(category);
            if (result == 1) {
                view.displayMessage("Category succeessfully added");
            } else {
                view.displayMessage("Error adding category");
            }
        } else {
            view.displayMessage("Error reading data");
        }
    }

    /**
     * get a list category by code from database and displays then.
     */
    private void listCategoryByCode() {
        //read code
        String code = view.inputString("Input Category code: ");
        if (code != null) {
            Category found = model.findCategoryByCode(code);
            if (found != null) {
                //display category
                view.displayCategory(found);
            } else {
                view.displayMessage("Category not found");
            }
        } else {
            view.displayMessage("Error reading code");
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
        view.displayMessage("Update a category");
        Scanner scan = new Scanner(System.in);
        String code = view.inputString("Input category code: ");
        if (code != null) {
            Category found = model.findCategoryByCode(code);
            if (found != null) {
                view.displayCategory(found);
                System.out.println("Its allright ? ");
                String option = scan.nextLine();
                if (option.equalsIgnoreCase("yes")) {
                    view.displayMessage("Input update Info: ");
                    Category actualCategory = new Category(found);
                    Category updatedCategory = view.inputCategory();
                    int result = model.modifyCategory(actualCategory, updatedCategory);
                    String resultMsg = (result == 1) ? "Category updated" : "Category not updated";
                    view.displayMessage(resultMsg);
                }
            } else {
                view.displayMessage("Category not found");
            }
        }

    }

    /**
     * Ask code,name,stock,price and category code
     * if stock or price is incorrect value it shows the user
     * checks category code exists and shwos the user if it exists or not
     * if exists continue and adds the product else shows the user it's not exist
     * if the product code alredy exists it's show user 'product code exists'
     */
    private void addProduct() {
        view.displayMessage("Add product");
        Product product = view.inputProduct();
        int result = model.addProduct(product);
        String resultMsg = (result == 1) ? "Product saved" : "Product not saved";
        view.displayMessage(resultMsg);
    }

    /**
     * Asks the user the product id to search if correctly read and delete
     * product with id and reports result to user
     */
    private void deleteCategory() {
        view.displayMessage("Delete a category");
        Scanner scan = new Scanner(System.in);
        view.displayMessage("Category id: ");
        long id = scan.nextLong();
        Category category = new Category(id);
        int result = model.deleteCategory(category);
        String resultMsg = (result == 0) ? "Category deleted" : "Category not deleted";
        view.displayMessage(resultMsg);
    }

    //Methods by Products
    /**
     * gets a list with all products and displays it
     */
    private void listAllProducts() throws StoreDalException {
        List<Product> data = model.findAllProducts();
        if (data != null) {
            view.displayList(data);
        } else {
            view.displayMessage("Error retrieving data");
        }

    }

    /**
     * Asks the user the category to search if correctly read, it returns the
     * product with that category and reports result to user
     */
    private void listProductCategory() {
        view.displayMessage("Retrieve product with category");
        Scanner scan = new Scanner(System.in);
        view.displayMessage("Input product id: ");
        long productId = scan.nextLong();
        Product product = model.findProductWithCategory(new Product(productId));
        System.out.println(product);
    }

    /**
     * Asks the user the code product to search if correctly read , it returns
     * the product at modify
     */
    private void modifyProduct() {
        view.displayMessage("Update a product");
        Scanner scan = new Scanner(System.in);
        String code = view.inputString("Input product code: ");
        if (code != null) {
            Product found = model.findProductByCode(code);
            if (found != null) {
                view.displayProduct(found);
            } else {
                view.displayMessage("Category not found");
            }
        }
        view.displayMessage("Product id (of product to be updated): ");
        long id = scan.nextLong();
        Product oldP = new Product(id);
        Product newP = view.inputProduct();
        int result = model.modifyProduct(oldP, newP);
        String resultMsg = (result == 1) ? "Product updated" : "Product not updated";
        System.out.println(resultMsg);
    }

    /**
     * Asks the user the code to search if correctly read, it returns the
     * product with that code and reports result to user
     */
    private void listProductByCode() {
        //read code
        String code = view.inputString("Input product code: ");
        if (code != null) {
            Product found = model.findProductByCode(code);
            if (found != null) {
                //display product
                view.displayProduct(found);
            } else {
                view.displayMessage("Product not found");
            }
        } else {
            view.displayMessage("Error reading code");
        }
    }

    /**
     * Asks the user the product id to search if correctly read and delete
     * product with id and reports result to user
     */
    private void deleteProduct() {
        view.displayMessage("Delete a product");
        Scanner scan = new Scanner(System.in);
        view.displayMessage("Product id: ");
        long id = scan.nextLong();
        Product product = new Product(id);
        int result = model.deleteProduct(product);
        String resultMsg = (result == 0) ? "Product deleted" : "product not deleted";
        view.displayMessage(resultMsg);
    }

    /**
     * Asks the user the name to search if correctly read, it returns the
     * product with that name and reports result to user
     */
    private void listProductByName() throws StoreDalException {
        String name = view.inputString("Input product name: ");
        if (name != null) {
            Product found = model.findProductByName(name);
            if (found != null) {
                view.displayProduct(found);
            } else {
                view.displayMessage("Product not found");
            }
        }

    }

}
