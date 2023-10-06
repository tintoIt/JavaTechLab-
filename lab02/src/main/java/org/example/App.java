package org.example;



import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab02", "root", "");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from product");
            while (result.next()){
                System.out.println(result.getString("id") + result.getString("Username") + result.getString("Password") + result.getString("Email"));
            }
            Scanner sc = new Scanner(System.in);
            ProductDAO prd = new ProductDAO(connection);
            int choose;
            do {
                System.out.println("1. Read all product");
                System.out.println("2. Read a product by id");
                System.out.println("3. Add a new product");
                System.out.println("4. Update a product");
                System.out.println("5. Delete a product by id");
                System.out.println("6. Exit");
                System.out.println();
                System.out.print("Your choice: ");
                choose = sc.nextInt();
                sc.nextLine();
                switch (choose){
                    case 1:
                        List<Product> listProduct = prd.readAll();
                        for (Product a:listProduct){
                            System.out.println(a.toString());
                        }
                        break;
                    case 2:
                        System.out.print("Enter product id: ");
                        int productid = sc.nextInt();
                        try {
                            Product a = prd.read(productid);
                            System.out.println(a.toString());
                            System.out.println();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        System.out.print("Enter product name: ");
                        String name = sc.nextLine();
                        System.out.println();

                        System.out.print("Enter product price: ");
                        double price = Double.parseDouble(sc.nextLine());

                        System.out.println();
                        System.out.print("Enter product color: ");
                        String color = sc.nextLine();
                        Product newProduct = new Product(name, price, color);
                        try {
                            prd.add(newProduct);
                            System.out.println("Add product success");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        System.out.print("Enter product id: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter product name: ");
                        String name1 = sc.nextLine();
                        System.out.println();

                        System.out.print("Enter product price: ");
                        double price1 = Double.parseDouble(sc.nextLine());
                        System.out.println();

                        System.out.print("Enter product color: ");
                        String color1 = sc.nextLine();
                        try {
                            Product a = new Product(id, name1, price1, color1);
                            if(prd.update(a)){
                                System.out.println("Update success");
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        System.out.print("Enter product id: ");
                        int del = sc.nextInt();
                        try {
                            if(prd.delete(del)){
                                System.out.println("Delete success");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        break;
                }

            } while (choose != 6);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
