package kitchenpos.dto;

public class MenuProductRequest {
    private Long productId;
    private long quantity;

    private MenuProductRequest() {
    }

    private MenuProductRequest(Long productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }
}