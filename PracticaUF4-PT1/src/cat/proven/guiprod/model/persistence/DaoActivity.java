/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.guiprod.model.persistence;

import cat.proven.guiprod.model.Activity;
import cat.proven.guiprod.model.DbConnect;
import cat.proven.guiprod.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author dax
 */
public class DaoActivity {

    DbConnect dbConnect;

    public DaoActivity() {
        this.dbConnect = new DbConnect();
    }

    public List<Activity> getActivities() {
        List<Activity> model = new ArrayList<Activity>();

        try ( Connection conn = dbConnect.getConnection()) {
            //TODO check that conn is not null
            String query = "SELECT * FROM activity";
            //execute query
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //fetch data
            while (rs.next()) {
                Activity act = fromResultSet(rs);
                if (act != null) {
                    model.add(act);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
    
    public List<User> getUserByActivity(int activity){
        List<User> model = new ArrayList<User>();

        try ( Connection conn = dbConnect.getConnection()) {
            //TODO check that conn is not null
            String query = "SELECT * FROM user WHERE activity_id = "+activity;
            //execute query
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //fetch data
            while (rs.next()) {
                User usr = fromUserResultSet(rs);
                if (usr != null) {
                    model.add(usr);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
    
    public long getActivityId(String actName) {
        long opcion = 0;
        try ( Connection conn = dbConnect.getConnection()) {
            //TODO check that conn is not null
            String query = "SELECT * FROM activity WHERE name = '"+actName+"'";
            //execute query
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //fetch data
            while (rs.next()) {
                Activity act = fromResultSet(rs);
                if (act != null) {
                    opcion = rs.getLong("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opcion;
    }
    
    public List<User> getUserByRangOfAge(int minrang, int maxrang){
        List<User> model = new ArrayList<User>();

        try ( Connection conn = dbConnect.getConnection()) {
            //TODO check that conn is not null
            String query = "SELECT * FROM user WHERE age>"+minrang+" and age<"+maxrang;
            //execute query
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //fetch data
            while (rs.next()) {
                User usr = fromUserResultSet(rs);
                if (usr != null) {
                    model.add(usr);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
    
    private Activity fromResultSet(ResultSet rs) throws SQLException {
        Activity prod;
        //read data from fields
        long id = rs.getLong("id");
        String name = rs.getString("name");
        int capacity = rs.getInt("capacity");
        //instantiate a category object
        prod = new Activity(id, name, capacity);
        return prod;
    }
    
    private User fromUserResultSet(ResultSet rs) throws SQLException {
        User prod;
        //read data from fields
        long id = rs.getLong("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        //instantiate a category object
        prod = new User(id, name, age);
        return prod;
    }
}
