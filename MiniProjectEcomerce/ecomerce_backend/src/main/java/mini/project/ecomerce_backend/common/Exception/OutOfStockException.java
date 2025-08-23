package mini.project.ecomerce_backend.common.Exception;

public class OutOfStockException  extends RuntimeException{
    public OutOfStockException(String message) {
        super(message);
    }

    public OutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }
}
