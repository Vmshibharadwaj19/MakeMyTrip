package com.Irctc.Payments.Entity;

import com.Irctc.Payments.PaymentType;
import com.Irctc.Payments.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    private Long bookingId;

    private BigDecimal amount;

    private PaymentType type;

    private Status status;

    private String transactionId;

    @CreationTimestamp
    private Timestamp createdAt;



}
