package com.nagp.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagp.ecommerce.dao.CartEntryJpaRepository;
import com.nagp.ecommerce.dao.CartJpaRepository;
import com.nagp.ecommerce.dto.ProductDTO;
import com.nagp.ecommerce.models.Cart;
import com.nagp.ecommerce.models.CartEntry;
import com.nagp.ecommerce.services.CartFactory;
import com.nagp.ecommerce.services.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartFactory cartFactory;

	@Autowired
	private CartJpaRepository cartRepository;

	@Autowired
	private CartEntryJpaRepository cartEntryRepository;

	@Override
	public Cart fetchCart(final String cartNumber) {

		Optional<Cart> cart = cartRepository.findByNumber(cartNumber);
		return cart.isPresent() ? cart.get() : null;
	}

	@Override
	public Cart createCart(String userName) {

		return cartRepository.saveAndFlush(cartFactory.createCart(userName));
	}

	@Override
	public void deleteCart(Long cartId) {

		cartRepository.deleteById(cartId);
	}

	@Override
	public void addProductToCart(ProductDTO product, int quantity, String cartId) {

		Optional<Cart> cart = cartRepository.findByNumber(cartId);

		if (cart.isPresent()) {

			if (null != product) {

				// check if product is already present in cart
				boolean productAlreadyPresent = cart.get().getEntries().stream()
						.anyMatch(entry -> product.getCode().equals(entry.getCode()));

				if (productAlreadyPresent) {

					updateCartEntry(cart.get(), product, quantity);
				} else {

					createNewEntry(cart.get(), product, quantity);
				}
			}

		}

	}

	@Override
	public void removeProductFromCart(ProductDTO product, int quantity, String cartId) {
		// TODO Auto-generated method stub

		Optional<Cart> cart = cartRepository.findByNumber(cartId);

		if (cart.isPresent()) {

			// check if product is already present in cart
			boolean productAlreadyPresent = cart.get().getEntries().stream()
					.anyMatch(entry -> product.getCode().equals(entry.getCode()));

			if (productAlreadyPresent) {

				updateCartEntry(cart.get(), product, -quantity);
			}
		}

	}

	@Override
	public Cart recalculateCart(Long cartId) {

		Optional<Cart> cart = cartRepository.findById(cartId);

		return cart.isPresent() ? recalculateCart(cart.get()) : null;
	}

	@Override
	public Cart recalculateCart(Cart cart) {

		List<CartEntry> entries = cart.getEntries();

		if (!cart.getEntries().isEmpty()) {

			Double totalPrice = Double.valueOf(entries.stream().mapToDouble(entry -> entry.getTotalPrice()).sum());
			Double totalTax = Double.valueOf(entries.stream().mapToDouble(entry -> entry.getTax()).sum());
			Double totalDiscount = Double.valueOf(entries.stream().mapToDouble(entry -> entry.getDiscount()).sum());

			cart.setTotalPrice(totalPrice);
			cart.setTotalTax(totalTax);
			cart.setDiscount(totalDiscount);
			cart.setSubTotal(cart.getTotalPrice() - cart.getDiscount() + cart.getDeliveryCost());
		} else {
			cart.setTotalPrice(0.0d);
			cart.setTotalTax(0.0d);
			cart.setDiscount(0.0d);
			cart.setSubTotal(0.0d);
		}

		return cartRepository.saveAndFlush(cart);
	}

	@Override
	public Cart getCartForUser(String userId) {

		return cartRepository.findByUserId(userId);

	}

	private void createNewEntry(Cart cart, ProductDTO product, int quantity) {

		// find product stock quantity
		int stockLevel = product.getStock();

		// new quantity for product in cart
		int newProductQty = quantity;

		int qtyToAdd = Math.min(newProductQty, stockLevel);

		if (qtyToAdd > 0) {

			CartEntry entry = cartEntryRepository.saveAndFlush(cartFactory.createCartEntry(cart, product, qtyToAdd));

			cart.getEntries().add(entry);
			recalculateCart(cart);

		}

	}

	private void updateCartEntry(Cart cart, ProductDTO product, int quantity) {

		// find product stock quantity
		int stockLevel = product.getStock();

		// check if product is already added in cart and find it's quantity

		Optional<CartEntry> cartEntry = cart.getEntries().stream()
				.filter(entry -> entry.getCode().equals(product.getCode())).findFirst();

		if (cartEntry.isPresent()) {

			int existingProductQty = cartEntry.get().getQuantity();

			// new quantity for product in cart
			int newProductQty = existingProductQty + quantity;

			int qtyToAdd = Math.min(newProductQty, stockLevel) - existingProductQty;

			CartEntry entry = cartEntryRepository.save(cartFactory.updateCartEntry(cartEntry.get(), product, qtyToAdd));

			if (entry.getQuantity() == 0) {
				cart.getEntries().remove(entry);
				cartEntryRepository.delete(entry);
			}
			recalculateCart(cart);

		}

	}

}
