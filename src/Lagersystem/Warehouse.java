package Lagersystem;

import java.util.ArrayList;

public class Warehouse {
    ArrayList<Product> products = new ArrayList<Product>();
    // ArrayList name: products; ArrayList contains data of the type Product.

    // Add product to warehouse. If product already exists, update stock and price.
    void addProduct(String productName, int stock, float price){
        Product inputProduct = new Product(productName, stock, price);

        // if it's the first item in the ArrayList
        if(products.isEmpty()){
            products.add(inputProduct);
            System.out.println("Product has been added. \n" + inputProduct.name + " is now " + inputProduct.stock + " times in stock " +
                    "with a price of " + inputProduct.price + " per " + inputProduct.name + ".");
        } else {
        // check if product already exists
            for (int i = 0; i < products.size(); i++) {

                Product p = products.get(i); // because it's easier
                if (p.name.equals(inputProduct.name)){
                    p.stock += inputProduct.stock;
                    p.price = inputProduct.price;
                    System.out.println("Product has been updated. \n" + p.name + " is now " + p.stock + " times in stock \n " +
                            "with a price of " + p.price + " per " + p.name + ".");
                    // evtl. noch hinzufügen, in welchem lager
                } else {
                    products.add(inputProduct);
                    System.out.println("Product has been added. \n" + inputProduct.name + " is now " + inputProduct.stock + " times in stock " +
                            "with a price of " + inputProduct.price + " per " + inputProduct.name + ".");
                }
                break;
            }
        }
    }

    // Withdraw given number of product. No negative numbers! boolean to exit the code as fast as possible
    boolean withdrawProduct(String name, int quantity) {

        // checks if quantity to withdraw is above zero. Returns false if not, to exit the code as fast as possible.
        if (quantity < 1) {
            System.out.println("Please try again and enter a number above zero.");
        } else {
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                if (p.name.equals(name)) {
                    if (p.stock >= quantity) {
                        p.stock -= quantity;
                        System.out.println("The new amount in stock of " + name + " is " + p.stock + ".");
                        // return true to exit the code without checking the other products,
                        // 'cause there are never two items with the same name.
                        return true;
                    } else {
                        System.out.println("The amount in stock is " + p.stock + ". \n You cannot withdraw more than " + p.stock + " items.");
                        return false;
                    }
                } else {
                    System.out.println("Sorry, the product " + name + " does not exist. Please try again with another product.");
                    // evtl. aktuell vorhandene Produkte ausgeben.
                }
                break;
            }
        }
        return false;
    }

    // Return the stock and price of a given product. Returns a String because I wanted to try it and the exercise could
    // be understood like that.
    String inventory(String product){
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            // check if product exists
            if(p.name.equals(product)){
                String inStock = "There are " + p.stock + " " + product + "s in stock for a price of " + p.price + ".";
                return inStock;
            }
            break;
        }
        // if given product does not exist (is also "default"):
        return "Sorry, the product " + product + " does not exist. Please try again with another product.";
    }

    // Getter for the index of a product
    int getProdIndexByName (String productName){
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if(p.name.equals(productName)){
                return i;
            } else {
                System.out.println("Sorry, there is no product called " + productName + ".");
            }
            break;
        }
        System.out.println("Looks like the warehouse is empty.");
        return -1; // needed to return something. Very bad choice if the product does not exist,
                    // because the program crashes. Catch exception in WarehouseManagement.java(100)
    }

}
