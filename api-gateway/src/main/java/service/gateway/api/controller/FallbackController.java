package service.gateway.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.gateway.api.dto.FallbackDTO;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/user")
    public ResponseEntity<FallbackDTO> userFallback() {
        return new ResponseEntity<>(FallbackDTO.builder()
                .message("User service is down at the moment, please try again later")
                .cause("User service is not responding. Please raise a ticket to the support team")
                .path("/user/**")
                .errorCode(HttpStatus.GATEWAY_TIMEOUT.toString())
                .build(), HttpStatus.GATEWAY_TIMEOUT);
    }

    @GetMapping("/order")
    public ResponseEntity<FallbackDTO> orderFallback() {
        return new ResponseEntity<>(FallbackDTO.builder()
                .message("Order service is down at the moment, please try again later")
                .cause("Order service is not responding. Please raise a ticket to the support team")
                .path("/order/**")
                .errorCode(HttpStatus.GATEWAY_TIMEOUT.toString())
                .build(), HttpStatus.GATEWAY_TIMEOUT);
    }
}
