package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payment;
import com.example.demo.feign.BillingClient;
import com.example.demo.model.Bill;
import com.example.demo.repository.PaymentRepository;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BillingClient billingClient;

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    public Payment makePayment(Payment payment) {

        // Step 1: Create bill
        Bill bill = new Bill();
        bill.setBookingId((long) payment.getBookingId());
        bill.setAmount(payment.getAmount());

        // Step 2: Call billing
        Bill generatedBill = billingClient.generateBill(bill);

        // Step 3: Update amount
        payment.setAmount(generatedBill.getTotalAmount());

        try {
            log.info("Amount before Razorpay: {}", payment.getAmount());

            RazorpayClient razorpay = new RazorpayClient(key, secret);

            JSONObject options = new JSONObject();
            options.put("amount", (int) Math.round(payment.getAmount() * 100)); // FIXED
            options.put("currency", "INR");
            options.put("receipt", "txn_" + System.currentTimeMillis());

            Order order = razorpay.orders.create(options);

            log.info("Razorpay Order Created: {}", order.toString());

            payment.setTransactionId(order.get("id").toString());
            payment.setStatus("SUCCESS");

        } catch (Exception e) {
            log.error("Razorpay ERROR", e);

            payment.setStatus("FAILED");
            payment.setTransactionId("FAILED_TXN");
        }

        return paymentRepository.save(payment);
    }

    
    public String verifyPayment() {
        return "Payment verification simulated successfully";
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByBooking(int bookingId) {
        return paymentRepository.findAll()
                .stream()
                .filter(p -> p.getBookingId() == bookingId)
                .toList();
    }
}