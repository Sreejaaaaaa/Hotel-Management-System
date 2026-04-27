package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

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

    public Map<String, Object> makePayment(Payment payment) {

        Bill bill = new Bill();
        bill.setBookingId((long) payment.getBookingId());
        bill.setAmount(payment.getAmount());

        Bill generatedBill = billingClient.generateBill(bill);

        payment.setAmount(generatedBill.getTotalAmount());

        try {
            log.info("Amount before Razorpay: {}", payment.getAmount());

            RazorpayClient razorpay = new RazorpayClient(key, secret);

            JSONObject options = new JSONObject();
            options.put("amount", (int) Math.round(payment.getAmount() * 100)); // in paise
            options.put("currency", "INR");
            options.put("receipt", "txn_" + System.currentTimeMillis());

            Order order = razorpay.orders.create(options);

            log.info("Razorpay Order Created: {}", order.toString());

            payment.setTransactionId(order.get("id").toString());
            payment.setStatus("CREATED");

        } catch (Exception e) {
            log.error("Razorpay ERROR", e);

            payment.setStatus("FAILED");
            payment.setTransactionId("FAILED_TXN");
        }

        Payment savedPayment = paymentRepository.save(payment);

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", savedPayment.getTransactionId());
        response.put("amount", savedPayment.getAmount());
        response.put("currency", "INR");
        response.put("paymentId", savedPayment.getId());

        return response;
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
    
    public String generateSignature(String orderId, String paymentId) throws Exception {

        String data = orderId + "|" + paymentId;

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        mac.init(secretKey);

        byte[] rawHmac = mac.doFinal(data.getBytes());
        return Hex.encodeHexString(rawHmac);
    }

    
    
    
}