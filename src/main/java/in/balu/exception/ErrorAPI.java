package in.balu.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorAPI {

    private String message;

    private String status;

    private String error;

    private LocalDateTime localDateTime;

}
