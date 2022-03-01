/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Store.Services.ManufacturerService;
import Store.Services.ProductService;
import java.util.Scanner;

/**
 *
 * @author joaqu
 */
public class MainService {

    ProductService product = new ProductService();
    ManufacturerService manufacturer = new ManufacturerService();

    public void mainService() throws Exception {

        Scanner read = new Scanner(System.in).useDelimiter("\n");

        String menuOption = "";
        
        System.out.println("Bienvenido");
        do {
            try {

                 System.out.println("\nPresione Enter para continuar");
                      read.next();
                      
                System.out.println("Por favor, escoja una opción"
                        + "\nA. Parar listar los productos por su nombre presione la tecla 1"
                        + "\nB. Para listar los productos por nombre y precio presione la tecla 2"
                        + "\nC. Para lista el producto de menor precio presione la tecla 3"
                        + "\nD. Para listar los productos cuyo precio se encuentre entre dos valores presione la tecla 4"
                        + "\nE. Para listar los productos Portátiles presione la tecla 5"
                        + "\nF. Para agregar un producto a la base de datos presione la tecla 6"
                        + "\nG. Para modificar los datos de un producto de la base de datos presione la tecla 7"
                        + "\nH. Para agregar un fabricante a la base de datos presione la tecla 8"
                        + "\nI. Para imprimir el listado de fabricante a la base de datos presione la tecla 9"
                        + "\nS. Para finalizar este menú presione la tecla S");

                menuOption = read.next();

                switch (menuOption) {

                    case "1":
                        product.listItemsByName();
                        break;
                    case "2":
                        product.listItemsByNamePrice();
                        break;
                    case "3":
                        product.listCheaperItem();
                        break;
                    case "4":
                        product.listPriceRange();
                        break;
                    case "5":
                        product.listTablets();
                        break;
                    case "6":
                        product.newProduct();
                        break;
                    case "7":                     
                        product.alterProduct();
                        break;
                    case "8":
                        manufacturer.newManufacturer();
                        break;
                        case "9":
                        manufacturer.ListManufacturers();
                        break;
                    case "s":
                        System.out.println("Hasta pronto");
                        break;
                        case "S":
                        System.out.println("Hasta pronto");
                        break;
                    default:
                        System.out.println("\nLa opción elegida no es válida");
                        break;
                    
                }
            } catch (Exception e) {

                throw e;
            }

        } while (!menuOption.equalsIgnoreCase("S"));

    }

}
