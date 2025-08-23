package mini.project.ecomerce_backend.service;

import mini.project.ecomerce_backend.dto.OrderRequestDTO;
import mini.project.ecomerce_backend.dto.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
}
