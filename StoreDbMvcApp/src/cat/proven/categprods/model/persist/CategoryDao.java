/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.categprods.model.persist;

import cat.proven.categprods.exceptions.ErrorCode;
import cat.proven.categprods.exceptions.StoreDalException;
import cat.proven.categprods.model.Category;
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
 * Data access object for category
 *
 * @author dax
 */
public class CategoryDao {
    private final Map<String, String> queries;

    private final DbConnect dbConnect;

    public CategoryDao() {
        this.dbConnect = new DbConnect();
        this.queries=new HashMap<>();
        initqueries();
    }

    /**
     * fetch all categories from database
     *
     * @return list with all categories or null in case of error
     * @throws cat.proven.categprods.exceptions.StoreDalException
     */
    public List<Category> selectAll() throws StoreDalException {
        List<Category> result = new ArrayList();
        //get a connection
        try ( Connection conn = dbConnect.getConnection()) {
            //TODO check that conn is not null
            String query = getQuery("sAll") ;
            //execute query
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //fetch data
            while (rs.next()) {
                Category cat = fromResultSet(rs);
                if (cat != null) {
                    result.add(cat);
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            result = null;
            throw new StoreDalException("No connection", ErrorCode.DB_NO_CONNECTION.code());
    
        }

        return result;
    }

    public Category select(Category category) {
        Category cat = null;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection()) {
            String query = getQuery("sSelect");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, category.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cat = fromResultSet(rs);

            } else {
                cat = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }

    /**
     * fetches category with given code
     *
     * @param code the code to search
     * @return category object with code being searched or null if not found or
     * in case or error
     */
    public Category selectWhereCode(String code) {
        Category result;
        try ( Connection conn = dbConnect.getConnection()) {

            String query = getQuery("sSelectCode");
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

    /**
     * inserts a new category in the database
     *
     * @param category the category to insert
     * @return number or rows inserted
     */
    public int insert(Category category) {
        int result = 0;
        try ( Connection conn = dbConnect.getConnection()) {

            String query = getQuery("sInsert");
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, category.getCode());
            st.setString(2, category.getName());
            result = st.executeUpdate();

        } catch (SQLException ex) {
            //Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            result = 0;
        }
        return result;
    }

    public int update(Category actualCategory, Category updatedCategory) throws StoreDalException {
        int result = 0;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection()) {
            String query = getQuery("sModify");
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, updatedCategory.getCode());
            st.setString(2, updatedCategory.getName());
            st.setLong(3, actualCategory.getId());
            result = st.executeUpdate();
        } catch (SQLException ex) {
            throw new StoreDalException("No connection", ErrorCode.DB_NO_CONNECTION.code());
        }
        return result;
    }

    public int delete(Category category) {
        int result = 0;
        try ( Connection conn = dbConnect.getConnection()) {
            String query = getQuery("sDelete");
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, category.getId());
            st.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * build a category with data in current row of resultset
     *
     * @param rs the resultset to fetch data from
     * @return a category object with data in current row or null in case error
     */
    private Category fromResultSet(ResultSet rs) throws SQLException {
        Category cat;
        //read data from fields
        long id = rs.getLong("id");
        String code = rs.getString("code");
        String name = rs.getString("name");
        //instantiate a category object
        cat = new Category(id, code, name);
        return cat;

    }

    private void initqueries() {
       queries.put("sAll", "select * from categories");
       queries.put("sSelect", "select * from categories where id=?");
       queries.put("sSelectCode", "select * from categories where code=?");
       queries.put("sModify", "update categories set code=?, name=?  where id=?");
       queries.put("sInsert", "insert into categories values (null,?,?)");
       queries.put("sDelete", "delete from categories where id= ?");
    }

    private String getQuery(String querykey){
        return queries.get(querykey);
    }
}
