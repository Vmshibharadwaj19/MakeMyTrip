package com.Irctc.Payments.PaymentRepository;

import com.Irctc.Payments.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBookingId(Long BookingId);
}
