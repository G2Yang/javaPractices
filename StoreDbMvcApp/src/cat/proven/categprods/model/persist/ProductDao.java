/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.categprods.model.persist;

import cat.proven.categprods.exceptions.StoreDalException;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO for Prouct Data access object for product
 *
 * @author dax
 */
public class ProductDao {

    private final Map<String, String> queries;
    private final DbConnect dbConnect;

    public ProductDao() {
        this.dbConnect = new DbConnect();
        this.queries = new HashMap<>();
        initqueries();
    }

    /**
     * fetch all Products from database
     *
     * @return list with all products or empty list in case of error
     * @throws cat.proven.categprods.exceptions.StoreDalException
     */
    public List<Product> selectAll() throws StoreDalException {
        List<Product> result = new ArrayList();
        //get a connection
        try ( Connection conn = dbConnect.getConnection()) {
            //TODO check that conn is not null
            String query = getQuery("sAll");
            //execute query
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //fetch data
            while (rs.next()) {
                Product prod = fromResultSet(rs);
                if (prod != null) {
                    result.add(prod);
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new StoreDalException("No connection to database", -10);
        }

        return result;
    }

    /**
     *
     * @param product
     * @return
     */
    public Product select(Product product) {
        Product prod = null;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection()) {
            String query = getQuery("sSelect");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, product.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                prod = fromResultSet(rs);

            } else {
                prod = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }

    /**
     * inserts a new product in the database
     *
     * @param product the product to insert
     * @return number or rows inserted
     */
    public int insert(Product product) {
        int result = 0;
        try ( Connection conn = dbConnect.getConnection()) {

            String query = getQuery("sInsert");
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, product.getCode());
            st.setString(2, product.getName());
            st.setInt(3, product.getStock());
            st.setDouble(4, product.getPrice());
            st.setLong(5, product.getCategory().getId());
            result = st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            result = 0;
        }
        return result;
    }

    /**
     *
     * @param actualProduct
     * @param updatedProduct
     * @return
     */
    public int update(Product actualProduct, Product updatedProduct) {
        int result = 0;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection()) {
            String query = getQuery("sUpdate");
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, updatedProduct.getCode());
            st.setString(2, updatedProduct.getName());
            st.setInt(3, updatedProduct.getStock());
            st.setDouble(4, updatedProduct.getPrice());
            st.setLong(5, actualProduct.getId());
            result = st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int delete(Product product) {
        int result = 0;
        try ( Connection conn = dbConnect.getConnection()) {
            String query = getQuery("sDelete");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, product.getId());
            st.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * fetches product with given code
     *
     * @param category the category to search
     * @return category for product object with code being searched or null if
     * not found or in case or error
     */
    public List<Product> selectWhereCategory(Category category) {
        List<Product> result = new ArrayList<>();
        try ( Connection conn = dbConnect.getConnection()) {
            String query = getQuery("sCategory");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, category.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product prod = fromResultSet(rs);
                if (prod != null) {
                    result.add(prod);
                }
            }

        } catch (SQLException ex) {
            result = null;
        }
        return result;
    }

    /**
     * fetches product with given code
     *
     * @param code the code to search
     * @return product object with code being searched or null if not found or
     * in case or error
     */
    public Product selectWhereCode(String code) {
        Product result;
        try ( Connection conn = dbConnect.getConnection()) {

            String query = getQuery("sCode");
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = fromResultSet(rs);
            } else {//not found
                result = null;
            }

        } catch (SQLException ex) {
            result = null;
        }
        return result;
    }

    public Product selectWhereName(String name) {
        Product result;
        try ( Connection conn = dbConnect.getConnection()) {

            String query = getQuery("sName");
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = fromResultSet(rs);
            } else {//not found
                result = null;
            }

        } catch (SQLException ex) {
            result = null;
        }
        return result;
    }

    /**
     *
     * @param minStock
     * @return
     */
    public List<Product> selectWhereMinStock(int minStock) {
        List<Product> result = new ArrayList<>();
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection()) {
            String query = getQuery("sStock");
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, minStock);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product prod = fromResultSet(rs);
                if (prod != null) {
                    result.add(prod);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * build a product with data in current row of resultset
     *
     * @param rs the resultset to fetch data from
     * @return a product object with data in current row or null in case error
     */
    private Product fromResultSet(ResultSet rs) throws SQLException {
        Product prod;
        //read data from fields
        long id = rs.getLong("id");
        String code = rs.getString("code");
        String name = rs.getString("name");
        int stock = rs.getInt("stock");
        double price = rs.getDouble("price");
        long categoryId = rs.getLong("category_id"); //Category category
        //instantiate a category object
        prod = new Product(id, code, name, stock, price, new Category(categoryId));
        return prod;

    }

    private void initqueries() {
        queries.put("sAll", "select * from products");
        queries.put("sSelect", "select * from products where id=?");
        queries.put("sInsert", "insert into products values (null,?,?,?,?,?)");
        queries.put("sUpdate", "update products set code=?, name=? , stock=?, price=? where id=?");
        queries.put("sDelete", "delete from products where id=?");
        queries.put("sCategory", "select * from products where category_id = ?");
        queries.put("sCode", "select * from products where code = ?");
        queries.put("sName", "select * from products where name like ?");
        queries.put("sStock", "select * from products where stock<?");
    }

    private String getQuery(String querykey) {
        return queries.get(querykey);
    }

}
