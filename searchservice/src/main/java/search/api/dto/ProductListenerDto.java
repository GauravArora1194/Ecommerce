package search.api.dto;

import lombok.Data;

@Data
public class ProductListenerDto extends ProductDTO {
    public enum ProductOperation {
        PRODUCT_CREATED, PRODUCT_DELETED, PRODUCT_UPDATED
    }

    private ProductOperation operation;
}
