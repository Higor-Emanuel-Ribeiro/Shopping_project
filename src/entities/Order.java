package entities;

import entities.enuns.OrderStatus;
import entities.execpition.ProgramExceptions;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    private Date moment;
    private OrderStatus status;
    private Double amount;
    
    private Client clients;
    List<OrderItem> itens = new ArrayList<>();
    List<Double> service = new ArrayList<>();
    

    public Order(Date moment, OrderStatus status, Client clients) {
        this.moment = moment;
        this.status = status;
        this.clients = clients;
    }

    public Date getMoment() {
        return moment;
    }
    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public Client getClients() {
        return clients;
    }

    public void setClients(Client clients) {
        this.clients = clients;
    }

    public List<OrderItem> getOrderItens() {
        return itens;
    }

    public List<Double> getService() {
        return service;
    }
    
    public void addItem(OrderItem item) {
        itens.add(item);
    }
    
    public void removeItem(OrderItem item) {
        itens.remove(item);
    }
    
    public Double total() {
        Double total = 0.0;
        for (OrderItem or: itens) {
            total += or.subTotal();
        }
        if (total < 0) {
            throw new ProgramExceptions("Your order must be incorrect. Try again please!");
        } else {
            return total;
        }
    }
    
    public void paymentWithoutDebit() {
        amount = total() - (total() * 0.05);
    }
    
    public void paymentWithoutCredit(Integer installments) {
         double valueInstallments = total() / installments;
         for (int i = 1; i <= installments; i++) {
            amount = valueInstallments + interest(valueInstallments, i);
            service.add(amount);
         }  
    }
    
    private double interest(Double amount, Integer months) {
        return amount * 0.01 * months;
    } 
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order moment: ");
        sb.append(sdf.format(moment)).append("\n");
        sb.append("Order status: ");
        sb.append(status).append("\n");
        sb.append("Client: ");
        sb.append(clients).append("\n\n");
        sb.append("ORDER ITENS:\n");
        sb.append(itens.toString()).append("\n");  
        sb.append("Total price: $");
        sb.append(String.format("%.2f", total())).append("\n");
        
        return sb.toString();
    }
}
