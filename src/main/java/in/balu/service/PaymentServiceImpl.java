package in.balu.service;

import in.balu.dto.PaymentRequestDTO;
import in.balu.dto.PaymentResponseDTO;
import in.balu.dto.UserDTO;
import in.balu.feignclients.UserFeignClient;
import in.balu.models.Payment;
import in.balu.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {

        UserDTO userDTO = userFeignClient.fetchUser(request.getUserId().intValue());

        if(userDTO == null) {
            throw new RuntimeException("User not Found in DB");
        }

        Payment payment = new Payment();

        BeanUtils.copyProperties(request, payment);
        payment.setStatus("SUCCESS");

        paymentRepository.save(payment);

        return mapToDto(payment, userDTO) ;

    }

    private PaymentResponseDTO mapToDto(Payment payment, UserDTO userDTO) {

        PaymentResponseDTO response = new PaymentResponseDTO();
        BeanUtils.copyProperties(payment, response);
        response.setPaymentId(payment.getId());
        response.setUserDTO(userDTO);

        return response;

    }
}