package Store.Services;

import Store.Entities.Product;
import Store.Persistent.ManufacturerDAO;
import Store.Persistent.ProductDAO;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author joaqu
 */
public class ProductService {

    ProductDAO dao = new ProductDAO();
    ManufacturerDAO daoM = new ManufacturerDAO();
    ManufacturerService manufacturer = new ManufacturerService();
    
    
    public void newProduct() throws Exception, InputMismatchException {

        Scanner read = new Scanner(System.in).useDelimiter("\n");

        try {

            System.out.println("Ingrese el nombre del producto");
            String nombre = read.next();
            
            if(nombre.trim().isEmpty()) {
                throw new Exception("No puede ingresar un nombre vacío");
            }
            
            System.out.println("Ingrese el precio del producto");
            Double precio = read.nextDouble();
                
            if(precio <= 0.00) {
                throw new Exception("El precio no puede ser 0o un valor no numérico");
               
            }
            System.out.println("Ingrese el nombre del fabricante del producto"
                    + "\n(si desea ver la lista de fabricantes presione 1)");
            String nombreFabricante = read.next();
            
            if (daoM.checkManufacturerInList(nombreFabricante).isEmpty()) {
                
                System.out.println("Nombre inexistente. Por favor agregue un nuevo fabricante. Lista de fabricantes:");
                manufacturer.ListManufacturers();
                
                System.out.println("Ingrese el nombre del fabricante del producto");
                nombreFabricante = read.next();
            
            } 
            
            if (nombreFabricante.equalsIgnoreCase("1")) {
                manufacturer.ListManufacturers();
                System.out.println("\nAhora ingrese el nombre del fabricante del producto"
                        + "\n Si desea agregar un nuevo fabricante presione 2");
                nombreFabricante = read.next();
                 
                
                
                if (nombreFabricante.equalsIgnoreCase("2")) {
                    manufacturer.newManufacturer();
                    
                    System.out.println("Ingrese el nombre del fabricante del producto"
                    + "\n(si desea ver la lista de fabricantes presione 1)");
                    nombreFabricante = read.next();
                    
                    if (nombreFabricante.equalsIgnoreCase("1")) {
                manufacturer.ListManufacturers();
                System.out.println("\nAhora ingrese el nombre del fabricante del producto");
                nombreFabricante = read.next();
                }
            }
            }
            
            int idFabricante = daoM.selectIdFabricante(nombreFabricante);

            Product product = new Product();
            product.setProductId(dao.idProduct() + 1);
            product.setName(nombre);
            product.setPrice(precio);
            product.setManufacturerID(idFabricante);

            dao.addProduct(product);

        } catch (InputMismatchException e) {
            throw new Exception("El precio debe ser un valor numérico");
            }
            catch (Exception e) {
            throw e;
        }

    }

    public void alterProduct() throws Exception, InputMismatchException {

        Scanner read = new Scanner(System.in).useDelimiter("\n");

        try {

            System.out.println("Indique el id del producto que desea modificar");
            
           int productoToModify = read.nextInt();

           if(productoToModify <= 0 || productoToModify > dao.idProduct()) {
               
               System.out.println("El código no ocrresponde a un fabricante");
               alterProduct();
           } else {
               
                System.out.println("\nQué dato del producto desea modificar?"
                    + "\nPara cambiar el nombre presione la tecla 1"
                    + "\nPara cambiar el precio presione la tecla 2");

            int optionChange = read.nextInt();
            
           
            if (optionChange != 1 && optionChange !=2) {
                System.out.println("Opción no válida");
                alterProduct();
                
            }
            if (optionChange == 1) {
                double price = 0.0;
                System.out.println("Ingrese el nuevo nombre del producto");
                String newName = read.next();

                if (newName.trim().isEmpty()) {
                    throw new Exception("Ingresó un nombre vacío");
                }
                dao.modifiyProduct(productoToModify, newName, price, 1);
            }

            if (optionChange == 2) {
                String newName = "";
                System.out.println("Ingrese el nuevo precio del producto");
                Double newPrice = read.nextDouble();
                
                if (newPrice <= 0.00) {
                    throw new Exception("El precio debe ser mayor a 0");
                }

                dao.modifiyProduct(productoToModify, newName, newPrice, 2);
            }

       }
        } 
        
        catch (InputMismatchException e) {
            throw new Exception("El precio debe ser un valor numérico");
        }catch (Exception e) {
            throw e;
        }

    }

