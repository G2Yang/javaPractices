/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.guiprod.model;

import cat.proven.guiprod.model.persistence.DaoActivity;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author dax
 */
public class ModelActivity {
    DaoActivity dao;
    
    public ModelActivity() {
        this.dao = new DaoActivity();
    }
    
    public List<Activity> getDataFromDataBase() {
        List<Activity> model = new ArrayList<Activity>();
        model = dao.getActivities();
        return model;
    }
    
    public List<User> getDataFromFilterByActivity(int activityId) {
        List<User> model = new ArrayList<User>();
        model = dao.getUserByActivity(activityId);
        return model;
    }
    
    public List<User> getDataFromFilterByAge(int minrange, int maxrange) {
        List<User> model = new ArrayList<User>();
        model = dao.getUserByRangOfAge(minrange, maxrange);
        return model;
    }
    
    public int getActivityId(String actName){
        int opcion=0;
        long op = dao.getActivityId(actName);
        opcion = (int)op;
        return opcion;
    }
}
