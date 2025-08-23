package mini.project.ecomerce_backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import mini.project.ecomerce_backend.common.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequestDTO {
    @NotNull(message = "Thong tin khach hang khong duoc de trong")
    @Valid
    private CustomerInfo customer;

    @NotEmpty(message = "Gio hang khong duoc de trong")
    @Valid
    private List<OrderItemRequest> items;

    @NotNull(message = "Phuong thuc thanh toan la bat buoc")
    private PaymentMethod paymentMethod;

    private String note;

    public OrderRequestDTO(CustomerInfo customer, List<OrderItemRequest> items, PaymentMethod paymentMethod, String note) {
        this.customer = customer;
        this.items = items;
        this.paymentMethod = paymentMethod;
        this.note = note;
    }

    public CustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static class CustomerInfo {
        @NotBlank(message = "Ten khach hang khong duoc de trong")
        private String name;
        @NotBlank(message = "So dien thoai khach hang khong duoc de trong")
        private String phone;
        @NotBlank(message = "Dia chi khach hang khong duoc de trong")
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

    public static class OrderItemRequest {
        @NotBlank(message = "Ma san pham khong duoc de trong")
        private String productId;
        @NotNull(message = "So luong khong duoc de trong")
        @Min(value = 1, message = "So luong phai lon hon 0")
        private Integer quantity;


        public OrderItemRequest() {
        }

        public OrderItemRequest(String productId, Integer quantity ) {
            this.productId = productId;
            this.quantity = quantity;

        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }


    }
}
