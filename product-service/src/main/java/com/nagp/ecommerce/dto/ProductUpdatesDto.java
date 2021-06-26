package com.nagp.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdatesDto extends ProductDTO {
    private ProductOperation operation;

    public enum ProductOperation {
        PRODUCT_CREATED, PRODUCT_DELETED, PRODUCT_UPDATED
    }
}
