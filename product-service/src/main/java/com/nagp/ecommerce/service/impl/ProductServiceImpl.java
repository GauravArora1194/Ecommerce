package com.nagp.ecommerce.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagp.ecommerce.dao.ProductRepository;
import com.nagp.ecommerce.dto.ProductDTO;
import com.nagp.ecommerce.dto.ProductUpdatesDto;
import com.nagp.ecommerce.entity.Product;
import com.nagp.ecommerce.event.SendProductUpdates;
import com.nagp.ecommerce.mappers.ProductMapper;
import com.nagp.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper mapper;

	@Autowired
	private SendProductUpdates sendProductUpdates;

	@Override
	public List<Product> fetchAllProducts() {

		return productRepository.findAll();
	}

	@Override
	public Product fetchProduct(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		return product.isPresent() ? product.get() : null;
	}

	@Override
	public ProductDTO fetchProduct(String code) {
		Optional<Product> product = productRepository.findByCode(code);
		return product.isPresent() ? mapper.map(product.get()) : null;
	}

	@Override
	@Transactional
	public Product addProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		sendProductUpdates(savedProduct, ProductUpdatesDto.ProductOperation.PRODUCT_CREATED);
		return savedProduct;
	}

	@Override
	@Transactional
	public Product updateProduct(Long productId, ProductDTO productData) {
		Product product = fetchProduct(productId);
		if (Objects.nonNull(product)) {
			// TODO
			// update here
		}
		sendProductUpdates(product, ProductUpdatesDto.ProductOperation.PRODUCT_UPDATED);
		return product;
	}

	@Override
	@Transactional
	public void deleteProduct(Long productId) {
		Product product = fetchProduct(productId);
		productRepository.deleteById(productId);
		sendProductUpdates(product, ProductUpdatesDto.ProductOperation.PRODUCT_DELETED);
	}

	private void sendProductUpdates(Product product, ProductUpdatesDto.ProductOperation productOperation) {
		ProductUpdatesDto productUpdatesDto = new ObjectMapper().convertValue(product, ProductUpdatesDto.class);
		productUpdatesDto.setOperation(productOperation);
		sendProductUpdates.sendProduct(productUpdatesDto);
	}

}
