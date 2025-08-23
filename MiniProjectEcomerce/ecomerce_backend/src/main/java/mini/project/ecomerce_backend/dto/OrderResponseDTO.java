package mini.project.ecomerce_backend.dto;

import mini.project.ecomerce_backend.common.OrderStatus;
import mini.project.ecomerce_backend.common.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {
    private String orderId;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private CustomerInfo customer;
    private List<OrderItemResponse> items;
    private BigDecimal totalAmount;
    private String note;
    private LocalDateTime createdAt;
    private String message;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(String orderId, OrderStatus orderStatus, PaymentMethod paymentMethod, CustomerInfo customer, List<OrderItemResponse> items, BigDecimal totalAmount, String note, LocalDateTime createdAt, String message) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.customer = customer;
        this.items = items;
        this.totalAmount = totalAmount;
        this.note = note;
        this.createdAt = createdAt;
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public CustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class CustomerInfo {
        private String name;
        private String phone;
        private String address;
        private String note;

        public CustomerInfo() {
        }
        public CustomerInfo(String name, String phone, String address, String note) {
            this.name = name;
            this.phone = phone;
            this.address = address;
            this.note = note;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }

    public static class OrderItemResponse {
        private String productId;
        private String productName;
        private Integer quantity;
        private BigDecimal priceAtOrder;
        private BigDecimal subtotal;



        public OrderItemResponse(String productId, String productName, Integer quantity, BigDecimal priceAtOrder, BigDecimal subtotal) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.priceAtOrder = priceAtOrder;
            this.subtotal = subtotal;
        }

        public OrderItemResponse() {

        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPriceAtOrder() {
            return priceAtOrder;
        }

        public void setPriceAtOrder(BigDecimal priceAtOrder) {
            this.priceAtOrder = priceAtOrder;
        }

        public BigDecimal getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
        }
    }
}
