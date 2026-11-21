package in.balu.service;

import in.balu.dto.PaymentRequestDTO;
import in.balu.dto.UserDTO;
import in.balu.exception.ResourceNotFoundException;
import in.balu.feignclients.UserFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserIntegrationService {

    @Autowired
    private UserFeignClient userFeignClient;

    @CircuitBreaker(name = "userServiceDB", fallbackMethod = "userFallBack")
    public UserDTO fetchUser(PaymentRequestDTO request) {
        UserDTO userDTO = userFeignClient.fetchUser(request.getUserId().intValue());

        if(userDTO == null) {
            throw new RuntimeException("User not Found in DB");
        }
        return userDTO;
    }

    public UserDTO userFallBack(PaymentRequestDTO request, Throwable t) {
        log.error("User Service FallBack {}", t.getMessage());

        throw new ResourceNotFoundException("User Service is unavailable. Please try again later");

    }

}
