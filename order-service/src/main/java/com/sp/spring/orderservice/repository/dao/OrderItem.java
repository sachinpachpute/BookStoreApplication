package com.sp.spring.orderservice.repository.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.spring.common.util.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends DateAudit {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ORDER_ITEM_ID", updatable = false, nullable = false)
    private String orderItemId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    @JsonIgnore
    private Order order;
    
    @Column(name = "PRODUCT_ID", nullable = false)
    private String productId;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;
    
    @Column(name = "ORDER_ITEM_PRICE", nullable = false)
    private double orderItemPrice;

    @Column(name = "ORDER_EXTENDED_PRICE", nullable = false)
    private double orderExtendedPrice;
    
}
