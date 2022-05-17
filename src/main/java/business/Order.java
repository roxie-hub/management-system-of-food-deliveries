package business;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private int orderId;
    private String clientId;
    private Date orderDate;
    private int price;
    public static int id=0;
    public Order(String clientId, Date orderDate, int price) {
        this.orderId = id;
        id++;
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && clientId == order.clientId && price == order.price && Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, orderDate, price);
    }

    @Override
    public String toString() {
        return
                "orderId=" + orderId +
                ", clientId='" + clientId + '\'' +
                ", orderDate=" + orderDate +
                ", price=" + price;
    }
}
