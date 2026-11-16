package in.balu.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {

    private Long paymentId;

    private Long orderId;

    private BigDecimal amount;

    private String status;

    private LocalDateTime paymentTime;

    private UserDTO userDTO;

}
