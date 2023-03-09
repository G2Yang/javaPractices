package cat.proven.guiprod;

import cat.proven.guiprod.model.Product;
import cat.proven.guiprod.model.ProductService;
import java.util.List;

/**
 *
 * @author jose
 */
public class Tester {

    public static void main(String[] args) {
        ProductService dataService = new ProductService();
        //dataService.loadData();
        List<Product> prodList;
        Product p;
        int resultCode;
        //
        System.out.println("List all");
        prodList = dataService.findAll();
        displayList(prodList);
        //
        System.out.println("Find by code");
        p = dataService.findByCode(new Product(0, "code04"));
        System.out.println(p.toString());
        //
        System.out.println("Add new");
        resultCode = dataService.add(new Product(10, "code10", "desc10", 110.0, 20));
        System.out.println("Result code: "+resultCode);
        prodList = dataService.findAll();
        displayList(prodList);
        //
        System.out.println("Modify");
        resultCode = dataService.modify(new Product(0, "code10"), new Product(10, "code10", "desc10b", 110.1, 20));
        System.out.println("Result code: "+resultCode);
        prodList = dataService.findAll();
        displayList(prodList);
        //
        System.out.println("Remove");
        resultCode = dataService.remove(new Product(10, "code10"));
        System.out.println("Result code: "+resultCode);
        prodList = dataService.findAll();
        displayList(prodList);
    }
    
    private static <T> void displayList(List<T> list) {
        list.forEach(System.out::println);
        System.out.format("%d elements listed\n", list.size());
    }
}
