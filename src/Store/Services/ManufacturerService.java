/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Store.Services;

import Store.Entities.Manufacturer;
import Store.Entities.Product;
import Store.Persistent.ManufacturerDAO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author joaqu
 */
public class ManufacturerService {

    ManufacturerDAO dao = new ManufacturerDAO();
    
    public void newManufacturer() throws Exception {
        
     Scanner read = new Scanner(System.in).useDelimiter("\n");

        try {

            System.out.println("Ingrese el nombre del fabricante");
            String nombre = read.next();
            
             if(nombre.trim().isEmpty()) {
                throw new Exception("No puede ingresar un nombre vacío");
            }
            
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setId(dao.idManufacturer() + 1);
            manufacturer.setName(nombre);
          

            dao.addManufacturer(manufacturer);

        } catch (Exception e) {
            throw e;
        }

    }
    public void ListManufacturers() throws Exception {
        try {
        List<Manufacturer> manufacturer = dao.ListManufacturers();
        
         if (manufacturer.isEmpty()) {
                throw new Exception("No se encontraron fabricantes");
            } else {
             System.out.println("\nLista de fabricantes: ");
                for (Manufacturer m : manufacturer) {
                    
              System.out.println("Fabricante código " + m.getId()
                            + " - Nombre del fabricante " + m.getName());
                }}
                
        } catch (Exception e) {

            throw e;
        }
    }
}
