package mini.project.ecomerce_backend.mapper;

import mini.project.ecomerce_backend.dto.OrderRequestDTO;
import mini.project.ecomerce_backend.dto.OrderResponseDTO;
import mini.project.ecomerce_backend.entity.Customer;
import mini.project.ecomerce_backend.entity.Order;
import mini.project.ecomerce_backend.entity.OrderItem;
import mini.project.ecomerce_backend.entity.Product;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderMapper {


    public Customer toCustomerEntity(OrderRequestDTO.CustomerInfo customerInfo) {
        Customer customer = new Customer();
        customer.setName(customerInfo.getName());
        customer.setPhone(customerInfo.getPhone());
        customer.setAddress(customerInfo.getAddress());
        customer.setNote(customerInfo.getNote());
        return customer;
    }

    public OrderResponseDTO toResponse(Order order, Customer customer, List<OrderItem> items, Map<String, Product> productMap) {
        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(order.getId());
        response.setOrderStatus(order.getOrderStatus());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setTotalAmount(order.getTotalAmount());
        response.setNote(order.getNote());
        response.setCreatedAt(order.getCreatedAt());
        response.setMessage("Đặt hàng thành công");

        // Map customer
        OrderResponseDTO.CustomerInfo customerInfo = new OrderResponseDTO.CustomerInfo();
        customerInfo.setName(customer.getName());
        customerInfo.setPhone(customer.getPhone());
        customerInfo.setAddress(customer.getAddress());
        customerInfo.setNote(customer.getNote());
        response.setCustomer(customerInfo);

        // Map order items với product name
        response.setItems(items.stream()
                .map(item -> toOrderItemResponse(item, productMap))
                .collect(Collectors.toList()));

        return response;
    }

    private OrderResponseDTO.OrderItemResponse toOrderItemResponse(OrderItem item, Map<String, Product> productMap) {
        OrderResponseDTO.OrderItemResponse response = new OrderResponseDTO.OrderItemResponse();
        response.setProductId(item.getProductId());
        response.setQuantity(item.getQuantity());
        response.setPriceAtOrder(item.getPriceAtOrder());
        response.setSubtotal(item.getPriceAtOrder().multiply(BigDecimal.valueOf(item.getQuantity())));

        // Lấy thông tin product từ map
        Product product = productMap.get(item.getProductId());
        if (product != null) {
            response.setProductName(product.getName());
        }
        return response;
    }


}
