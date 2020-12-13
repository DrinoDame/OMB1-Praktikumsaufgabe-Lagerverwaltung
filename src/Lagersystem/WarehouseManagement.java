package Lagersystem;

import java.util.Scanner;

public class WarehouseManagement {
    Warehouse north = new Warehouse();
    Warehouse east = new Warehouse();
    Warehouse south = new Warehouse();
    Warehouse west = new Warehouse();


    void update(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            String userInput = scanner.next();
            if(userInput.equals("/exit")){
                break;

            } else if(userInput.equals("/add")){
                System.out.println("Please enter the name of the product to add.");
                String productName = scanner.next();

                System.out.println("Please enter the amount you want to add.");
                int stock = scanner.nextInt();

                System.out.println("Please enter the price per item. As a float, with a ','.");
                float price = scanner.nextFloat();

                System.out.println("To which warehouse you want to add the product " + productName + "?");
                String whNameAsString = scanner.next(); //.nextLine() geht nicht, da er da immer direkt in die
                                                    // getWarehouseByName geht, ohne den Input abzuwarten. Warum?
                Warehouse selectedWarehouse = getWarehouseByName(whNameAsString);

                selectedWarehouse.addProduct(productName, stock, price);

            } else if(userInput.equals("/withdraw")){
                System.out.println("Please enter the name of the product.");
                String productName = scanner.next();

                System.out.println("How much you want to withdraw from " + productName + "?");
                int quantity = scanner.nextInt();

                System.out.println("From which warehouse you want to withdraw?");
                String whNameAsString = scanner.next();
                Warehouse selectedWarehouse = getWarehouseByName(whNameAsString);

                selectedWarehouse.withdrawProduct(productName, quantity);

            } else if(userInput.equals("/inventory")){
                System.out.println("Please enter the product name, from which you want to know the amount in stock and price.");
                String productName = scanner.next();

                System.out.println("In which warehouse is " + productName + " located?");
                String whNameAsString = scanner.next();
                Warehouse selectedWarehouse = getWarehouseByName(whNameAsString);

                String isAvailable = selectedWarehouse.inventory(productName); // inventory is returning a string, it's no "void" method
                System.out.println(isAvailable);

            } else if(userInput.equals("/move")){
                System.out.println("Please enter the product you want to move.");
                String moveIt = scanner.next();

                System.out.println("From which warehouse you want to move " + moveIt + "?");
                String moveFrom = scanner.next();

                System.out.println("To which warehouse you want to add the product?");
                String moveTo = scanner.next();

                moveProduct(moveIt, moveFrom, moveTo);

            } else if(userInput.equals("/help")){
                System.out.println("""
                        Enter /exit to leave the program.\s
                        Enter /add to add an item to the warehouse.\s
                        Enter /withdraw to withdraw a given amount of a product.\s
                        Enter /inventory to check the amount in stock and the price of a given product.""");
            }
        }
    }


    // Move products from one warehouse to another.
    // If product already exists, update stock.
    private void moveProduct(String moveIt, String moveFrom, String moveTo) {

        // Get warehouses by name
        Warehouse moveFromWH = getWarehouseByName(moveFrom);
        Warehouse moveToWH = getWarehouseByName(moveTo);

        // Get the index of product to move
        int indexAtMoveFrom = moveFromWH.getProdIndexByName(moveIt);

        try {
            Product pInMoveFrom = moveFromWH.products.get(indexAtMoveFrom);
            moveToWH.addProduct(pInMoveFrom.name, pInMoveFrom.stock, pInMoveFrom.price);
            // 'Remove' product from the moveFrom warehouse
            moveFromWH.withdrawProduct(pInMoveFrom.name, pInMoveFrom.stock);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception thrown: " + e);
        }

    }

    // Get the wanted warehouse. No breaks needed, because of the return statements.
     private Warehouse getWarehouseByName (String warehouseName){
        switch (warehouseName){
            case "north":
                return north;
            case "east":
                return east;
            case "south":
                return south;
            case "west":
                return west;
            default:
                System.out.println("Please enter north, east, south or west.");
        }
        return null; // I need to return something to make it work, but it's a failure, so I return null.
    }

}
