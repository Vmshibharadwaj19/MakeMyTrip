package com.Irctc.Payments.ServiceImpl;

import com.Irctc.Payments.Dto.PaymentRequestDto;
import com.Irctc.Payments.Dto.PaymentResponseDto;
import com.Irctc.Payments.Entity.Payment;
import com.Irctc.Payments.PaymentRepository.PaymentRepo;
import com.Irctc.Payments.Service.PaymentService;
import com.Irctc.Payments.Status;
import com.Irctc.Payments.Util.TrasactionIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PeaymentsImpl implements PaymentService {
    private final PaymentRepo paymentRepo;
    private final TrasactionIdGenerator gen;

    @Override
    @Transactional
    public PaymentResponseDto createPayment(PaymentRequestDto request) {

        Payment payment = new Payment();
        payment.setBookingId(request.getBookingId());
        payment.setAmount(request.getAmount());
        payment.setType(request.getPaymentMethod());
        payment.setStatus(Status.Pending);
       // payment.setTransactionId(gen.generateTrasactionId());


        return mapper(paymentRepo.save(payment));


    }

    @Override
    @Transactional
    public PaymentResponseDto processPayment(Long paymentId) {
        Payment p=paymentRepo.findById(paymentId).orElseThrow();

        if(p.getStatus().equals(Status.Seccess)){

            return  mapper(paymentRepo.save(p));
        }

        boolean payment=true;
        if(payment)
        {
            p.setStatus(Status.Seccess);
            p.setTransactionId(gen.generateTrasactionId());


        }
        else {
            p.setStatus(Status.Failed);

        }

        return mapper(paymentRepo.save(p));
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponseDto getPaymentById(Long paymentId) {
        Payment p=paymentRepo.findById(paymentId).orElseThrow();
        return mapper(p);
    }

    @Override
    public PaymentResponseDto getPaymentByBookingId(Long bookingId) {
        Payment p=paymentRepo.findByBookingId(bookingId).orElseThrow();

        return mapper(p);
    }

    @Override
    public Page<PaymentResponseDto> getAllPayments(Pageable pageable) {
        Page<Payment> p=paymentRepo.findAll(pageable);
        return p.map(this::mapper);
    }

    public PaymentResponseDto mapper(Payment p)
    {
        PaymentResponseDto pd=new PaymentResponseDto(
                p.getId(),p.getBookingId(),
                p.getAmount(),p.getType(),p.getStatus(),
                p.getTransactionId(),p.getCreatedAt()
        );

        return pd;
    }
}