    public void listItemsByName() throws Exception {

        try {
            
            
            List<Product> productos = dao.selectProductNamePrice("onlyName");
            if (productos.isEmpty()) {
                throw new Exception("No se encontraron productos");
            } else {
                System.out.println("\nLista de productos: ");
                for (Product p : productos) {
                    System.out.println("Producto código " + p.getProductId()
                            + " - Nombre del producto " + p.getName());
                }}
        } catch (Exception e) {

            throw e;
        }
    }

    public void listItemsByNamePrice() throws Exception {

        try {
            List<Product> productos = dao.selectProductNamePrice("namePrice");
            
            if (productos.isEmpty()) {
                throw new Exception("No se encontraron productos");
            } else {
               System.out.println("\nLista de productos: ");
                for (Product p : productos) {
                    System.out.println("Producto código " + p.getProductId() 
                            + " - Nombre del producto: " + p.getName() 
                            + " - Precio: " + p.getPrice());
                            
                }}
            
        } catch (Exception e) {

            throw e;
        }
    }

    public void listCheaperItem() throws Exception {

        try {
            
            
            List<Product> productos = dao.selectProductNamePrice("cheaper");
            
                System.out.println("\nSe lista el producto más económico");
                for (Product p : productos) {
                    System.out.println("Producto código " + p.getProductId() 
                            + " - Nombre del producto: " + p.getName() 
                            + " - Precio: " + p.getPrice());
                }
        } catch (Exception e) {

            throw e;
        }
    }

    public void listTablets() throws Exception {

        try {
            List<Product> productos = dao.selectTablets();
        
            if (productos.isEmpty()) {
                throw new Exception("No se encontraron tablets");
            } else {
               System.out.println("\nSe listan las tablets");
                for (Product p : productos) {
                    
                    String manufacturerName = daoM.selectManufacturerName(p.getManufacturerID());
                    
                    System.out.println("Producto código " + p.getProductId() 
                            + " - Nombre del producto: " + p.getName() 
                            + " - Precio: " + p.getPrice()
                    + " - Fabricante: " + manufacturerName);
                }
            }
        } catch (Exception e) {

            throw e;
        }
    }

    public void listPriceRange() throws Exception, InputMismatchException {
         Scanner read = new Scanner(System.in).useDelimiter("\n");
         
        try {
            System.out.println("Ingrese los valores del rango"
                    + "\nPrimer valor:");
            int n1 = read.nextInt();
            System.out.println("Segundo valor:");
            int n2 = read.nextInt();
            
          
            List<Product> productos = dao.priceRange(n1, n2);
            if (productos.isEmpty()) {
                throw new Exception("No se encontraron productos en ese rango de precios");
            } else {
                
                 System.out.println("\nSe listan los productos cuyo precio se encuentra entre los valores predefinidos");
                for (Product p : productos) {
                    
                    String manufacturerName = daoM.selectManufacturerName(p.getManufacturerID());
                    
                    System.out.println("Producto código " + p.getProductId() 
                            + " - Nombre del producto: " + p.getName() 
                            + " - Precio: " + p.getPrice()
                    + " - Fabricante: " + manufacturerName);
                }
            }
        } catch (InputMismatchException e) {
                throw new Exception("Los valores del rango deben ser numéricos");
            }
            catch (Exception e) {

            throw e;
        }
    }
}
