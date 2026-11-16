package in.balu.controller;
import in.balu.dto.PaymentRequestDTO;
import in.balu.dto.PaymentResponseDTO;
import in.balu.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public PaymentResponseDTO makePayment(@RequestBody PaymentRequestDTO request) {

        return paymentService.processPayment(request);

    }
}