package mini.project.ecomerce_backend.service.impl;

import mini.project.ecomerce_backend.common.Exception.OutOfStockException;
import mini.project.ecomerce_backend.common.OrderStatus;
import mini.project.ecomerce_backend.common.PaymentMethod;
import mini.project.ecomerce_backend.dto.OrderRequestDTO;
import mini.project.ecomerce_backend.dto.OrderResponseDTO;
import mini.project.ecomerce_backend.entity.Customer;
import mini.project.ecomerce_backend.entity.Order;
import mini.project.ecomerce_backend.entity.OrderItem;
import mini.project.ecomerce_backend.entity.Product;
import mini.project.ecomerce_backend.mapper.OrderMapper;
import mini.project.ecomerce_backend.repository.CustomerRepository;
import mini.project.ecomerce_backend.repository.OrderItemRepository;
import mini.project.ecomerce_backend.repository.OrderRepository;
import mini.project.ecomerce_backend.repository.ProductRepository;
import mini.project.ecomerce_backend.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            OrderItemRepository orderItemRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }
    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        // 1. Validate payment method
        if (orderRequestDTO.getPaymentMethod() != PaymentMethod.COD) {
            throw new IllegalArgumentException("Chỉ hỗ trợ COD");
        }

        // 2. Validate stock availability và tính toán total amount
        Map<String, Product> productMap = validateStockAndGetProducts(orderRequestDTO.getItems());
        BigDecimal totalAmount = calculateTotalAmount(orderRequestDTO.getItems(), productMap);

        // 3. Generate order ID
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // 4. Save customer
        Customer customer = orderMapper.toCustomerEntity(orderRequestDTO.getCustomer());
        customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);

        // 5. Save order
        Order order = new Order();
        order.setId(orderId);
        order.setCustomerId(savedCustomer.getId());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPaymentMethod(orderRequestDTO.getPaymentMethod());
        order.setTotalAmount(totalAmount);
        order.setNote(orderRequestDTO.getNote());
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        // 6. Save order items and decrease stock
        List<OrderItem> orderItems = saveOrderItemsAndUpdateStock(orderRequestDTO.getItems(), orderId, productMap);

        // 7. Build response
        return orderMapper.toResponse(savedOrder, savedCustomer, orderItems, productMap);
    }

    private Map<String, Product> validateStockAndGetProducts(List<OrderRequestDTO.OrderItemRequest> items) {
        List<String> productIds = items.stream()
                .map(OrderRequestDTO.OrderItemRequest::getProductId)
                .collect(Collectors.toList());

        Map<String, Product> productMap = productRepository.findByIdIn(productIds)
                .stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        // Validate stock
        List<String> outOfStockProducts = new ArrayList<>();
        List<String> insufficientStockProducts = new ArrayList<>();

        for (OrderRequestDTO.OrderItemRequest item : items) {
            Product product = productMap.get(item.getProductId());
            if (product == null) {
                throw new IllegalArgumentException("Sản phẩm không tồn tại: " + item.getProductId());
            }

            if (product.getStock() <= 0) {
                outOfStockProducts.add(product.getName());
            } else if (product.getStock() < item.getQuantity()) {
                insufficientStockProducts.add(product.getName() + " (còn " + product.getStock() + ")");
            }
        }

        if (!outOfStockProducts.isEmpty()) {
            throw new OutOfStockException("Sản phẩm hết hàng: " + String.join(", ", outOfStockProducts));
        }

        if (!insufficientStockProducts.isEmpty()) {
            throw new OutOfStockException("Số lượng không đủ: " + String.join(", ", insufficientStockProducts));
        }
        return productMap;
    }

    private BigDecimal calculateTotalAmount(List<OrderRequestDTO.OrderItemRequest> items, Map<String, Product> productMap) {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderRequestDTO.OrderItemRequest item : items) {
            Product product = productMap.get(item.getProductId());
            if (product != null) {
                BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                total = total.add(itemTotal);
            }
        }

        return total;
    }

    private List<OrderItem> saveOrderItemsAndUpdateStock(List<OrderRequestDTO.OrderItemRequest> items, String orderId, Map<String, Product> productMap) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderRequestDTO.OrderItemRequest itemRequest : items) {
            Product product = productMap.get(itemRequest.getProductId());

            // Decrease stock
            int updatedRows = productRepository.decreaseStock(itemRequest.getProductId(), itemRequest.getQuantity());
            if (updatedRows == 0) {
                throw new OutOfStockException("Không thể cập nhật stock cho sản phẩm: " + itemRequest.getProductId());
            }

            // Save order item với giá tại thời điểm order
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(itemRequest.getProductId());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPriceAtOrder(product.getPrice()); // Lấy giá từ product
            orderItems.add(orderItemRepository.save(orderItem));
        }

        return orderItems;
    }

}
