package com.sp.spring.payment.repository.dao;

import com.sp.spring.common.util.DateAudit;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_PAYMENT_CUSTOMER")
@Builder
public class UserPaymentCustomer extends DateAudit {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID", updatable = false, nullable = false)
    private String userPaymentCustomerId;

    @Column(name = "USER_ID", nullable = false, unique = true)
    private String userId;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String userName;

    @Column(name = "PAYMENT_CUSTOMER_ID", nullable = false, unique = true)
    private String paymentCustomerId;
}
