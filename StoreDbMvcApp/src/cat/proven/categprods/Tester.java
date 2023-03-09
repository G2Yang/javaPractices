/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cat.proven.categprods;

import cat.proven.categprods.exceptions.StoreDalException;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.model.Product;
import cat.proven.categprods.model.persist.CategoryDao;
import cat.proven.categprods.model.persist.ProductDao;
import java.util.List;

/**
 *
 * @author dax
 */
public class Tester {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws StoreDalException {
        Tester main = new Tester();
        main.runTests();

    }

    public Tester() {
        categoryDao = new CategoryDao();
        productDao = new ProductDao();
    }

    private void runTests() throws StoreDalException {
        testRetrieveAllcategories();

        testRetrieveAllproducts();

    }

    private void testRetrieveAllcategories() throws StoreDalException {
        System.out.println("Test retrieve all categories");
        List<Category> allCategories = categoryDao.selectAll();
        printList(allCategories);
    }

    private void testRetrieveCategoryByCodeExists() {
        System.out.println("Test retrieve category by code (exists)");
        String code = "C03";
        Category category = categoryDao.selectWhereCode(code);
        System.out.println(category);
    }

    private void testRetrieveCategoryByCodeNotExists() {
        System.out.println("Test retrieve category by code (not exists)");
        String code = "C99";
        Category category = categoryDao.selectWhereCode(code);
        if (category == null) {
            System.out.println("Ok: not found");
        } else {
            System.out.println("Test fail");
        }
    }

    private void testInsertCategoryNotExists() {
        System.out.println("Test Insert category (not exists)");
        Category c = new Category(0, "C07", "category07");
        int numrows = categoryDao.insert(c);
        String msj = (numrows == 1) ? "Ok(inserted)" : "Fail(not inserted)";
        System.out.println(msj);

    }

    private void testInsertCategoryExists() {
        System.out.println("Test Insert category (exists)");
        Category c = new Category(0, "C02", "category08");
        int numrows = categoryDao.insert(c);
        String msj = (numrows == 0) ? "Ok(not inserted)" : "Fail(inserted)";
        System.out.println(msj);

    }

    private void testRetrieveDeleteCategory() {
        System.out.println("Test retrieve delete");
        Category c = new Category(0);
        int delete = categoryDao.delete(c);
        String msj = (delete == 0) ? "Ok(delete)" : "Fail(not delete)";
        System.out.println(msj);
    }

    //Test Retrieve products
    private void testRetrieveAllproducts() throws StoreDalException {
        System.out.println("Test retrieve all products");
        List<Product> allproducts = productDao.selectAll();
        printList(allproducts);
    }

    private void testRetrieveSelectProducts() {
        System.out.println("Test retrieve select");
        Product c = new Product(1);
        Product select = productDao.select(c);
        System.out.println(select);
    }

    private void testInsertProductNotExists() {
        System.out.println("Test Insert Product (not exists)");
        Product c = new Product(0, "P10", "product10", 110, 1010.0, new Category(3, "null", "null"));
        int numrows = productDao.insert(c);
        String msj = (numrows == 1) ? "Ok(inserted)" : "Fail(not inserted)";
        System.out.println(msj);

    }

    private void testInsertProductExists() {
        System.out.println("Test Insert Product (exists)");
        Product c = new Product(0, "P09", "product09", 109, 1009.0, new Category(3, "null", "null"));
        int numrows = productDao.insert(c);
        String msj = (numrows == 0) ? "Ok(not inserted)" : "Fail(inserted)";
        System.out.println(msj);

    }
    private void testRetrieveDeleteProduct() {
        System.out.println("Test retrieve delete");
        Product c = new Product(13);
        int delete = productDao.delete(c);
        String msj = (delete == 0) ? "Ok(delete)" : "Fail(not delete)";
        System.out.println(msj);
    }
    
    

    private <T> void printList(List<T> data) {
        if (data != null) {
            data.forEach(System.out::println);
        }
    }

}
