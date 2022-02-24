/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Store.Persistent;

import Store.Entities.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaqu
 */
public class ProductDAO extends DAO {
    
    
    public void addProduct (Product producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Not a valid product");
            }

            String sql = "INSERT INTO Producto"
                    + "VALUES ( '" + producto.getProductId() + "' , '" + producto.getName() + "' , '" + producto.getPrice() + "' ,'" + producto.getManufacturerID() + "' );";

            insertModifyDelete(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            disconnectDB();
        }
    }

    public void modifiyProduct (Product producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Unvalid change");
            }

            String sql = "UPDATE Producto SET "
                    + " codigo = '" + producto.getProductId() + "' , nombre = '" + producto.getName() + "' , precio = " + producto.getPrice() + "' , codigo_fabricante = '" + producto.getManufacturerID()
                    + " WHERE id = '" + producto.getProductId() + "'";

            insertModifyDelete(sql);
            
        } catch (Exception e) {
            throw e;
        } finally {
            disconnectDB();
        }
    }
    
    public List<Product> selectProductNamePrice(String respuesta) throws Exception {
        
        
        try {
            
            String sql = "";
            
            if (respuesta){
                sql = "SELECT nombre FROM Producto";
            }
            if (respuesta) {
                sql = "SELECT nombre, precio FROM Producto";
            }
            if (respuesta) {
                sql = "SELECT nombre, precio FROM Producto order by precio limit 1";
            }
            
            
            
            queryDataBase(sql);
            List <Product> productos = new ArrayList();
            Product producto = null;
            
            while (result.next()) {
                producto = new Product();
                producto.setProductId(result.getInt(1));
                producto.setName(result.getString(2));
                producto.setPrice(result.getDouble(3));
                producto.setManufacturerID(result.getInt(4));
                
                productos.add(producto);
                
                        
            }
            disconnectDB();
            return productos;
            
           
        } catch (Exception e) {
            e.printStackTrace();
           disconnectDB();
           throw e;
        }
    }
    
    public List<Product> selectTablets() throws Exception {
        
          try {
            
               String sql = "SELECT * FROM Producto "
                       + " WHERE nombre like '%Portátil%'";
           
            
            
            
            queryDataBase(sql);
            List <Product> products = new ArrayList();
            Product product = null;
            
            while (result.next()) {
                product = new Product();
                product.setProductId(result.getInt(1));
                product.setName(result.getString(2));
                product.setPrice(result.getDouble(3));
                product.setManufacturerID(result.getInt(4));
                
                products.add(product);
                
                        
            }
            disconnectDB();
            return products;
            
           
        } catch (Exception e) {
            e.printStackTrace();
           disconnectDB();
           throw e;
        }
    }
}
