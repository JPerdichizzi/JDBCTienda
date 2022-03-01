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

    public void addProduct(Product producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Not a valid product");
            }

            String sql = "INSERT INTO Producto (codigo, nombre, precio, codigo_fabricante)"
                    + "VALUES ( " + producto.getProductId() + " , '" + producto.getName() + "' , " + producto.getPrice() + " ," + producto.getManufacturerID() + " );";

            insertModifyDelete(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            disconnectDB();
        }
    }

    public void modifiyProduct(int id, String name, double price, int option) throws Exception {
        try {
            
            if (option == 1) {

                String sql = "UPDATE Producto SET "
                        + " nombre = '" + name
                        + "' WHERE codigo = " + id + "";

                insertModifyDelete(sql);
            }

            if (option == 2) {

                String sql = "UPDATE Producto SET precio = " + price + " WHERE codigo = " + id + "";

                insertModifyDelete(sql);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            disconnectDB();
        }
    }

    public List<Product> selectProductNamePrice(String option) throws Exception {

        try {

            String sql = "";
            List productos = null;
            if (option.equalsIgnoreCase("onlyName")) {
                
                sql = "SELECT codigo, nombre FROM Producto";

                queryDataBase(sql);
                productos = new ArrayList();
                Product producto = null;

                while (result.next()) {
                    producto = new Product();
                    producto.setProductId(result.getInt(1));
                    producto.setName(result.getString(2));
               

                    productos.add(producto);

                }
            }
            if (option.equalsIgnoreCase("namePrice")) {
                sql = "SELECT codigo, nombre, precio FROM Producto";

                queryDataBase(sql);
                productos = new ArrayList();
                Product producto = null;

                while (result.next()) {
                    producto = new Product();
                    producto.setProductId(result.getInt(1));
                    producto.setName(result.getString(2));
                    producto.setPrice(result.getDouble(3));
//                producto.setManufacturerID(result.getInt(4));

                    productos.add(producto);

                }
            }
            if (option.equalsIgnoreCase("cheaper")) {
                sql = "SELECT codigo, nombre, precio FROM Producto order by precio limit 1";

                queryDataBase(sql);
                productos = new ArrayList();
                Product producto = null;

                while (result.next()) {
                    producto = new Product();
                    producto.setProductId(result.getInt(1));
                    producto.setName(result.getString(2));
                    producto.setPrice(result.getDouble(3));
//                producto.setManufacturerID(result.getInt(4));

                    productos.add(producto);

                }

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
            List<Product> products = new ArrayList();
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

    public List<Product> priceRange(int n1, int n2) throws Exception {

        try {

            String sql = "SELECT * FROM Producto "
                    + " WHERE precio between " + n1 + " and " + n2 + "";

            queryDataBase(sql);
            List<Product> products = new ArrayList();
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

    public int idProduct() throws Exception {

        try {
            int id = 0;
            
            String sql = "SELECT max(codigo) FROM Producto;";
            queryDataBase(sql);
            
            if (result.next()) {
                id = result.getInt(1);
            }
              
            
            disconnectDB();
            return id;

        } catch (Exception e) {
            disconnectDB();
            throw e;
        }
    }
}
