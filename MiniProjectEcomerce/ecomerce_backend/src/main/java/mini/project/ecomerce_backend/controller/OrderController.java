package mini.project.ecomerce_backend.controller;

import jakarta.validation.Valid;
import mini.project.ecomerce_backend.common.Exception.OutOfStockException;
import mini.project.ecomerce_backend.dto.OrderRequestDTO;
import mini.project.ecomerce_backend.dto.OrderResponseDTO;
import mini.project.ecomerce_backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@Validated
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        try {
            OrderResponseDTO response = orderService.createOrder(orderRequestDTO);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "error", "BadRequest",
                            "message", e.getMessage()
                    ));
        } catch (OutOfStockException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "error", "OutOfStock",
                            "message", e.getMessage()
                    ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of(
                            "error", "InternalError",
                            "message", "Đã xảy ra lỗi hệ thống"
                    ));
        }
    }
}
