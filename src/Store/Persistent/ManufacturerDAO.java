/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Store.Persistent;

import Store.Entities.Manufacturer;
import Store.Entities.Product;
import Store.Services.ManufacturerService;
import Store.Services.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author joaqu
 */
public class ManufacturerDAO extends DAO {

    public void addManufacturer(Manufacturer manufacturer) throws Exception {

        try {
            if (manufacturer == null) {

                throw new Exception("Not a valid brand");
            }

            String sql = "INSERT INTO Fabricante (codigo, nombre)"
                    + "VALUES ( " + manufacturer.getId() + " , '" + manufacturer.getName() + "' );";

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
            List<Manufacturer> manufacturers = new ArrayList();
            Manufacturer manufacturer = null;

            while (result.next()) {
                manufacturer = new Manufacturer();
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

    public int selectIdFabricante(String nombre) throws Exception {
        
        if (nombre.isEmpty()) {
            System.out.println("No ha ingresado un nombre de fabricante. Por favor hágalo ahora:");
            Scanner read = new Scanner(System.in).useDelimiter("\n");
            nombre = read.next();
        }
        
        int id = 0;
        String sql = "SELECT codigo FROM Fabricante where nombre like '" + nombre + "'";
        
        queryDataBase(sql);
        
        
        
        if (result.next()) {
            
             id = result.getInt(1);
        }
           
        return id;
    }

    public int idManufacturer() throws Exception {

        try {
            int id = 0;
            String sql = "SELECT max(codigo) FROM Fabricante;";
            
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
    
    public String selectManufacturerName(int id) throws Exception {
        
        if (id < 0) {
            System.out.println("No ha ingresado un id válido. Por favor hágalo ahora:");
            Scanner read = new Scanner(System.in).useDelimiter("\n");
            id = read.nextInt();
        }
        
        String nombre = "";
        String sql = "SELECT nombre FROM Fabricante where codigo = " + id + "";
        queryDataBase(sql);
         
        if (result.next())  {
        
         nombre = result.getString(1);
    }
                   
        return nombre;
    }
    
    public List<Manufacturer> checkManufacturerInList(String nombre) throws Exception {

        try {

            String sql = "SELECT codigo FROM Fabricante where nombre like '" + nombre + "'";

            queryDataBase(sql);
            List<Manufacturer> manufacturers = new ArrayList();
            Manufacturer manufacturer = null;

            while (result.next()) {
                manufacturer = new Manufacturer();
                manufacturer.setId(result.getInt(1));

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
