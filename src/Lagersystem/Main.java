package Lagersystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        WarehouseManagement management = new WarehouseManagement();

        System.out.println("Hello. With this warehouse management system you can manage four " +
                "warehouses. \nnorth, east, south and west. Right now they are all empty. \n" +
                "If you want to know all possible commands, please enter '/help'.");

        //default
        management.update();
    }
}
