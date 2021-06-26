package com.nagp.ecommerce.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nagp.ecommerce.dto.CartDTO;
import com.nagp.ecommerce.dto.CartEntryDTO;
import com.nagp.ecommerce.mapper.CartMapper;
import com.nagp.ecommerce.models.Cart;
import com.nagp.ecommerce.models.CartEntry;

@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public CartDTO map(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartDTO cartDTO = new CartDTO();

        cartDTO.setCurrency( cart.getCurrency() );
        cartDTO.setDate( cart.getDate() );
        cartDTO.setDeliveryCost( cart.getDeliveryCost() );
        cartDTO.setDiscount( cart.getDiscount() );
        cartDTO.setEntries( cartEntryListToCartEntryDTOList( cart.getEntries() ) );
        cartDTO.setNumber( cart.getNumber() );
        cartDTO.setStatus( cart.getStatus() );
        cartDTO.setSubTotal( cart.getSubTotal() );
        cartDTO.setTotalPrice( cart.getTotalPrice() );
        cartDTO.setTotalTax( cart.getTotalTax() );
        cartDTO.setUserId( cart.getUserId() );

        return cartDTO;
    }

    protected CartEntryDTO cartEntryToCartEntryDTO(CartEntry cartEntry) {
        if ( cartEntry == null ) {
            return null;
        }

        CartEntryDTO cartEntryDTO = new CartEntryDTO();

        cartEntryDTO.setBasePrice( cartEntry.getBasePrice() );
        cartEntryDTO.setCode( cartEntry.getCode() );
        cartEntryDTO.setCurrency( cartEntry.getCurrency() );
        cartEntryDTO.setDiscount( cartEntry.getDiscount() );
        cartEntryDTO.setEntryNumber( cartEntry.getEntryNumber() );
        cartEntryDTO.setName( cartEntry.getName() );
        cartEntryDTO.setQuantity( cartEntry.getQuantity() );
        cartEntryDTO.setTax( cartEntry.getTax() );
        cartEntryDTO.setTotalPrice( cartEntry.getTotalPrice() );

        return cartEntryDTO;
    }

    protected List<CartEntryDTO> cartEntryListToCartEntryDTOList(List<CartEntry> list) {
        if ( list == null ) {
            return null;
        }

        List<CartEntryDTO> list1 = new ArrayList<CartEntryDTO>( list.size() );
        for ( CartEntry cartEntry : list ) {
            list1.add( cartEntryToCartEntryDTO( cartEntry ) );
        }

        return list1;
    }
}