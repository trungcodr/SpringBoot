package mini.project.ecomerce_backend.entity;

import jakarta.persistence.*;
import mini.project.ecomerce_backend.common.PaymentMethod;
import mini.project.ecomerce_backend.common.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table (name = "orders")
public class Order {
    @Id
    private String id;
    private int customerId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private BigDecimal totalAmount;
    private String note;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Order() {}

    public Order(String id, int customerId, OrderStatus orderStatus, PaymentMethod paymentMethod, BigDecimal totalAmount, String note, LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.note = note;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
