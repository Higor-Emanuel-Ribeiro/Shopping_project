package fixationexercise04;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.enuns.OrderStatus;
import entities.Product;
import entities.execpition.ProgramExceptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws ParseException {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            System.out.println("ENTER CLIENT DATA: ");

            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.next();
            System.out.print("Birth date (DD/MM/YYYY): ");
            Date date = sdf.parse(sc.next());

            System.out.println();
            System.out.println("ENTER ORDER DATA: ");
            System.out.println("""
                             --------------------
                             PENDING_PAYMENT,
                             PROCESSING,
                             SHIPPED,
                             DELIVERED,
                             --------------------""");
            
            System.out.print("Status: ");
            OrderStatus status = OrderStatus.valueOf(sc.next().toUpperCase());  
            Order order = new Order(new Date(), status, new Client(name, email, date));

            System.out.print("How many items to this order? ");
            Integer quant = sc.nextInt();

            for (int i = 0; i < quant; i++) {
                System.out.println();
                sc.nextLine();
                System.out.println(("ENTER #" + (i+1) + " ITEM DATA:"));
                System.out.print("Product name: ");
                String nameProduct = sc.nextLine();
                System.out.print("Product price: ");
                Double price = sc.nextDouble();

                Product product = new Product(nameProduct, price);

                System.out.print("Quantity: ");
                Integer quantity = sc.nextInt();

                OrderItem orderItens = new OrderItem(product, price, quantity);
                order.addItem(orderItens);
            }

            System.out.println();
            System.out.println("ORDER SUMMARY: ");
            System.out.print(order);

            sc.close();
        }
        catch (ProgramExceptions | ParseException e) {
            System.out.println("ERROR! " + e.getMessage());
        }
    }
} 