/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.categprods.model;

import cat.proven.categprods.exceptions.StoreDalException;
import cat.proven.categprods.model.persist.CategoryDao;
import cat.proven.categprods.model.persist.ProductDao;
import java.util.List;

/**
 *
 * @author dax
 */
public class StoreModel {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public StoreModel() {
        this.categoryDao = new CategoryDao();
        this.productDao = new ProductDao();
    }

    /**
     * adds a new category to database if prevents adding null categories or
     * null code also prevens code duplicates
     *
     * @param category the category to add
     * @return 1 if successful, 0 otherwise.
     */
    public int addCategory(Category category) {
        int result = 0;
        if (category != null) {
            if (category.getCode() != null) {
                Category c = categoryDao.select(category);//comprobar si la categoria existe
                if (c == null) {
                    result = categoryDao.insert(category);
                }

            }
        }

        return result;

    }

    /**
     * modifies a category in the data source, performing proper validations
     *
     * @param oldC the actual category to update
     * @param newC the new values to update
     * @return result code: 1 for success, 0 if fail (change as necessary)
     */
    public int modifyCategory(Category oldC, Category newC) throws StoreDalException {
        int result = 0;
        if ((oldC != null) && (newC != null)) {
            result = categoryDao.update(oldC, newC);
        }
        return result;
    }

    /**
     * finds a category with the given code
     *
     * @param code the code to find
     * @return category found or null if not found or in case of error
     */
    public Category findCategoryByCode(String code) {
        Category c = null;
        if (code != null) {
            c = categoryDao.selectWhereCode(code);
        }
        return c;
    }

    public int deleteCategory(Category category) {
        int result = 0;
        if (category != null) {
            result = categoryDao.delete(category);
        }
        return result;
    }

    /**
     * find all categories in database
     *
     * @return list with all categories or null in case of error
     * @throws StoreDalException in case of error connecting to database
     */
    public List<Category> findAllCategories() throws StoreDalException {
        return categoryDao.selectAll();

    }

    


    public int modifyProduct(Product oldP, Product newP) {
        int result = 0;
        if ((oldP != null) && (newP != null)) { //perform proper validations before attempting insertion
            result = productDao.update(oldP, newP);
        }
        return result;
    }
    
    
    /**
     * finds a product and retrieves all its information, including that
     * corresponding to its category
     *
     * @param product the product to find
     * @return product found or null in case of error
     */
    public Product findProductWithCategory(Product product) {
        Product p = null;
        if (product != null) {
            p = productDao.select(product);
            if (p != null) {
                Category c = categoryDao.select(p.getCategory());
                if (c != null) {
                    p.setCategory(c);
                }
            }
        }
        return p;
    }
    
    
    public int deleteProduct(Product product) {
        int result = 0;
        if (product != null) {
            result = productDao.delete(product);
        }
        return result;
    }

    /**
     * finds all products in data sources
     *
     * @return list of all products or null in case of error
     */
    public List<Product> findAllProducts() throws StoreDalException {
        return productDao.selectAll();
    }

    /**
     * find a product given its code
     *
     * @param code the code to search
     * @return the product with given code or null if not found or in case of
     * error
     */
    public Product findProductByCode(String code) {
        Product result = null;
        if (code != null) {
            result = productDao.selectWhereCode(code);
        }

        return result;

    }

    public List<Product> findProductsByCategory(Category category) {
        List<Product> result = null;
        if (category != null) {
            result = productDao.selectWhereCategory(category);
        }
        return result;
    }

    

    public Product findProductByName(String name) throws StoreDalException {
        Product result = null;
        if (name != null) {
            result = productDao.selectWhereName(name);
        }
         return result;

    }
    
    public int addProduct(Product product) {
        int result = 0;
        if (product != null) { //perform proper validations before attempting insertion
            result = productDao.insert(product);
        }
        return result;
    }
}
