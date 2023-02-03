package com.sp.spring.orderservice.repository.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.spring.common.util.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "CART_ITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem extends DateAudit {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "CART_ITEM_ID", updatable = false, nullable = false)
    private String cartItemId;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "ITEM_PRICE", nullable = false)
    private double itemPrice;

    @Column(name = "EXTENDED_PRICE", nullable = false)
    private double extendedPrice;
    
    @Column(name = "PRODUCT_ID", nullable = false)
    private String productId;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;
    
    @ManyToOne
    @JoinColumn(name = "CART_ID")
    @JsonIgnore
    private Cart cart;
    
    @PreRemove
    public void dismissParent() {
        this.cart.dismissChild(this); //SYNCHRONIZING THE OTHER SIDE OF RELATIONSHIP
        this.cart = null;
    }
    
    @Override
    public String toString() {
        return "CartItem{" +
                       "cartItemId='" + cartItemId + '\'' +
                       ", quantity=" + quantity +
                       ", itemPrice=" + itemPrice +
                       ", productId='" + productId + '\'' +
                       '}';
    }
}
