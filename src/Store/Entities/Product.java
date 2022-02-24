/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Store.Entities;

/**
 *
 * @author joaqu
 */
public class Product {
    
    private int ProductId;
    private String name;
    private double price;
    private int ManufacturerID;

    public Product() {
    }

    public Product(int ProductId, String name, double price, int ManufacturerID) {
        this.ProductId = ProductId;
        this.name = name;
        this.price = price;
        this.ManufacturerID = ManufacturerID;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getManufacturerID() {
        return ManufacturerID;
    }

    public void setManufacturerID(int ManufacturerID) {
        this.ManufacturerID = ManufacturerID;
    }

    @Override
    public String toString() {
        return "Product{" + "ProductId=" + ProductId + ", name=" + name + ", price=" + price + ", ManufacturerID=" + ManufacturerID + '}';
    }
    
    
    
}
