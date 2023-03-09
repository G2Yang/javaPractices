
package cat.proven.categprods;

import cat.proven.categprods.controllers.StoreController;
import cat.proven.categprods.exceptions.StoreDalException;
import cat.proven.categprods.model.StoreModel;

/**
 *
 * @author dax
 */
public class MainMvc {
    
    public static void main(String[] args) throws StoreDalException{
        //instantiate model
        StoreModel model = new StoreModel();
        
        //instantiate controller
        StoreController controller = new StoreController(model);
        //start
        controller.start();
    }
    
    
}
