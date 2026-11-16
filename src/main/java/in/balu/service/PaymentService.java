package in.balu.service;

import in.balu.dto.PaymentRequestDTO;
import in.balu.dto.PaymentResponseDTO;

public interface PaymentService {

    PaymentResponseDTO processPayment(PaymentRequestDTO request);

}
