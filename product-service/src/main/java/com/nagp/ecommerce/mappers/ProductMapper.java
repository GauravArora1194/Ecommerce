package com.nagp.ecommerce.mappers;

import java.util.List;

import com.nagp.ecommerce.dto.ProductDTO;
import com.nagp.ecommerce.entity.Product;

public interface ProductMapper {

	List<ProductDTO> mapToDTO(List<Product> product);

	List<Product> map(List<ProductDTO> product);

	Product map(ProductDTO productDTO);

	ProductDTO map(Product product);
}
