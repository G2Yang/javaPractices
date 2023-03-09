/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.guiprod.model;

/**
 *
 * @author dax
 */
public class ProductService extends DataService<Product> {

    public ProductService() {
        super();
        loadData();
    }

    @Override
    public void loadData() {
        for (int i = 1; i < 10; i++) {
            data.add(
                    new Product(
                            i,
                            String.format("code%02d", i),
                            String.format("desc%02d", i),
                            100.0 + i,
                            10 + i
                    )
            );
        }
    }
}
