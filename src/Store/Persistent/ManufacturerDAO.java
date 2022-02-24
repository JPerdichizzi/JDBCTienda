/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Store.Persistent;

import Store.Entities.Manufacturer;
import Store.Entities.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaqu
 */
public class ManufacturerDAO extends DAO {
    
    public void addManufacturer (Manufacturer manufacturer) throws Exception {
        try {
            if (manufacturer == null) {
                throw new Exception("Not a valid brand");
            }

            String sql = "INSERT INTO Fabricante"
                    + "VALUES ( '" + manufacturer.getId() + "' , '" + manufacturer.getName()+ "' );";

            insertModifyDelete(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            disconnectDB();
        }
    }
    
    public List<Manufacturer> ListManufacturers() throws Exception {
        
          try {
            
               String sql = "SELECT * FROM Fabricante ";
           
            
            
            
            queryDataBase(sql);
            List <Manufacturer> manufacturers = new ArrayList();
            Manufacturer manufacturer = null;
            
            while (result.next()) {
                manufacturer = new Manufacturer ();
                manufacturer.setId(result.getInt(1));
               manufacturer.setName(result.getString(2));
                
                
                manufacturers.add(manufacturer);
                
                        
            }
            disconnectDB();
            return manufacturers;
            
           
        } catch (Exception e) {
            e.printStackTrace();
           disconnectDB();
           throw e;
        }
    }
}
