package cat.proven.guiprod.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Data provider for testing
 * Holds data in a List
 * @author jose
 * @param <T> the generic type
 */
public abstract class DataService<T> {

    protected List<T> data;

    public DataService() {
        data = new ArrayList<>();
    }

    public List<T> findAll() {
        return new ArrayList(data);
    }

    public T findByCode(T t) {
        T p = null;
        int index = data.indexOf(t);
        if (index >= 0) {
            p = data.get(index);
        }
        return p;
    }

    public int add(T t) {
        return ((data.add(t)) ? 1 : 0);
    }

    public int remove(T t) {
        return ((data.remove(t)) ? 1 : 0);
    }

    public int modify(T tCurrent, T tNew) {
        int r = 0;
        int index = data.indexOf(tCurrent);
        if (index >= 0) {
            data.set(index, tNew);
            r = 1;
        }
        return r;
    }

    public abstract void loadData();
    
}
