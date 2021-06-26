package com.nagp.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nagp.ecommerce.dto.CartDTO;
import com.nagp.ecommerce.dto.ProductDTO;
import com.nagp.ecommerce.execptions.ModelNotFoundException;
import com.nagp.ecommerce.mapper.CartMapper;
import com.nagp.ecommerce.services.CartService;

@RestController
@CrossOrigin
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CartMapper mapper;

	@Value("${product.service.url}")
	private String productServiceUrl;

	@Value("${order.service.url}")
	private String orderServiceURL;

	@GetMapping("/{cartNumber}")
	public ResponseEntity<CartDTO> fetchCart(@PathVariable String cartNumber) {

		return new ResponseEntity<CartDTO>(mapper.map(cartService.fetchCart(cartNumber)), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CartDTO> createCart(@RequestParam("userName") String userName) {

		return new ResponseEntity<CartDTO>(mapper.map(cartService.createCart(userName)), HttpStatus.OK);
	}

	@DeleteMapping("/{cartId}")
	public void deleteCart(@PathVariable Long cartId) {

		cartService.deleteCart(cartId);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<CartDTO> getCartForUser(@PathVariable String userId) {

		return new ResponseEntity<CartDTO>(mapper.map(cartService.getCartForUser(userId)), HttpStatus.OK);

	}

	@DeleteMapping
	public ResponseEntity<CartDTO> removeProductFromCart(@RequestParam("code") String code,
			@RequestParam("quantity") int quantity, @RequestParam("cartId") String cartNumber) {

		String url = productServiceUrl + code;

		ProductDTO product = restTemplate.getForObject(url, ProductDTO.class);

		if (null != product) {

			cartService.removeProductFromCart(product, quantity, cartNumber);
			return new ResponseEntity<CartDTO>(mapper.map(cartService.fetchCart(cartNumber)), HttpStatus.OK);
		} else {

			throw new ModelNotFoundException("Product with code " + code + " does not exist");
		}
	}

	@PostMapping("/add")
	public ResponseEntity<CartDTO> addProductToCart(@RequestParam("code") String code,
			@RequestParam("quantity") int quantity, @RequestParam("cartId") String cartNumber) {

		// fetch product from product service

		String url = productServiceUrl + code;

		ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);

		ProductDTO product = response.getBody();

		if (null != product) {

			cartService.addProductToCart(product, quantity, cartNumber);
			return new ResponseEntity<CartDTO>(mapper.map(cartService.fetchCart(cartNumber)), HttpStatus.OK);
		} else {

			throw new ModelNotFoundException("Product with code " + code + " does not exist");
		}
	}

	@GetMapping("/test")
	public String testServiceCommunication() {

		String url = orderServiceURL + "/order/test";

		String response = restTemplate.getForObject(url, String.class);

		return response;
	}

}
